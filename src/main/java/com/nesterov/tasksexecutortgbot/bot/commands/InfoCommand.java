package com.nesterov.tasksexecutortgbot.bot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InfoCommand extends BotCommand {

    public InfoCommand(String commandIdentifier, String description){
        super(commandIdentifier,description);
    }

    public void execute(AbsSender absSender, SendMessage sendMessage, User user) throws TelegramApiException {
        absSender.execute(sendMessage);
    }

}
