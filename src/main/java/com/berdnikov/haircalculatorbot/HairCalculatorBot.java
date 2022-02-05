package com.berdnikov.haircalculatorbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class HairCalculatorBot extends TelegramLongPollingBot {
    public static void main(String[] args) {


        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new HairCalculatorBot());
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    Premium premium = context.getBean("premium", Premium.class);
    Kids kids = context.getBean("kids", Kids.class);

    @Override
    public String getBotUsername() {
        return "HairCalculatorBot";
    }

    @Override
    public String getBotToken() {
        return "TOKEN";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message.getText().startsWith("/kids") || message.getText().startsWith("/k")){
            sendMsg(message, kids.getPrice(message.getText()));
        }else if(message.getText().startsWith("/premium")|| message.getText().startsWith("/p")){
            sendMsg(message, premium.getPrice(message.getText()));
        }else{
            switch (message.getText()) {
                case "/start" -> sendMsg(message, "Для вычисления наберите /категория волос + длинна волос + количество" +
                        "\n Категория волос: \n *Премиум* (/premium или /p ). \n *Деткие* (/kids или /k )." +
                        "\n Пример: /p 40 3");

            }
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}