package sample.MySQL.project;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Transactional
    @Modifying
    @Query("update Transaction t set t.userId = ?1, t.amount = ?2, t.timeUpdated = ?3 where t.transactionId = ?4")
    void setTransactionInfoById(@Param("userId") Long userId, @Param("amount") Long amount,
                                @Param("timeUpdated") String timeUpdated, @Param("transactionId") Long transactionId);

}

