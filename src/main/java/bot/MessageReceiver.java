package bot;

import command.Command;
import command.CommandParser;
import command.ParsedCommand;
import handler.AbstractHandler;
import handler.DefaultHandler;
import handler.SystemHandler;
import handler.WeatherHandler;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Slf4j
public class MessageReceiver {
    private final CommandParser commandParser = new CommandParser();
    private final SystemHandler systemHandler = new SystemHandler();
    private final WeatherHandler weatherHandler = new WeatherHandler();
    private final DefaultHandler defaultHandler = new DefaultHandler();


    public AbstractHandler getHandlerForMessage(Command command) {

        return switch (command) {
            case START, HELP -> systemHandler;
            case CITY, LOCATION -> weatherHandler;
            default -> defaultHandler;
        };
    }

    public SendMessage analyzeUpdate(Update update) {
        Long chatID = update.getMessage().getChatId();
        Message message = update.getMessage();
        ParsedCommand parsedCommand = commandParser.getParsedCommand(message);
        Command command = parsedCommand.getCommand();

        AbstractHandler handlerForCommand = getHandlerForMessage(command);

        SendMessage sendMessage = new SendMessage(chatID.toString(), "Something is wrong");
        try {
            sendMessage = handlerForCommand.operate(chatID.toString(), parsedCommand).get();
            return sendMessage;
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            return sendMessage;
        } catch (IOException e) {
            log.error(e.getMessage());
            sendMessage = new SendMessage(chatID.toString(), "Don't know that city :(");
            return sendMessage;
        }

    }
}
