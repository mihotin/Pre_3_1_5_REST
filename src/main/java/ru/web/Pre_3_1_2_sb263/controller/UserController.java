package ru.web.Pre_3_1_2_sb263.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.Pre_3_1_2_sb263.model.User;
import ru.web.Pre_3_1_2_sb263.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String showUser(Principal principal, Model model) {
        User user = userService.findUserByName(principal.getName());
        model.addAttribute("user", user);
        return "user/user";
    }
}


