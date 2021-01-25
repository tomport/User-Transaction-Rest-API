package userTransactionRestAPI.transaction;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Transactional
    @Modifying
    @Query(value = "update transactions t set t.user_id = ?1, t.amount = ?2, t.time_updated = ?3 where t.transaction_id = ?4", nativeQuery = true)
    void setTransactionInfoById(@Param("userId") Long userId, @Param("amount") Long amount,
                                @Param("timeUpdated") String timeUpdated, @Param("transactionId") Long transactionId);

}

