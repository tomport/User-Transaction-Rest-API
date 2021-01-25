package userTransactionRestAPI.userTransaction;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTransactionRepository extends CrudRepository<UserTransaction, Long> {

    @Query(value = "SELECT * FROM user_transactions u WHERE CONCAT(u.user_id) LIKE %?1%", nativeQuery = true)
    List<UserTransaction> findByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query(value = "update user_transactions u set u.user_id = ?1 where u.transaction_id = ?2", nativeQuery = true)
    void setUserTransactionInfoById(@Param("userId") Long userId, @Param("transactionId") Long transactionId);
}
