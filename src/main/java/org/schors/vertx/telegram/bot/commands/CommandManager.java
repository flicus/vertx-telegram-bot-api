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

import org.schors.vertx.telegram.bot.TelegramBot;
import org.telegram.telegrambots.api.objects.Update;

import java.util.HashSet;
import java.util.Set;

public class CommandManager {

    private TelegramBot bot;

    private Set<Command> commands = new HashSet<>();
    private Command defaultCommand = new DefaultCommand("Unknown command");

    public CommandManager(TelegramBot bot) {
        this.bot = bot;
    }

    public CommandManager() {
    }

    public CommandManager addCommand(Command command) {
        if (!commands.contains(command)) {
            command.setBot(this.bot);
            commands.add(command);
        }
        return this;
    }

    public CommandManager execute(String text, CommandContext context) {
        commands.stream()
                .filter(command -> command.isApplicable(text))
                .findAny().orElse(defaultCommand).execute(text, context);
        return this;
    }

    public CommandManager setDefaultCommand(Command command) {
        this.defaultCommand = command;
        this.defaultCommand.setBot(this.bot);
        return this;
    }

    public CommandContext createContext(Update update) {
        return new CommandContext(update);
    }

    public CommandManager setBot(TelegramBot bot) {
        this.bot = bot;
        return this;
    }
}
