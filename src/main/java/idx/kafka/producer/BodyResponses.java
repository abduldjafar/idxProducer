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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    BodyResponses() {}

    BodyResponses(String name) {

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

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
        return "Employee{" + "name='" + this.name + '\'';
    }
}
