import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URL;

public class Main {
   /**
            * Для простоты и удобства используем уже сформированную строку
     * с запросом погоды в Лондоне на данный момент
     * <p>
     * другие примеры запросов можете глянуть здесь
     * {@see <a href="http://openweathermap.org/current">openweathermap</a>}
     * также Вам понадобится свой API ключ
     */
    public static final String WEATHER_URL =
            "https://belarusbank.by/api/kursExchange?city=";

    public static void main(String[] args) throws ParseException {
        // создаем URL из строки
        URL url = JsonUtils.createUrl(WEATHER_URL+"Брест");

        // загружаем Json в виде Java строки
        String resultJson = JsonUtils.parseUrl(url);

        CourceService.getUser(resultJson);
//        System.out.println("Полученный JSON:\n" + resultJson);

        // парсим полученный JSON и печатаем его на экран
//        CourceService.parseCurrentCourseJson(resultJson);

        // формируем новый JSON объект из нужных нам погодных данных
//        String json = JsonUtils.buildWeatherJson();
//        System.out.println("Созданный нами JSON:\n" + json);
//    }

}
}
