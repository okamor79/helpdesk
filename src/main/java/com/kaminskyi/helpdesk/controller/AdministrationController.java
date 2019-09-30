package com.kaminskyi.helpdesk.controller;

import com.kaminskyi.helpdesk.entity.Users;
import com.kaminskyi.helpdesk.service.ProjectService;
import com.kaminskyi.helpdesk.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/administration")
public class AdministrationController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/issue/type")
    public String showTypes(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("loginedUser");
        model.addAttribute("userRole", user.getRole());
        model.addAttribute("userID", user.getId());
        model.addAttribute("userFullName", user.getFullName());
        return "administration/issue/type";
    }

    @GetMapping("/projects")
    public String showProjectList(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("loginedUser");
        model
                .addAttribute("userRole", user.getRole())
                .addAttribute("userID", user.getId())
                .addAttribute("userFullName", user.getFullName())
                .addAttribute("agents", usersService.findAllAgents())
                .addAttribute("projectList", projectService.findAllProjects());
        return "administration/projects";
    }

}
