package com.nesterov.tasksexecutortgbot.bot;

import com.nesterov.tasksexecutortgbot.bot.dao.CommandMapper;
import com.nesterov.tasksexecutortgbot.bot.dao.TasksDbDao;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

@Slf4j

public class Bot extends TelegramLongPollingBot {
    private final String token;
    private final String userName;
    private final CommandsHandler commandsHandler;
    public Bot(String token, String userName, CommandsHandler commandsHandler){
        this.userName = userName;
        this.token = token;
        this.commandsHandler = commandsHandler;
    }

    public boolean isCommand(Update update){
        if (update.getMessage() != null) {
            if (update.getMessage().getText().startsWith("/")){
                return true;
            }
        }
        return false;
    }
    @Override
    public void onUpdateReceived(Update update) {
//        String message = update.getMessage().getText();
//        sendMsg(update.getMessage().getChat().getUserName(), message);
        if (isCommand(update)){
            System.out.println("обработка комады");
            sendMessage(commandsHandler.handle(update.getMessage(), update.getMessage().getChat().getUserName()));
            System.out.println(update.getMessage());
            System.out.println(update.getUpdateId());
            return;
        }
        log.info(update.getMessage().getFrom().toString());

    }

    private InlineKeyboardMarkup creatMainButtons(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("info");
        inlineKeyboardButton1.setCallbackData("info button was clicked");
        List <InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(inlineKeyboardButton1);
        List <List<InlineKeyboardButton>> buttonsList = new ArrayList<>();
        buttonsList.add(buttons);
        inlineKeyboardMarkup.setKeyboard(buttonsList);
        return inlineKeyboardMarkup;
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        sendMessage.setReplyMarkup(creatMainButtons());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }

    public void sendMessage(SendMessage sendMessage){
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}