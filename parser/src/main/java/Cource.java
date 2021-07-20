public class Cource {
    private String city;
    private double USD_in; //курс покупки структурным подразделением Доллар США
    private double USD_out; //курс продажи структурным подразделением Доллар США
    private double EUR_in; //- курс покупки структурным подразделением Евро
    private double EUR_out;// - курс продажи структурным подразделением Евро
    private double RUB_in;// - курс покупки структурным подразделением (100 ед.) Российский рубль
    private double RUB_out;// - курс продажи структурным подразделением (100 ед.) Российский рубль

    public Cource(String city, double USD_in, double USD_out, double EUR_in, double EUR_out, double RUB_in, double RUB_out) {
        this.city = city;
        this.USD_in = USD_in;
        this.USD_out = USD_out;
        this.EUR_in = EUR_in;
        this.EUR_out = EUR_out;
        this.RUB_in = RUB_in;
        this.RUB_out = RUB_out;
    }

    public Cource() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getUSD_in() {
        return USD_in;
    }

    public void setUSD_in(double USD_in) {
        this.USD_in = USD_in;
    }

    public double getUSD_out() {
        return USD_out;
    }

    public void setUSD_out(double USD_out) {
        this.USD_out = USD_out;
    }

    public double getEUR_in() {
        return EUR_in;
    }

    public void setEUR_in(double EUR_in) {
        this.EUR_in = EUR_in;
    }

    public double getEUR_out() {
        return EUR_out;
    }

    public void setEUR_out(double EUR_out) {
        this.EUR_out = EUR_out;
    }

    public double getRUB_in() {
        return RUB_in;
    }

    public void setRUB_in(double RUB_in) {
        this.RUB_in = RUB_in;
    }

    public double getRUB_out() {
        return RUB_out;
    }

    public void setRUB_out(double RUB_out) {
        this.RUB_out = RUB_out;
    }

    @Override
    public String toString() {
        return "Cource{" +
                "city='" + city + '\'' +
                ", USD_in=" + USD_in +
                ", USD_out=" + USD_out +
                ", EUR_in=" + EUR_in +
                ", EUR_out=" + EUR_out +
                ", RUB_in=" + RUB_in +
                ", RUB_out=" + RUB_out +
                '}';
    }
}
