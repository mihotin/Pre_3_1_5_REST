package ru.web.Pre_3_1_2_sb263.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.web.Pre_3_1_2_sb263.model.Role;
import ru.web.Pre_3_1_2_sb263.model.User;
import ru.web.Pre_3_1_2_sb263.service.RoleService;
import ru.web.Pre_3_1_2_sb263.service.UserService;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.save(role1);
        roleService.save(role2);

        List<Role> roleAdmin = new ArrayList<>();
        List<Role> roleUser = new ArrayList<>();
        List<Role> roleAll = new ArrayList<>();

        roleAdmin.add(role1);
        roleUser.add(role2);
        roleAll.add(role1);
        roleAll.add(role2);

        User admin = new User("admin", "adminov", "admin@mail.ru", (byte) 50, "admin", roleAdmin);
        User user1 = new User("user", "userov", "user@mail.ru", (byte) 15, "user", roleUser);
        User user2 = new User("user2", "userov2", "user2@mail.ru", (byte) 120, "user2", roleAll);

        userService.save(admin);
        userService.save(user1);
        userService.save(user2);
    }
}
