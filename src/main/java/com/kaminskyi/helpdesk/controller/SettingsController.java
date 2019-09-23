package com.kaminskyi.helpdesk.controller;

import com.kaminskyi.helpdesk.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private UsersService usersService;

    @GetMapping({"/","/info"})
    public String indexSettings() {
        return "settings/info";
    }

    @GetMapping("/users")
    public String usersSettings() {
        return "settings/users";
    }

    @GetMapping("/projects")
    public String projectsSettings() {
        return "settings/projects";
    }

    @GetMapping("/agents")
    public String agentsSettings() {
        return "settings/agents";
    }

    @GetMapping("/user/remove/{id}")
    public String removeUser(Model model,  @PathVariable("id") Long id) {
        usersService.removeUser(id);
        return "redirect:/settings/users";
    }
}
