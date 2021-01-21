package sample.MySQL.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.lastName LIKE %?1%")
    List<User> findByLastName(String lastName);
}
