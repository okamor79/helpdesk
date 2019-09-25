package com.kaminskyi.helpdesk.service.impl;

import com.kaminskyi.helpdesk.entity.Projects;
import com.kaminskyi.helpdesk.repository.ProjectRepository;
import com.kaminskyi.helpdesk.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void save(Projects projects) {
        projectRepository.save(projects);
    }

    @Override
    public Projects findProjectByID(Long id) {
        return projectRepository.getOne(id);
    }

    @Override
    public List<Projects> findAllProjects() {
        return projectRepository.findAll();
    }
}
