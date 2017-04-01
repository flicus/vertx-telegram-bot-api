/*
 *
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2016 schors
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
 *
 */

package org.schors.vertx.telegram.bot.commands;

import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.schors.vertx.telegram.bot.TelegramBot;
import org.schors.vertx.telegram.bot.api.types.Update;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager {

    private static final Logger log = Logger.getLogger(CommandManager.class);

    private TelegramBot bot;
    private Pattern commandPattern;

    private Set<Command> commands = new HashSet<>();
    private Set<Check> checks = new HashSet<>();
    private Set<Command> postExecute = new HashSet<>();
    private Command defaultCommand = new DefaultCommand("Unknown command");

    public CommandManager() {
    }

    public CommandManager(TelegramBot bot) {
        this.bot = bot;
    }

    public CommandManager addCommand(Command command) {
        BotCommand annotation = command.getClass().getAnnotation(BotCommand.class);
        if (annotation.isDefault()) {
            setDefaultCommand(command);
        } else if (annotation.isPostExecute()) {
            command.setCommandManager(this);
            postExecute.add(command);
        } else {
            command.setCommandManager(this);
            commands.add(command);
        }
        return this;
    }

    public CommandManager addCommand(Check check) {
        check.setCommandManager(this);
        checks.add(check);
        return this;
    }

    public CommandManager addCheck(Check check) {
        check.setCommandManager(this);
        checks.add(check);
        return this;
    }

    public CommandManager loadCommands() {
        return loadCommands(null);
    }

    public CommandManager loadCommands(String _package) {
        bot.getVertx().executeBlocking(future -> {
            Reflections reflections = _package == null ? new Reflections(this.getClass().getClassLoader()) : new Reflections(_package);
            Set<Class<?>> res = reflections.getTypesAnnotatedWith(BotCommand.class);
            for (Class _class : res) {
                if (Command.class.isAssignableFrom(_class)) {
                    try {
                        Command command = (Command) _class.newInstance();
                        addCommand(command);
                    } catch (Exception e) {
                        log.error(e, e);
                    }
                }
            }
            Set<Class<?>> checks = reflections.getTypesAnnotatedWith(BotCheck.class);
            for (Class _class : checks) {
                if (Check.class.isAssignableFrom(_class)) {
                    try {
                        Check check = (Check) _class.newInstance();
                        addCheck(check);
                    } catch (Exception e) {
                        log.error(e, e);
                    }
                }

            }
        }, result -> {

        });
        return this;
    }

    public void handle(Update update) {
        String text = update.getMessage().getText();
        String userName = update.getMessage().getFrom().getUsername();
        log.warn("onUpdate: " + text + ", " + userName);
        execute(text, createContext(update));
    }

    public CommandManager execute(String text, CommandContext context) {
        //if any of the checks returns false, do not execute command
        if (!checks.stream().anyMatch(check -> !check.execute(text, context))) {
            commands.stream()
                    .filter(cmd -> {
                        BotCommand annotation = cmd.getClass().getAnnotation(BotCommand.class);
                        commandPattern = Pattern.compile(annotation.regexp());
                        Matcher matcher = commandPattern.matcher(text);
                        return matcher.matches();
                    })
                    .findAny().orElse(defaultCommand).execute(text, context);
            postExecute.stream().forEach(cmd -> cmd.execute(text, context));
        }
        return this;
    }

    public CommandManager setDefaultCommand(Command command) {
        this.defaultCommand = command;
        this.defaultCommand.setCommandManager(this);
        return this;
    }

    public CommandContext createContext(Update update) {
        return new CommandContext(update, bot.getFacilities());
    }

    public TelegramBot getBot() {
        return this.bot;
    }

    public CommandManager setBot(TelegramBot bot) {
        this.bot = bot;
        return this;
    }
}
