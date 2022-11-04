package ru.web.Pre_3_1_2_sb263.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.web.Pre_3_1_2_sb263.model.Role;
import ru.web.Pre_3_1_2_sb263.repositorys.RoleRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImp implements RoleService{
    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImp(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAll() {
        return roleRepo.findAll();
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleRepo.save(role);
    }
}
