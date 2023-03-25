package com.nesterov.tasksexecutortgbot.bot.repository;

import com.nesterov.tasksexecutortgbot.bot.models.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
}
