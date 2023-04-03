package com.shurapili.vitasoft_test.controllers;

import com.shurapili.vitasoft_test.models.Role;


import com.shurapili.vitasoft_test.models.User;
import com.shurapili.vitasoft_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;


public class MainController {
    @Autowired
    protected UserService userService;

    public String currentUserName(Principal principal) {
        return principal == null ? null : principal.getName();
    }

    @ModelAttribute("user")
    public User getUser(Principal principal) {
        String username = currentUserName(principal);
        if (username == null) {
            return null;
        } else {
            return userService.findByUsername(username);
        }
    }

    @ModelAttribute("client")
    public Boolean getUserStatusClient(Principal principal) {
        String username = currentUserName(principal);
        if (username == null) {
            return null;
        } else {
            return userService.findByUsername(username).getRoles().contains(Role.CLIENT) ? true : null;
        }
    }

    @ModelAttribute("operator")
    public Boolean getUserStatusOperator(Principal principal) {
        String username = currentUserName(principal);
        if (username == null) {
            return null;
        } else {
            return userService.findByUsername(username).getRoles().contains(Role.OPERATOR) ? true : null;
        }
    }

    @ModelAttribute("admin")
    public Boolean getUserStatusAdmin(Principal principal) {
        String username = currentUserName(principal);
        if (username == null) {
            return null;
        } else {
            return userService.findByUsername(username).getRoles().contains(Role.ADMIN) ? true : null;
        }
    }
}
