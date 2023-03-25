package com.nesterov.tasksexecutortgbot.bot;

import com.nesterov.tasksexecutortgbot.bot.dto.SendMessageWithCommandId;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
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

    public boolean isCallBack(Update update){
        if(update.getCallbackQuery() != null){
            return true;
        }
        return false;
    }

    @Override
    public void onUpdateReceived(Update update) {
//        String message = update.getMessage().getText();
//        sendMsg(update.getMessage().getChat().getUserName(), message);
        if (isCommand(update)){
            List<SendMessageWithCommandId> sendMessageWithCommandIds = commandsHandler.handle(update.getMessage(), update.getMessage().getChat().getUserName());
            System.out.println("обработка комады");
            for (SendMessageWithCommandId message : sendMessageWithCommandIds
                 ) {
                sendMessage(message);
            }
            System.out.println(update.getMessage());
            System.out.println(update.getUpdateId());
            return;
        }
        if (isCallBack(update)){
            System.out.println("id = " + update);
        }
    }

    private InlineKeyboardMarkup createSubButton(Long commandId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("info");
        inlineKeyboardButton1.setCallbackData(commandId.toString());
        List <InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(inlineKeyboardButton1);
        List <List<InlineKeyboardButton>> buttonsList = new ArrayList<>();
        buttonsList.add(buttons);
        inlineKeyboardMarkup.setKeyboard(buttonsList);
        return inlineKeyboardMarkup;
    }

    private synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
//        sendMessage.setReplyMarkup(createSubButton());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }

    public void sendMessage(SendMessageWithCommandId sendMessageWithCommandId){
        try {
            sendMessageWithCommandId.setReplyMarkup(createSubButton(sendMessageWithCommandId.getCommandId()));
            execute(sendMessageWithCommandId);
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