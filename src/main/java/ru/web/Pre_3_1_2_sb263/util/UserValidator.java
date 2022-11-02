package ru.web.Pre_3_1_2_sb263.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.web.Pre_3_1_2_sb263.model.User;
import ru.web.Pre_3_1_2_sb263.service.UserServiceImp;

public class UserValidator implements Validator {

    private final UserServiceImp userServiceImp;
    @Autowired
    public UserValidator(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        UserDetails userCheckName = userServiceImp.loadUserByUsername(user.getFirstName());
        if (userCheckName != null) {
            errors.rejectValue("firstName","", "Пользователь \"%s\" уже существует");
        }
    }
}
