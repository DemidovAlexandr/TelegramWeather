package services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SystemService {


    public SendMessage sendStartMessage(String chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);

        StringBuilder text = new StringBuilder();
        text.append("Hi! I am a weather forecaster").append("\n");
        text.append("I was created by Alex Dem").append("\n").append("\n");
        text.append("Type [/help](/help) to see what I can do");
        sendMessage.setText(text.toString());
        return sendMessage;
    }

    public SendMessage sendHelpMessage(String chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);

        StringBuilder text = new StringBuilder();
        text.append("This is help message:").append("\n");
        text.append("[/start](/start) - show start message").append("\n");
        text.append("[/help](/help) - show this help message").append("\n");
        text.append("[/city](/city) - get forecast for any city").append("\n");
        text.append("Also, you can send me your **location** for more accurate forecast!");
        sendMessage.setText(text.toString());
        return sendMessage;
    }
}
