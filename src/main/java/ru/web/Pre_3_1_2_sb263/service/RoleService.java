package ru.web.Pre_3_1_2_sb263.service;

import ru.web.Pre_3_1_2_sb263.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    void save(Role role);
}
