package handler;

import command.Command;
import command.ParsedCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import services.WeatherService;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class WeatherHandler extends AbstractHandler {
    private final WeatherService weatherService = new WeatherService();

    @Override
    public CompletableFuture<SendMessage> operate(String chatId, ParsedCommand parsedCommand) throws IOException {
        Command command = parsedCommand.getCommand();

        return switch (command) {
            case CITY -> CompletableFuture.completedFuture(weatherService.sendForecastByCity(parsedCommand.getText(), chatId));
            case LOCATION -> CompletableFuture.completedFuture(weatherService.sendForecastByLocation(Double.parseDouble(parsedCommand.getText()),
                    Double.parseDouble(parsedCommand.getText2()), chatId));
            default -> null;
        };
    }
}
