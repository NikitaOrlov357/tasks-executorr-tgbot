package com.nesterov.tasksexecutortgbot.bot.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long phone;
    @Column(name = "tg_login")
    private String tgLogin;
    @Column(name = "tg_id")
    private long tgId;
    @OneToMany(mappedBy = "owner")
    private List<Command> commands;
}
