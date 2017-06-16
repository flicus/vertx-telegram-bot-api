/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2017 schors
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package org.schors.vertx.telegram.bot.commands;

import io.vertx.core.Handler;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.schors.vertx.telegram.bot.TelegramBot;
import org.schors.vertx.telegram.bot.api.types.Update;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class CommandHandler implements Handler<Update> {

    private static final Logger log = Logger.getLogger(CommandHandler.class);

    private TelegramBot bot;
    private Pattern commandPattern;

    private Set<Command> commands = new HashSet<>();
    private Command defaultCommand = new DefaultCommand("Unknown command");
    private Command preExecute;
    private Command postExecute;

    public CommandHandler() {
    }

    public CommandHandler(TelegramBot bot) {
        this.bot = bot;
    }

    public CommandHandler addCommand(Command command) {
        BotCommand annotation = command.getClass().getAnnotation(BotCommand.class);
        if (annotation.isDefault()) {
            setDefaultCommand(command);
        } else if (annotation.isPostExecute()) {
            command.setCommandHandler(this);
            postExecute = command;
        } else if (annotation.isPreExecute()) {
            command.setCommandHandler(this);
            preExecute = command;
        } else {
            command.setCommandHandler(this);
            commands.add(command);
        }
        return this;
    }

    public CommandHandler loadCommands() {
        return loadCommands(null);
    }

    public CommandHandler loadCommands(String _package) {
        bot.getVertx().executeBlocking(future -> {
            String pckg = _package == null ? bot.getOptions().getCommandPackage() : _package;
            Reflections reflections = pckg == null ? new Reflections(this.getClass().getClassLoader()) : new Reflections(pckg);
            Set<Class<?>> res = reflections.getTypesAnnotatedWith(BotCommand.class);
            res.stream().filter(_class -> Command.class.isAssignableFrom(_class)).forEach(_class -> {
                try {
                    Command command = (Command) _class.newInstance();
                    addCommand(command);
                } catch (Exception e) {
                    log.error(e, e);
                }
            });
            future.complete();
        }, result -> {
            //check result

        });
        return this;
    }

    public void handle(Update update) {
        String text = update.getMessage().getText();
        String userName = update.getMessage().getFrom().getUsername();
        log.warn("onUpdate: " + text + ", " + userName);
        execute(createContext(update));
    }

    private boolean match(String text, String regexp) {
        commandPattern = Pattern.compile(regexp);
        return commandPattern.matcher(text).matches();
    }

    public CommandHandler execute(CommandContext context) {
        if (preExecute != null) {
            preExecute.execute(context, checkEvent -> {
                if (Boolean.TRUE.equals(checkEvent)) {
                    executeNormalCommand(context);
                }
            });
        } else {
            executeNormalCommand(context);
        }
        return this;
    }

    private void executeNormalCommand(CommandContext context) {
        commands.stream()
                .filter(cmd -> {
                    BotCommand annotation = cmd.getClass().getAnnotation(BotCommand.class);
                    if (context.getUpdate().getMessage() != null) {
                        return match(context.getUpdate().getMessage().getText(), annotation.message());
                    } else if (context.getUpdate().getInlineQuery() != null) {
                        return match(context.getUpdate().getInlineQuery().getQuery(), annotation.inline());
                    } else if (context.getUpdate().getCallbackQuery() != null) {
                        return match(context.getUpdate().getCallbackQuery().getMessage().getText(), annotation.callback());
                    } else if (context.getUpdate().getChannelPost() != null) {
                        return match(context.getUpdate().getChannelPost().getText(), annotation.channel());
                    }
                    return false;
                })
                .findAny()
                .orElse(defaultCommand)
                .execute(context, event -> {
                    if (postExecute != null) {
                        postExecute.execute(context, postEvent -> {
                        });
                    }
                });
    }

    public CommandHandler setDefaultCommand(Command command) {
        this.defaultCommand = command;
        this.defaultCommand.setCommandHandler(this);
        return this;
    }

    public CommandContext createContext(Update update) {
        return new CommandContext(update, bot.getFacilities());
    }

    public TelegramBot getBot() {
        return this.bot;
    }

    public CommandHandler setBot(TelegramBot bot) {
        this.bot = bot;
        return this;
    }
}
