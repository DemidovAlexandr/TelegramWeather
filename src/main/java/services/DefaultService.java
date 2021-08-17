package services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class DefaultService {
    public SendMessage sendDefaultMessage(String chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);

        StringBuilder text = new StringBuilder();
        text.append("Unknown command").append("\n");
        sendMessage.setText(text.toString());
        return sendMessage;
    }
}
