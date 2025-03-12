package latina.negocio;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer implements Serializable {
    private Long id;
    private String name;

    // No-arg constructor
    public Customer() {}

    @Id // property access is used
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
