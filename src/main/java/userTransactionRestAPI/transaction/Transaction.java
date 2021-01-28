package userTransactionRestAPI.transaction;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long transactionId;
    @Column
    private Long userId;
    @Column
    private Long amount;
    @Column
    private String timeCreated;
    @Column
    private String timeUpdated;

    public Transaction(){
    }
    public Transaction(Long userId, Long amount){
        this.userId = userId;
        this.amount = amount;
        this.timeCreated = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        this.timeUpdated = this.timeCreated;
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

    public String getTimeCreated() {
        return this.timeCreated;
    }
    public void setTimeCreated() {
        this.timeCreated = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public String getTimeUpdated() {
        return this.timeUpdated;
    }
    public void setTimeUpdated() {
        this.timeUpdated = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public Long getAmount() {
        return this.amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
