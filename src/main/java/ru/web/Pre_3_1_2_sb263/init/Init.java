package ru.web.Pre_3_1_2_sb263.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.web.Pre_3_1_2_sb263.model.User;
import ru.web.Pre_3_1_2_sb263.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class Init {
    private final UserService userService;
    @Autowired
    public Init(UserService userService) {
        this.userService = userService;
    }

//    @PostConstruct
//    public void init() {
//        User user1 = new User("admin", "adminov", "admin@mail.com", (byte) 50, "admin", "ROLE_ADMIN");
//        User user2 = new User("user", "userov", "user@mail.com", (byte) 15, "user", "ROLE_USER");
//
//        userService.save(user1);
//        userService.save(user2);
//    }
}
