package ru.web.Pre_3_1_2_sb263.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.web.Pre_3_1_2_sb263.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.userRoles where u.firstName = :username")
    User findByFirstName(@Param("username") String username);
   @Query("select u from User u join fetch u.userRoles where u.email = :email")
   User findByEmail(@Param("email") String email);
}
