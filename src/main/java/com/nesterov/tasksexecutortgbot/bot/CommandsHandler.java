package com.nesterov.tasksexecutortgbot.bot;

import com.nesterov.tasksexecutortgbot.bot.dao.ShortCommandMapper;
import com.nesterov.tasksexecutortgbot.bot.dao.TasksDbDao;
import com.nesterov.tasksexecutortgbot.bot.dto.ShortCommand;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

@Service
public class CommandsHandler {

    TasksDbDao tasksDbDao;

    public CommandsHandler (TasksDbDao tasksDbDao){
        this.tasksDbDao = tasksDbDao;
    }

    public SendMessage handle(Message message, String tgLogin){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        switch (message.getText()){
            case "/info":
                for (ShortCommand shortCommand:
                     shortCommands)
                {

                }

                sendMessage.setText(tasksDbDao.getShortCurrentCommands(tgLogin).toString());
                return sendMessage;
            case "/set":
                sendMessage.setText("команда set работает");
                return sendMessage;
            default:
                return null;
        }
    }
}
