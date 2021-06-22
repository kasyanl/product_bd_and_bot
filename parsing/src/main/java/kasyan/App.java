package kasyan;

import kasyan.bean.Course;
import org.json.JSONObject;

public class App {
    public static void main(String[] args){
        System.out.println(App.getUser());

    }

    public static Course getUser(String response) {
        var userJson = new JSONObject(response);
        var usdIn = userJson.getDouble("USD_in");
        var usdOut = userJson.getDouble("USD_out");
        var sapId = userJson.getLong("sap_id");
        var street = userJson.getString("street");

        return new Course(usdIn, usdOut, sapId, street);
    }

}
