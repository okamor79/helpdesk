package com.kaminskyi.helpdesk.controller;

import com.kaminskyi.helpdesk.dto.UsersFilter;
import com.kaminskyi.helpdesk.entity.Users;
import com.kaminskyi.helpdesk.service.ProjectService;
import com.kaminskyi.helpdesk.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/administration")
public class AdministrationController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public String showAllUserList(@ModelAttribute("searchUser") UsersFilter usersFilter, @PageableDefault Pageable pageable, HttpSession session, Model model) {
        Users userLogged = (Users) session.getAttribute("loginedUser");
        model.addAttribute("userRole", userLogged.getRole());
        model.addAttribute("userID", userLogged.getId());
        model.addAttribute("userFullName", userLogged.getFullName());
        Page<Users> usersPage = usersService.findAllUserByPage(pageable);
        model.addAttribute("searchUser", usersFilter == null ? new UsersFilter() : usersFilter);
        int currentPage = usersPage.getNumber();
        int begin = Math.max(1, currentPage - 5);
        int end = Math.min(begin + 4, usersPage.getNumber());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", currentPage);
        model.addAttribute("usersList", usersPage);
        model.addAttribute("usersPageListByPageSize", usersPage.getContent());
        return "administration/users";
    }

    @PostMapping("/user/search")
    public String showFilteredUser(@ModelAttribute("searchUser") UsersFilter usersFilter, @PageableDefault Pageable pageable, HttpSession session, Model model) {
        Users userLogged = (Users) session.getAttribute("loginedUser");
        model.addAttribute("userRole", userLogged.getRole());
        model.addAttribute("userID", userLogged.getId());
        model.addAttribute("userFullName", userLogged.getFullName());
        Page<Users> usersPage = usersService.findFilteredUsersByPage(pageable, usersFilter);
        model.addAttribute("searchUser", usersFilter);
        int begin = Math.max(1, usersPage.getNumber() - 5);
        int end = Math.min(begin + 4, usersPage.getNumber());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", usersPage.getNumber());
        model.addAttribute("usersList", usersPage);
        model.addAttribute("usersPageListByPageSize", usersPage.getContent());
        return "administration/users";
    }

    @GetMapping("/user/remove/{id}")
    public String removeUser(@ModelAttribute("searchUser") UsersFilter usersFilter, @PathVariable("id") Long id, Model model) {
        usersService.removeUser(id);
        model.addAttribute("searchUser", usersFilter);
        return "redirect:/administration/users";
    }

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
