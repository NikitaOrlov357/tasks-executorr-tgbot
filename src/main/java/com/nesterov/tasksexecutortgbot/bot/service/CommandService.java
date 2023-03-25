package com.nesterov.tasksexecutortgbot.bot.service;

import com.nesterov.tasksexecutortgbot.bot.models.Command;
import com.nesterov.tasksexecutortgbot.bot.repository.CommandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandService {

    private CommandRepository commandRepository;

    public CommandService(CommandRepository commandRepository){
        this.commandRepository = commandRepository;
    }

    public List<Command> list() {
        return commandRepository.findAll();
    }

}
