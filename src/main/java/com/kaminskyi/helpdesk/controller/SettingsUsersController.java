package com.kaminskyi.helpdesk.controller;

import com.kaminskyi.helpdesk.dto.UsersFilter;
import com.kaminskyi.helpdesk.entity.Users;
import com.kaminskyi.helpdesk.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
@SessionAttributes
public class SettingsUsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping({"/", "/info"})
    public String indexSettings() {
        return "settings/info";
    }

    @GetMapping("/users")
    public String usersSettings(Model model, @PageableDefault Pageable pageable) {
        Page<Users> usersPage = usersService.findAllUserByPage(pageable);
        int currentPage = usersPage.getNumber();
        int begin = Math.max(1, currentPage - 5);
        int end = Math.min(begin + 5, usersPage.getNumber());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", currentPage);
        model.addAttribute("usersList", usersPage);
        model.addAttribute("usersPageListByPageSize", usersPage.getContent());
        model.addAttribute("searchText", new UsersFilter());
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

    @GetMapping("/users/remove/{id}")
    public String removeUser(Model model, @PathVariable("id") Long id) {
        usersService.removeUser(id);
        return "redirect:/settings/users";
    }

    @PostMapping("/users/search")
    public String userFind(
            @ModelAttribute("searchText") UsersFilter filter,
            @PageableDefault Pageable pageable,
            Model model) {

        Page<Users> page = usersService.findFilteredUsersByPage(pageable, filter);
        int currentPage = page.getNumber();
        int begin = Math.max(1, currentPage - 5);
        int end = Math.min(begin + 5, page.getNumber());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", currentPage);
        model.addAttribute("usersList", page);
        model.addAttribute("usersPageListByPageSize", page.getContent());
        model.addAttribute("searchText", filter);
        return "settings/users";
    }
}


