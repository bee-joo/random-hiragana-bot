package org.example.telegram;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class RandomHiraganaBot extends TelegramLongPollingBot {

    private final ReplyKeyboard replyKeyboard;
    private final String BOT_USERNAME;
    private final String BOT_TOKEN;

    public RandomHiraganaBot(String botUsername, String botToken) {
        super();
        this.BOT_USERNAME = botUsername;
        this.BOT_TOKEN = botToken;
        this.replyKeyboard = new ReplyKeyboard();

        log.info("Init bot");
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text;

            if (message.getText().equals("/start")) {
                text = "Привет! Давай потренируем хирагану?";
            } else if (message.getText().equals("/random") || message.getText().equals("Случайный слог")) {
                text = GenerateHiragana.randomHiragana();
            } else {
                text = "Я вас не понял";
            }

            sendSimpleMessage(message, text);
        }
    }

    public void sendSimpleMessage(Message message, String text) {
        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());
        answer.setText(text);
        answer.setReplyMarkup(replyKeyboard.getReplyKeyboard());

        try {
            executeAsync(answer);
        } catch (TelegramApiException e) {
            log.error(String.valueOf(e));
        }
    }
}
