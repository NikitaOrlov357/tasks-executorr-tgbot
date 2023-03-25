package com.nesterov.tasksexecutortgbot.bot.models;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table (name = "command")
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String command;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private CommandType typeId;
    private  long regularity;
    private  long start;
  //  @Column(name = "owner_id")
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;
    private Date time;
}
