package com.kaminskyi.helpdesk.repository;

import com.kaminskyi.helpdesk.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

    @Query("SELECT u FROM Users u WHERE u.login = :login")
    Users findUserByLogin(@Param("login") String login);

    @Query("SELECT u FROM Users u WHERE u.role='ROLE_AGENT'")
    Users findAllAgents();


}
