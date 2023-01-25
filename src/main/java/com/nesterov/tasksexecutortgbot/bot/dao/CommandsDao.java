package com.nesterov.tasksexecutortgbot.bot.dao;

import com.nesterov.tasksexecutortgbot.bot.dto.Command;
import com.nesterov.tasksexecutortgbot.bot.dto.ShortCommand;

import java.util.List;

public interface CommandsDao {
    List<ShortCommand> getShortCurrentCommands(String tgLogin);
}
