package com.nesterov.tasksexecutortgbot.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bot")

public class BotSettings {

    private String userName;
    private String token;

}
