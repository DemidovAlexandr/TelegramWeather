package bot;

import config.BotConfig;
import handler.AbstractHandler;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
// TODO: 19.08.2021 Add message reciever with analyze for update type 
public class WeatherBot extends TelegramLongPollingBot {
    private final MessageReceiver messageReceiver = new MessageReceiver();
    private AbstractHandler handler;

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            SendMessage message = new SendMessage();
//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText(update.getMessage().getText());
//
//            try {
//                execute(message);
//            } catch (TelegramApiException e) {
//               log.error(e.getMessage());
//            }
//        }
//    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            SendMessage sendMessage = messageReceiver.analyzeUpdate(update);
            try {
                executeAsync(sendMessage);
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }
}
