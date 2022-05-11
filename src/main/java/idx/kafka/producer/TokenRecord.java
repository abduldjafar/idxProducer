package idx.kafka.producer;

public class TokenRecord {

    String token;


    public TokenRecord() {
    }


    public String getToken() {
        return token;

    }

    public void setToken(String token) {
        this.token = token;
    }


    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }

}
