package com.nesterov.tasksexecutortgbot.bot.dto;

import lombok.Value;
@Value
public class ShortCommand {
    Long id;
    String command;
    CommandType type;

    @Override
    public String toString() {
        return  "id=" + id +
                "\ncommand = " + command +
                "\ntype = " + type + "\n" ;
    }
}

