package org.example.jpa.service;

import org.example.jpa.entities.Role;
import org.example.jpa.entities.User;

public interface UserService {

    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roneName);
    void addRoleToUser(String username, String roleName);

    User authenticate(String userName, String password);
}
