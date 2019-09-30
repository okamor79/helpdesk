package com.kaminskyi.helpdesk.service;

import com.kaminskyi.helpdesk.entity.Projects;

import java.util.List;

public interface ProjectService {

    void save(Projects projects);

    Projects findProjectByID(Long id);

    List<Projects> findAllProjects();

    void update(Projects project);
}
