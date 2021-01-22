package sample.MySQL.project;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.lastName LIKE %?1%")
    List<User> findByLastName(String lastName);
    
    @Transactional
    @Modifying
    @Query("update User t set t.firstName = ?1, t.lastName = ?2 where t.userId = ?3")
    void setUserInfoById(@Param("firstName") String firstName,
                                @Param("lastName") String lastName, @Param("userId") Long userId);
}

