package ru.web.Pre_3_1_2_sb263.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roleName")
    private String roleName;

    @ManyToMany(mappedBy = "userRoles")
    @JsonBackReference
    private List<User> users;

    public Role() {
    }

    public Role(String role) {
        this.roleName = role;
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return roleName.replace("ROLE_", "");
    }

    // Мтоеды GrantedAuthority
    @Override
    public String getAuthority() {
        return roleName;
    }
}
