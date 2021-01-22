package sample.MySQL.project;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTransactionRepository extends CrudRepository<UserTransaction, Long> {

    @Query("SELECT t FROM UserTransaction t WHERE CONCAT(t.userId) LIKE %?1%")
    List<UserTransaction> findByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("update UserTransaction t set t.userId = ?1 where t.transactionId = ?2")
    void setUserTransactionInfoById(@Param("userId") Long userId, @Param("transactionId") Long transactionId);
}
