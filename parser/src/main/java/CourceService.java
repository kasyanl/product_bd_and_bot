import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CourceService {


  public static Cource getUser(String response) throws JSONException {

      JSONObject userJson = new JSONObject(response);

       String city = (String) userJson.get("city");
       double USD_in = (double)userJson.get("USD_in");
       double USD_out = (double)userJson.get("USD_out");
       double EUR_in = (double)userJson.get("EUR_in");
       double EUR_out = (double)userJson.get("EUR_out");
       double RUB_in = (double)userJson.get("RUB_in");
       double RUB_out = (double)userJson.get("RUB_out");

      return new Cource(city, USD_in, USD_out, EUR_in,  EUR_out,  RUB_in,  RUB_out);
  }

}
