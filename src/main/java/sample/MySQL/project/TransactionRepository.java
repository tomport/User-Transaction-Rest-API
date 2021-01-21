package sample.MySQL.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t WHERE CONCAT(t.userId) LIKE %?1%")
    List<Transaction> findByUserId(@Param("userId") Long userId);

}

