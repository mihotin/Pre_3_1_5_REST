package ru.web.Pre_3_1_2_sb263.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.web.Pre_3_1_2_sb263.model.Role;
import ru.web.Pre_3_1_2_sb263.model.User;
import ru.web.Pre_3_1_2_sb263.service.RoleService;
import ru.web.Pre_3_1_2_sb263.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
public class RESTController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public RESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/user");
        return modelAndView;
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<User>> printUsers() {
        final List<User> users = userService.getAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> printOneUser(@PathVariable("id") Long id) {
        final User user = userService.getOne(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/all")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("/admin/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody User user) {
        userService.update(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/del/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/auth")
    public ResponseEntity<User> printAuthUser(Principal principal) {
        // User userLog = userService.findUserByName(principal.getName());
        return new ResponseEntity<>(userService.findUserByName(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("admin/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }
}
