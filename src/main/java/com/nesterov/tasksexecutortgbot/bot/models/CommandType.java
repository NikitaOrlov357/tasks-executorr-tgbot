package com.nesterov.tasksexecutortgbot.bot.models;

import javax.persistence.*;

@Entity
@Table(name = "command_type")
public class CommandType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private com.nesterov.tasksexecutortgbot.bot.dto.CommandType type;
}
