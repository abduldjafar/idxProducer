package idx.kafka.producer;

import javax.persistence.*;
import java.util.Objects;

@Entity
public
class TokenBodyResponses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String token;
    private  String topic;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    TokenBodyResponses() {}

    public String getToken() {
        return this.token;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public String getTopic() {
        return this.topic;
    }


    public void setTopic(String topic) {
        this.topic = topic;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof TokenBodyResponses))
            return false;
        TokenBodyResponses employee = (TokenBodyResponses) o;
        return Objects.equals(this.token, employee.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.token );
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}
