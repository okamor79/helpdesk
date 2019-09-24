package com.kaminskyi.helpdesk.repository;

import com.kaminskyi.helpdesk.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {

    @Query("SELECT pr FROM Projects pr WHERE LOWER(pr.code)=:code")
    Projects findProjectByCode(@Param("code") String code);

}
