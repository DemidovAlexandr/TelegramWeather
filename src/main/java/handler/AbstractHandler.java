package handler;

import command.ParsedCommand;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public abstract class AbstractHandler {


    public abstract CompletableFuture<SendMessage> operate(String chatId, ParsedCommand parsedCommand) throws IOException;

}

