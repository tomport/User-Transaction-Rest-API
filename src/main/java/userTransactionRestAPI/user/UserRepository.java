package userTransactionRestAPI.user;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users u WHERE u.last_name LIKE %?1%", nativeQuery = true)
    List<User> findByLastName(String lastName);
    
    @Transactional
    @Modifying
    @Query(value = "update users t set t.first_name = ?1, t.last_name = ?2 where t.user_id = ?3", nativeQuery = true)
    void setUserInfoById(@Param("firstName") String firstName,
                                @Param("lastName") String lastName, @Param("userId") Long userId);
}

