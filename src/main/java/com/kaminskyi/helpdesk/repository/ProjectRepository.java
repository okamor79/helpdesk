package com.kaminskyi.helpdesk.repository;

import com.kaminskyi.helpdesk.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {

}
