package sample.MySQL.project;

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
    /*
    User(){}
          User(String firstName, String lastName, Long amount){
           this.firstName = firstName;
           this.lastName = lastName;
           this.amount = amount;
       }
       User(String name, Long amount){
           String[] parts = name.split(" ");
           this.firstName = parts[0];
           this.lastName = parts[1];
           this.amount = amount;
       }
    */
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
    }}