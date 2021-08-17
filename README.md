# TelegramWeather
Погодный телеграм-бот 

### Функции
* Прислать прогноз погоды в ответ на название города
* Прислать прогноз погоды в ответ на геолокацию пользователя

### Подробности
* Парсит команды из чата
* При помощи CommandHandler'a вызывает нужный сервис
* В сервисе пользуется Jackson чтобы маппить объекты из .json, приходящие от OpenWeatherMap API
