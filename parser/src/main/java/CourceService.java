import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CourceService {
//    public static final String WEATHER_URL =
//            "https://belarusbank.by/api/kursExchange?city=Брест";

//  public static Cource getUser(String response) throws JSONException {
//
//      JSONObject userJson = new JSONObject(response);
//
//       String city = (String) userJson.get("city");
//       double USD_in = (double)userJson.get("USD_in");
//       double USD_out = (double)userJson.get("USD_out");
//       double EUR_in = (double)userJson.get("EUR_in");
//       double EUR_out = (double)userJson.get("EUR_out");
//       double RUB_in = (double)userJson.get("RUB_in");
//       double RUB_out = (double)userJson.get("RUB_out");
//
//      return new Cource(city, USD_in, USD_out, EUR_in,  EUR_out,  RUB_in,  RUB_out);
//  }
//
//    public Cource readUserInfo(String city) throws IOException, JSONException {
//        String requestUrl = "https://belarusbank.by/api/kursExchange?city=" + city;
//
//    URL url = new URL(requestUrl);
//    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//    // add auth header to request
//
//    InputStream in;
//    int status = connection.getResponseCode();
//        if (status != HttpURLConnection.HTTP_OK) {
//        in = connection.getErrorStream();
//    } else {
//        in = connection.getInputStream();
//    }
//
//    String response = convertStreamToString(in);
//        Cource user = jsonParser.getUser(response);
//
//        return user;
//}


//
//        public static void parseCurrentCourseJson(String resultJson) {
//        try {
//            // конвертируем строку с Json в JSONObject для дальнейшего его парсинга
//            JSONObject weatherJsonObject = (JSONObject) JSONValue.parseWithException(resultJson);
//
//            JSONArray weatherArray = (JSONArray) weatherJsonObject.get("Брест");
//            // достаем из массива первый элемент
//            JSONObject weatherData = (JSONObject) weatherArray.get(0);
//
//            Cource cource = new Cource();
//            cource.setEUR_in((double)weatherData.get("EUR_in"));
//            cource.setEUR_out((double)weatherData.get("EUR_out"));
//            // печатаем текущую погоду в консоль
//            System.out.println(cource);
//            System.out.println("курс доллара: " + weatherData.get("USD_out"));
//            // и описание к ней
//            System.out.println("курс евро: " + weatherData.get("EUR_out"));
//
//        } catch (org.json.simple.parser.ParseException e) {
//            e.printStackTrace();
//        }


















        // создаем URL из строки
//        URL url = JsonUtils.createUrl(WEATHER_URL);
//
//        // загружаем Json в виде Java строки
//        String resultJson = JsonUtils.parseUrl(url);
//        System.out.println("Полученный JSON:\n" + resultJson);

        // парсим полученный JSON и печатаем его на экран
//        JsonUtils.parseCurrentWeatherJson(resultJson);

        // формируем новый JSON объект из нужных нам погодных данных
//        String json = JsonUtils.buildWeatherJson();
//        System.out.println("Созданный нами JSON:\n" + json);
//    }
//    }
}
