package com.nesterov.tasksexecutortgbot.bot;

import lombok.extern.slf4j.Slf4j;
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
    private final CommandsHandler commandsHandler = new CommandsHandler();
    public Bot(String token, String userName){
        this.userName = userName;
        this.token = token;
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
        if (isCommand(update)){
            System.out.println("обработка комады");
            sendMessage(commandsHandler.handle(update.getMessage()));
            return;
        }
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
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