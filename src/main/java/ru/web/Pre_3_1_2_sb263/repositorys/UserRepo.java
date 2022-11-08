package ru.web.Pre_3_1_2_sb263.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.Pre_3_1_2_sb263.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByFirstName(String username);

    User findByEmail(String email);
}
