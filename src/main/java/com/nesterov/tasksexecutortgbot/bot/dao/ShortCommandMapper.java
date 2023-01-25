package com.nesterov.tasksexecutortgbot.bot.dao;

import com.nesterov.tasksexecutortgbot.bot.dto.Command;
import com.nesterov.tasksexecutortgbot.bot.dto.CommandType;
import com.nesterov.tasksexecutortgbot.bot.dto.ShortCommand;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class ShortCommandMapper implements RowMapper<ShortCommand> {

    @Override
    public ShortCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ShortCommand(
                rs.getInt("id"),
                rs.getString("command"),
                CommandType.valueOf(rs.getString("type").toUpperCase(Locale.ROOT))
                );
    }
}
