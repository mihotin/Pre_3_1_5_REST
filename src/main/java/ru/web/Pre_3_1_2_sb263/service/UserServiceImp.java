package ru.web.Pre_3_1_2_sb263.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.web.Pre_3_1_2_sb263.model.User;
import ru.web.Pre_3_1_2_sb263.repositorys.UserRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService, UserDetailsService{
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, User updateUser) {
        updateUser.setId(id);
        updateUser.setPassword(getPasswordEncoder().encode(updateUser.getPassword()));
        userRepo.save(updateUser);
    }

    @Override
    public User getOne(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByFirstName(username);

        if (user == null)
            throw new UsernameNotFoundException("User not found");

        return user;
    }
}
