package com.kaminskyi.helpdesk.controller;

import com.kaminskyi.helpdesk.dto.UsersFilter;
import com.kaminskyi.helpdesk.entity.CustomUserDetails;
import com.kaminskyi.helpdesk.entity.Users;
import com.kaminskyi.helpdesk.entity.enums.UserRole;
import com.kaminskyi.helpdesk.service.impl.UsersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
    private UsersServiceImpl usersService;

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
        mapAttributes.put("userName", userDetails.getFullName());
        mapAttributes.put("userFullName", userDetails.getFullName());
        mapAttributes.put("userLogin", userDetails.getLogin());
        mapAttributes.put("userDepartment", userDetails.getDepartament());
        mapAttributes.put("userPosition", userDetails.getPosition());
        mapAttributes.put("userPhone", userDetails.getPhoneNumber());
        mapAttributes.put("userRole", usersService.findUserByLogin(userDetails.getLogin()).getRole());
        model.addAllAttributes(mapAttributes);
        return "index";
    }

    @GetMapping({"/settings", "/settings/info"})
    public String indexSettings(Model model) {
        model.addAllAttributes(mapAttributes);
        return "settings/info";
    }

    @GetMapping("/settings/users")
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
        model.addAllAttributes(mapAttributes);
        return "settings/users";
    }

    @GetMapping("/settings/projects")
    public String projectsSettings(Model model) {
        model.addAllAttributes(mapAttributes);
        return "settings/projects";
    }

    @GetMapping("/setting/projects/create")
    public String projectCreate(Model model) {
        return "settings/projectcreate";
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
        System.out.println(user);
        usersService.save(user);
        return "redirect:/settings/users";
    }

    @GetMapping("/settings/users/remove/{id}")
    public String removeUser(Model model, @PathVariable("id") Long id) {
        usersService.removeUser(id);
        return "redirect:/settings/users";
    }

    @PostMapping("/settings/users/search")
    public String userFind(@ModelAttribute("searchText") UsersFilter filter, @PageableDefault Pageable pageable, Model model) {
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
        model.addAllAttributes(mapAttributes);
        return "settings/users";
    }

    @GetMapping("/settings/synchronize")
    public String synchronize() {
        usersService.synchronizeUser();
        return "redirect:/settings/users";
    }


}
