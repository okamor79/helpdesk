package com.kaminskyi.helpdesk.controller;

import com.kaminskyi.helpdesk.entity.CustomUserDetails;
import com.kaminskyi.helpdesk.service.impl.UsersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
    private UsersServiceImpl usersService;

    private static Logger logger = LoggerFactory.getLogger(DefaultController.class);

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
    CustomUserDetails userDetails;

    @GetMapping("/")
    public String index(Model model) {
        usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        userDetails = (CustomUserDetails) usernamePasswordAuthenticationToken.getPrincipal();

        usersService.userLogged(userDetails);

        model.addAttribute("userName", userDetails.getFullName());
        model.addAttribute("userFullName", userDetails.getFullName());
//        model.addAttribute("userDepartament", userDetails.getDepartament());
//        model.addAttribute("userPosition", userDetails.getPosition());
//        model.addAttribute("userPhone", userDetails.getPhoneNumber());
//        model.addAttribute("userMail", userDetails.getMail());
        // + " --> " + userDetails.getAccountName()
        //model.addAttribute("userAttr", userDetails.getMail());

//        logger.info("Logged by " + this.userDetails.getAccountName() + " (" + this.userDetails.getTitle() + ")");
        return "index";
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
//        model.addAttribute("userName", userDetails.getFullName());
        model.addAttribute("userFullName", userDetails.getFullName());
        model.addAttribute("userDepartament", userDetails.getDepartament());
        model.addAttribute("userPosition", userDetails.getPosition());
        model.addAttribute("userPhone", userDetails.getPhoneNumber());
        model.addAttribute("userMail", userDetails.getMail());
        return "profile";
    }

    @GetMapping("/synchronize")
    public String synchronize() {
        usersService.synchronizeUser();
        return "redirect:/settings";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("userList", usersService.findAllUsers());
        return "settings";
    }

}
