package com.kaminskyi.helpdesk.service.impl;

import com.kaminskyi.helpdesk.repository.ProjectRepository;
import com.kaminskyi.helpdesk.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


}
