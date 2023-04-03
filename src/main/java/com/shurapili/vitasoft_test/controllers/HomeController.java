package com.shurapili.vitasoft_test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends MainController{

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/hello")
    public String about(Model model) {
        return "hello";
    }
}
