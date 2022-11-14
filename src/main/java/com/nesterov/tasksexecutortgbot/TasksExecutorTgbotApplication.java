package com.nesterov.tasksexecutortgbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(value = "com.nesterov.tasksexecutortgbot.configs")
public class TasksExecutorTgbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksExecutorTgbotApplication.class, args);

	}

}
