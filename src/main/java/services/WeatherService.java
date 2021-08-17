package services;

import DTO.ForecastDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class WeatherService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SendMessage sendForecastByCity(String city, String chatID) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);

        if (city.isEmpty()) {
            String text = "Use [/city](/city) + Your city name";
            sendMessage.setText(text);
            return sendMessage;
        }

        URL jsonURL = new URL(String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s&lang=ru",
                URLEncoder.encode(city, StandardCharsets.UTF_8), BotConfig.OPENWEATHERAPIKEY));

        log.info("URL composed: {}", jsonURL);
        ForecastDTO forecastDTO = objectMapper.readValue(jsonURL, ForecastDTO.class);

        String text = String.format("%s (%s:%s)%n", forecastDTO.name, forecastDTO.coord.lon, forecastDTO.coord.lat) +
                String.format("Current temp: %s%n", (forecastDTO.main.temp)) +
                String.format("Feels like: %s%n", (forecastDTO.main.feels_like)) +
                String.format("Description: %s%n", (forecastDTO.weather.get(0).description));

        sendMessage.setText(text);
        return sendMessage;
    }

    public SendMessage sendForecastByLocation(Double lat, Double lon, String chatID) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);

        URL jsonURL = new URL(String.format("http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric&lang=ru",
                lat, lon, BotConfig.OPENWEATHERAPIKEY));

        log.info("URL composed: {}", jsonURL);
        ForecastDTO forecastDTO = objectMapper.readValue(jsonURL, ForecastDTO.class);

        String text = String.format("%s (%s:%s)%n", forecastDTO.name, forecastDTO.coord.lon, forecastDTO.coord.lat) +
                String.format("Current temp: %s%n", (forecastDTO.main.temp)) +
                String.format("Feels like: %s%n", (forecastDTO.main.feels_like));

        sendMessage.setText(text);
        return sendMessage;
    }
}
