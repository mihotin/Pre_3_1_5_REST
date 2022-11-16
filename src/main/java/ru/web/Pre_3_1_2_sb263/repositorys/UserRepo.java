package ru.web.Pre_3_1_2_sb263.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.web.Pre_3_1_2_sb263.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByFirstName(String username);
   @Query("select u from User u join fetch u.userRoles where u.email = :email")
   Optional<User> findByEmail(@Param("email") String email);
}
