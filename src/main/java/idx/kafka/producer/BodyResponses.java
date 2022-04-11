package idx.kafka.producer;

import java.util.Objects;
import javax.persistence.*;

@Entity
public
class BodyResponses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String topic;
    private  String idxGroup;
    private  Integer idxTotal;
    private Integer idxNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    BodyResponses() {}

    BodyResponses(String name, String topic, String idxGroup, Integer idxTotal, Integer idxNumber) {

        this.name = name;
        this.topic = topic;
        this.idxGroup = idxGroup;
        this.idxTotal = idxTotal;
        this.idxNumber = idxNumber;
    }

    public String getName() {
        return this.name;
    }
    public String getTopic() {return  this.topic;};
    public  String getIdxGroup(){return  this.idxGroup;}
    public Integer getIdxTotal(){return  this.idxTotal;}
    public Integer getIdxNumber(){return  this.idxNumber;}

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof BodyResponses))
            return false;
        BodyResponses employee = (BodyResponses) o;
        return Objects.equals(this.name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name );
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}