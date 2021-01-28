package userTransactionRestAPI.userTransaction;


import javax.persistence.*;

@Entity
@Table(name = "userTransactions")
public class UserTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long transactionId;
    @Column
    private Long userId;

    public UserTransaction(){};
    public UserTransaction(Long userId){
        this.userId = userId;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
