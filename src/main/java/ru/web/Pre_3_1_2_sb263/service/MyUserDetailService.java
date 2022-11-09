package ru.web.Pre_3_1_2_sb263.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.web.Pre_3_1_2_sb263.model.User;
import ru.web.Pre_3_1_2_sb263.repositorys.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;
    @Autowired
    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        if (userRepo.findByFirstName(username) != null) {
            user = userRepo.findByFirstName(username);
        } else if (userRepo.findByEmail(username) != null) {
            user =  userRepo.findByEmail(username);
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
