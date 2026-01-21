package elisaraeli.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "location")

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "città", nullable = false)
    private String city;

    public Location() {
    }

    public Location(String name, String city) {
        this.name = name;
        this.city = city;
    }

    // GETTER E SETTER
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
