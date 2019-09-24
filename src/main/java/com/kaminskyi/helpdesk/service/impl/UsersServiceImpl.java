package com.kaminskyi.helpdesk.service.impl;

import com.kaminskyi.helpdesk.dto.UsersFilter;
import com.kaminskyi.helpdesk.entity.CustomUserDetails;
import com.kaminskyi.helpdesk.entity.Users;
import com.kaminskyi.helpdesk.entity.enums.UserRole;
import com.kaminskyi.helpdesk.repository.UsersRepository;
import com.kaminskyi.helpdesk.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

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
            user.setDepartment(userDetails.getDepartament());
            user.setPosition(userDetails.getPosition());
            user.setPhone(userDetails.getPhoneNumber());
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

    @Override
    public Page<Users> findAllUserByPage(Pageable pageable) {

        return usersRepository.findAll(PageRequest.of(pageable.getPageNumber(), 15));
    }

    @Override
    public Page<Users> findFilteredUsersByPage(Pageable pageable, UsersFilter filter) {
        return usersRepository.findAll(getSpecification(filter), PageRequest.of(pageable.getPageNumber(), 15));
    }

    private Specification<Users> getSpecification(UsersFilter filter) {
        return new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicateCollation = new ArrayList<>();
                for (String t : String.valueOf(filter.toString()).split(" ")) {
                    UsersFilter rockyFilter = new UsersFilter();
                    rockyFilter.setSearch(t);
                    predicateCollation.add(criteriaBuilder.like(root.get("login"), '%' + rockyFilter.getSearch().toString() + '%'));
                    predicateCollation.add(criteriaBuilder.like(root.get("fullName"), '%' + rockyFilter.getSearch().toString() + '%'));
                }
                return criteriaBuilder.and(
                        criteriaBuilder.or(predicateCollation.toArray(new Predicate[predicateCollation.size()]))
                );
            }

        };
    }
}
