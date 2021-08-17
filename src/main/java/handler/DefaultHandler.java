package handler;

import command.ParsedCommand;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import services.DefaultService;

import java.util.concurrent.CompletableFuture;

@NoArgsConstructor
public class DefaultHandler extends AbstractHandler {
    private final DefaultService defaultService = new DefaultService();

    @Override
    public CompletableFuture<SendMessage> operate(String chatId, ParsedCommand parsedCommand) {
        return CompletableFuture.completedFuture(defaultService.sendDefaultMessage(chatId));
    }
}
