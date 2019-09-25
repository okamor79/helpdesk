package com.kaminskyi.helpdesk.service;

import com.kaminskyi.helpdesk.dto.UsersFilter;
import com.kaminskyi.helpdesk.entity.CustomUserDetails;
import com.kaminskyi.helpdesk.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {

    void save(Users user);

    Users findUserByID(Long id);

    Users findUserByLogin(String login);

    List<Users> findAllUsers();

    void userLogged(CustomUserDetails userDetails);

    List<String> findAllLDAPUsers();

    void removeUser(Long id);

    Page<Users> findAllUserByPage(Pageable pageable);

    Page<Users> findFilteredUsersByPage(Pageable pageable, UsersFilter filter);

}
