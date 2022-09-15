package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.example.telegram.RandomHiraganaBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new RandomHiraganaBot(dotenv.get("HIRAGANA_BOT_USERNAME"), dotenv.get("HIRAGANA_BOT_TOKEN")));
        } catch (TelegramApiException e) {
            log.error(String.valueOf(e));
        }
    }
}

