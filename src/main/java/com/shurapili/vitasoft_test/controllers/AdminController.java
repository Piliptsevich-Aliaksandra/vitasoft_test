package com.shurapili.vitasoft_test.controllers;

import com.shurapili.vitasoft_test.models.Role;
import com.shurapili.vitasoft_test.models.User;
import com.shurapili.vitasoft_test.repository.UserRepository;
import com.shurapili.vitasoft_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController  extends MainController{
    protected UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(@RequestParam(name="text", required = false) String text, Model model) {
        List<User> users = null;
        if (text == null) {
            users = userService.findAll();
            model.addAttribute("text", "");
        } else {
            users = userService.findByUsernameContaining(text);
            model.addAttribute("text", text);
        }
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users")
    public String find(@ModelAttribute("search") String search, Model model) {
        return "redirect:/users?text=" + search;
    }

    @GetMapping("/user/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        User userEdit = userService.findById(id);
        model.addAttribute("userEdit", userEdit);
        Boolean operator = userEdit.getRoles().contains(Role.OPERATOR);
        model.addAttribute("operatorEdit", operator);
        return "userEdit";
    }

    @PostMapping("/user/{id}")
    public String userChangeOperator(@PathVariable(value = "id") Long id, Model model) {
        User userEdit = userService.findById(id);
        Set<Role> roles = userEdit.getRoles();
        if (roles.contains(Role.OPERATOR)) {
            roles.remove(Role.OPERATOR);
        } else {
            roles.add(Role.OPERATOR);
        }
        userEdit.setRoles(roles);
        userService.save(userEdit);
        return "redirect:/user/" + id;
    }
}
