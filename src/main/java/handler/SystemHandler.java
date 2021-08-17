package handler;

import command.Command;
import command.ParsedCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import services.SystemService;

import java.util.concurrent.CompletableFuture;

public class SystemHandler extends AbstractHandler {
    private final SystemService systemService = new SystemService();

    @Override
    public CompletableFuture<SendMessage> operate(String chatId, ParsedCommand parsedCommand) {
        Command command = parsedCommand.getCommand();

        return switch (command) {
            case START -> CompletableFuture.completedFuture(systemService.sendStartMessage(chatId));
            case HELP -> CompletableFuture.completedFuture(systemService.sendHelpMessage(chatId));
            default -> null;
        };
    }
}
