package ru.web.Pre_3_1_2_sb263.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.web.Pre_3_1_2_sb263.model.User;
import ru.web.Pre_3_1_2_sb263.service.RoleService;
import ru.web.Pre_3_1_2_sb263.service.UserService;
import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String printUsers(@ModelAttribute("user") User user, Principal principal, Model model) {
            model.addAttribute("allusers", userService.getAll());
            model.addAttribute("roles", roleService.getAll());
            User userLog = userService.findUserByName(principal.getName());
            model.addAttribute("userLog", userLog);
        return "admin/index";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/admin";
        userService.save(user);
        return "redirect:/admin";
    }

        @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "redirect:/admin";
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
