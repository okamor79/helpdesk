package com.kaminskyi.helpdesk.controller;

import com.kaminskyi.helpdesk.entity.CustomUserDetails;
import com.kaminskyi.helpdesk.entity.Projects;
import com.kaminskyi.helpdesk.entity.Users;
import com.kaminskyi.helpdesk.entity.enums.UserRole;
import com.kaminskyi.helpdesk.service.ProjectService;
import com.kaminskyi.helpdesk.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes(types = Users.class)
@RequestMapping("/")
public class DefaultController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProjectService projectService;

    UserRole userRole;
    Map<String, Object> mapAttributes = new HashMap<>();

    private static Logger logger = LoggerFactory.getLogger(DefaultController.class);

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
    CustomUserDetails userDetails;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/goodlogin")
    public String logined() {
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        userDetails = (CustomUserDetails) usernamePasswordAuthenticationToken.getPrincipal();
        usersService.userLogged(userDetails);
        Users user = usersService.findUserByLogin(userDetails.getLogin());
        request.getSession().setAttribute("loginedUser", user);
        model.addAttribute("userRole", user.getRole());
        model.addAttribute("userID", user.getId());
        model.addAttribute("userFullName", user.getFullName());
        return "index";
    }

    @GetMapping({"/settings", "/settings/info"})
    public String indexSettings(Model model) {
        model.addAllAttributes(mapAttributes);
        return "settings/info";
    }

    @GetMapping("/settings/projects")
    public String projectsSettings(Model model) {
        model.addAttribute("projectList", projectService.findAllProjects());
        model.addAttribute("agents", usersService.findAllAgents());
        model.addAllAttributes(mapAttributes);
        return "settings/projects";
    }

    @GetMapping("/settings/projects/create")
    public String projectCreate(Model model, @ModelAttribute("createProject") Projects projects) {
        model.addAttribute("agents", usersService.findAllAgents());
        if (projects == null) {
            model.addAttribute("createProject", new Projects());
        } else {
            model.addAttribute("createProject", projects);
        }
        model.addAttribute("pageTitle", "Новий проект");
        model.addAllAttributes(mapAttributes);
        return "settings/projects/create";
    }

    @GetMapping("/settings/projects/modify/{id}")
    public String modifyProject(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pageTitle", "Редагування проекту");
        model.addAttribute("agents", usersService.findAllAgents());
        model.addAttribute("createProject", projectService.findProjectByID(id));
        model.addAllAttributes(mapAttributes);
        return "settings/projects/modify";
    }

    @PostMapping("/settings/projects/modify")
    public String postModifyProject(@ModelAttribute("createProject") Projects projects) {
        projectService.update(projects);
        return "redirect:/settings/projects";
    }

    @PostMapping("/settings/projects/create")
    public String projectCreate(@Valid @ModelAttribute("createProject") Projects projects, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("agents", usersService.findAllAgents());
            model.addAttribute("createProject", projects);
            model.addAllAttributes(mapAttributes);
            return "settings/projects/create";
        }
        projects.setLastIssueNumber((long) 0);
        projectService.save(projects);
        return "redirect:/settings/projects";
    }

    @GetMapping("/settings/agents")
    public String agentsSettings() {
        return "settings/agents";
    }

    @GetMapping("/settings/users/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        Users user = usersService.findUserByID(id);
        model.addAttribute("user", user);
        model.addAllAttributes(mapAttributes);
        return "settings/users/edit";
    }

    @PostMapping("/settings/users/edit")
    public String editUser(@ModelAttribute("user") Users user) {
        usersService.save(user);
        return "redirect:/settings/users";
    }

    @GetMapping("/settings/synchronize")
    public String synchronize() {
        usersService.synchronizeUser();
        return "redirect:/settings/users";
    }

}
