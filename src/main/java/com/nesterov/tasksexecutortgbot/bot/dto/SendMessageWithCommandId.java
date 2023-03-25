package com.nesterov.tasksexecutortgbot.bot.dto;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageWithCommandId extends SendMessage {
    private Long commandId;

    public Long getCommandId() {
        return commandId;
    }

    public void setCommandId(Long commandId) {
        this.commandId = commandId;
    }
}
