package com.nesterov.tasksexecutortgbot.configs;

import com.nesterov.tasksexecutortgbot.bot.Bot;
import com.nesterov.tasksexecutortgbot.bot.CommandsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    @Bean
    public static TelegramBotsApi registerBot(BotSettings botSettings, CommandsHandler commandsHandler) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(new Bot(botSettings.getToken(), botSettings.getUserName(), commandsHandler));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return telegramBotsApi;
    }
}
