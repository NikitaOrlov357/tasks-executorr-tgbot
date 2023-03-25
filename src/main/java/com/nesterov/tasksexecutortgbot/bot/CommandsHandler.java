package com.nesterov.tasksexecutortgbot.bot;

import com.nesterov.tasksexecutortgbot.bot.dao.CommandsDao;
import com.nesterov.tasksexecutortgbot.bot.dao.ShortCommandMapper;
import com.nesterov.tasksexecutortgbot.bot.dao.TasksDbDao;
import com.nesterov.tasksexecutortgbot.bot.dto.SendMessageWithCommandId;
import com.nesterov.tasksexecutortgbot.bot.dto.ShortCommand;
import com.nesterov.tasksexecutortgbot.bot.models.Command;
import com.nesterov.tasksexecutortgbot.bot.repository.CommandRepository;
import com.nesterov.tasksexecutortgbot.bot.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandsHandler {

    private final CommandService commandService;
    private final TasksDbDao tasksDbDao;
       public List<SendMessageWithCommandId> handle(Message message, String tgLogin){

        switch (message.getText()){
            case "/info":
                return getAllShortCommands(tgLogin, message.getChatId());
            case "/set":
//                sendMessage.setText("команда set работает");
//                return sendMessage;
            default:
                return null;
        }
    }

    public List<SendMessageWithCommandId> getAllShortCommands (String tgLogin, long chatId){
                List<SendMessageWithCommandId> sendMessageWithCommandIds = new ArrayList<>();
        for (Command command : commandService.list()) {
            SendMessageWithCommandId sendMessageWithCommandId = new SendMessageWithCommandId();
            sendMessageWithCommandId.setCommandId(command.getId());
            sendMessageWithCommandId.setText(command.toString());
            sendMessageWithCommandId.setChatId(chatId);
            sendMessageWithCommandIds.add(sendMessageWithCommandId);
        }
        return sendMessageWithCommandIds;
    }
}
