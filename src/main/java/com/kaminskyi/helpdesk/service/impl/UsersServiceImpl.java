package com.kaminskyi.helpdesk.service.impl;

import com.kaminskyi.helpdesk.entity.CustomUserDetails;
import com.kaminskyi.helpdesk.entity.Users;
import com.kaminskyi.helpdesk.entity.enums.UserRole;
import com.kaminskyi.helpdesk.repository.UsersRepository;
import com.kaminskyi.helpdesk.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Value("${spring.ldap.embedded.base-dn}")
    protected String baseDn;

    private static Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void save(Users user) {
        usersRepository.save(user);
    }

    @Override
    public Users findUserByID(Long id) {
        return usersRepository.getOne(id);
    }

    @Override
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void userLogged(CustomUserDetails userDetails) {
        Calendar calendar = Calendar.getInstance();
        if (usersRepository.findUserByLogin(userDetails.getLogin()) == null) {
            Users user = Users.builder()
                    .login(userDetails.getLogin())
                    .fullName(userDetails.getFullName())
                    .mail(userDetails.getMail())
                    .role(UserRole.ROLE_USER)
                    .lastActivity(calendar.getTime())
                    .build();
            usersRepository.save(user);
        } else {
            Users user = usersRepository.findUserByLogin(userDetails.getLogin());
            user.setLastActivity(calendar.getTime());
            user.setMail(userDetails.getMail());
            user.setFullName(userDetails.getFullName());
            usersRepository.save(user);
        }
    }

    public void synchronizeUser() {

        List<String> usersFromLDAP = findAllLDAPUsers();
        Iterator iterator = usersFromLDAP.listIterator();
        while (iterator.hasNext()) {
            String userLogin = (String) iterator.next();
            if (usersRepository.findUserByLogin(userLogin) == null) {
                Users user = Users.builder().login(userLogin).role(UserRole.ROLE_USER).build();
                usersRepository.save(user);
            }
        }

    }

    @Override
    public List<String> findAllLDAPUsers() {
        return ldapTemplate
                .search(""
                        , "(&(objectCategory=person)(objectClass=user)(!(userAccountControl:1.2.840.113556.1.4.803:=2)))"
                        , (AttributesMapper<String>) attr -> (String) attr.get("sAMAccountName").get()); //(memberOf=cn=all_users,cn=users,DC=bankgroup,DC=pbank,DC=if,DC=ua)
    }

    @Override
    public void removeUser(Long id) {
        usersRepository.deleteById(id);
    }


}
