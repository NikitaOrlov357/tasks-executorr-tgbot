package com.nesterov.tasksexecutortgbot.bot.dto;

import lombok.Value;

import java.util.Date;

@Value
public class Command {
    int id;
    String command;
    CommandType type;
    long regularity;
    long start;
    String owner;
    Date time;

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", command='" + command + '\'' +
                ", type='" + type + '\'' +
                ", regularity=" + regularity +
                ", start=" + start + "(" + new Date(start) + ")" +
                ", owner='" + owner + '\'' +
                ", time=" + time +
                '}';
    }
}