package com.nesterov.tasksexecutortgbot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class CommandsHandler {

    public SendMessage handle(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        switch (message.getText()){
            case "/info":
                sendMessage.setText("команда info работает");
                return sendMessage;
            case "/set":
                sendMessage.setText("команда set работает");
                return sendMessage;
            default:
                return null;
        }
    }
}
