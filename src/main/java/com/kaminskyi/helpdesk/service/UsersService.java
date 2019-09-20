package com.kaminskyi.helpdesk.service;

import com.kaminskyi.helpdesk.entity.CustomUserDetails;
import com.kaminskyi.helpdesk.entity.Users;

import java.util.List;

public interface UsersService {

    void save(Users user);

    Users findUserByID(Long id);

    List<Users> findAllUsers();

    void userLogged(CustomUserDetails userDetails);

    List<String> findAllLDAPUsers();


    void removeUser(Long id);

}
