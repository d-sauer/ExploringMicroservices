package ms.services.userService.core.model.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by davor on 21/05/15.
 */
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String address;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "addresses")
    private Set<User> users;

    public Address() {  }

    public Address(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
