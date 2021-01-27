package userTransactionRestAPI.userTransaction;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTransactionRepository extends CrudRepository<UserTransaction, Long> {

    @Query(value = "SELECT * FROM user_transactions ut WHERE CONCAT(ut.user_id) LIKE %?1%", nativeQuery = true)
    List<UserTransaction> findByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query(value = "update user_transactions ut set ut.user_id = ?1 where ut.transaction_id = ?2", nativeQuery = true)
    void setUserTransactionInfoById(@Param("userId") Long userId, @Param("transactionId") Long transactionId);
}
