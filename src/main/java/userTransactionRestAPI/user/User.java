package userTransactionRestAPI.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long userId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    User(){}
    public User(String firstName, String lastName){
           this.firstName = firstName;
           this.lastName = lastName;
    }
    public User(String name){
           String[] parts = name.split(" ");
           this.firstName = parts[0];
           this.lastName = parts[1];
    }
    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}