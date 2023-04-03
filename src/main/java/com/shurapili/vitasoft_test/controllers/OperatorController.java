package com.shurapili.vitasoft_test.controllers;

import com.shurapili.vitasoft_test.models.Application;
import com.shurapili.vitasoft_test.models.Application.Status;
import com.shurapili.vitasoft_test.models.User;
import com.shurapili.vitasoft_test.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('OPERATOR')")
public class OperatorController extends MainController {
    protected ApplicationService applicationService;

    @Autowired
    public OperatorController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/applications")
    public String applications(@RequestParam(name="text", required=false) String text, Model model, @PageableDefault(size = 5, sort = {"creationTime"}) Pageable pageable) {
        Page<Application> page = null;
        if (text == null) {
            page = applicationService.findAllByStatus(Status.SENT, pageable);
            model.addAttribute("url", "/applications?");
            model.addAttribute("text", "");
        } else {
            page = applicationService.findByStatusAndUserUsernameContaining(Status.SENT, text, pageable);
            model.addAttribute("url", "/applications?text=" + text);
            model.addAttribute("text", text);
        }
        model.addAttribute("page", page);
        return "applications";
    }

    @PostMapping("/applications")
    public String find(@ModelAttribute("search") String search, Model model, @PageableDefault(size = 5, sort = {"creationTime"}) Pageable pageable) {
        return "redirect:/applications?text=" + search;
    }

    @GetMapping("/application/{id}/accept")
    public String acceptApplication(@PathVariable(value = "id") Long id, Model model) {
        Application app = applicationService.findByStatusAndId(Status.SENT, id);
        applicationService.updateStatus(app, Status.ACCEPTED);
        return "redirect:/applications";
    }

    @GetMapping("/application/{id}/reject")
    public String rejectApplication(@PathVariable(value = "id") Long id, Model model) {
        Application app = applicationService.findByStatusAndId(Status.SENT, id);
        applicationService.updateStatus(app, Status.REJECTED);
        return "redirect:/applications";
    }

    @GetMapping("/application/{id}")
    public String myApplication(@PathVariable(value = "id") Long id, Model model) {
        Application app = applicationService.findByStatusAndId(Status.SENT, id);
        model.addAttribute("app", app);
        return "applicationDetails";
    }
}
