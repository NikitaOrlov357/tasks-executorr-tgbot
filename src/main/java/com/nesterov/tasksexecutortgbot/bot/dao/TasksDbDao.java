package com.nesterov.tasksexecutortgbot.bot.dao;

import com.nesterov.tasksexecutortgbot.bot.dto.Command;
import com.nesterov.tasksexecutortgbot.bot.dto.CommandType;
import com.nesterov.tasksexecutortgbot.bot.dto.ShortCommand;
import com.nesterov.tasksexecutortgbot.bot.repository.CommandRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class TasksDbDao implements CommandsDao {

    private final HikariDataSource hikariDataSource;
    @Override
    public List<ShortCommand> getShortCurrentCommands(String tgLogin) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        String sql = "SELECT command.id, command, type  FROM command INNER JOIN command_type ON type_id = command_type.id INNER JOIN owner ON owner.id = command.owner_id WHERE owner.tg_login= ?" ;
        Object[] values = new Object[] {tgLogin};
        log.debug("sql = {} ", sql);
        return jdbcTemplate.query (sql, new ShortCommandMapper(), values);
    }
}