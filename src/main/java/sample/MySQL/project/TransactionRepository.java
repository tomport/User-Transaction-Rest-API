package sample.MySQL.project;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t WHERE CONCAT(t.userId) LIKE %?1%")
    List<Transaction> findByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("update Transaction t set t.userId = ?1, t.amount = ?2, t.timeUpdated = ?3 where t.transactionId = ?4")
    void setTransactionInfoById(@Param("userId") Long userId, @Param("amount") Long amount,
                                @Param("timeUpdated") String timeUpdated, @Param("transactionId") Long transactionId);

}

