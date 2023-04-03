package com.shurapili.vitasoft_test.controllers;

import com.shurapili.vitasoft_test.models.Application;
import com.shurapili.vitasoft_test.models.Application.Status;
import com.shurapili.vitasoft_test.models.User;
import com.shurapili.vitasoft_test.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('CLIENT')")
public class ClientController extends MainController {
    protected ApplicationService applicationService;

    @Autowired
    public ClientController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/myApplications")
    public String myApplications(Model model, @PageableDefault(size = 5, sort = { "creationTime" }) Pageable pageable) {
        Page<Application> page = applicationService.findAllByUser((User) model.getAttribute("user"), pageable);
        model.addAttribute("url", "/myApplications?");
        model.addAttribute("page", page);
        return "myApplications";
    }

    @GetMapping("/myApplication/{id}/send")
    public String sendApplication(@PathVariable(value = "id") Long id, Model model) {
        Application app = applicationService.findById(id);
        applicationService.updateStatus(app, Status.SENT);
        return "redirect:/myApplication/" + id;
    }

    @GetMapping("/myApplication/{id}/edit")
    public String editApplication(@PathVariable(value = "id") Long id, Model model) {
        Application app = applicationService.findByStatusAndId(Status.DRAFT, id);
        Application appForm = new Application(app.getText());
        model.addAttribute("appForm", appForm);
        return "applicationEdit";
    }

    @PostMapping("/myApplication/{id}/edit")
    public String updateApplication(@PathVariable(value = "id") Long id, @Valid @ModelAttribute("appForm") Application appForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("appForm", appForm);
            return "applicationEdit";
        }
        Application app = applicationService.findByStatusAndId(Status.DRAFT, id);
        applicationService.update(app, appForm);
        return "redirect:/myApplication/" + id;
    }

    @GetMapping("/createApplication")
    public String createApplication(Model model) {
        Application appForm = new Application();
        model.addAttribute("appForm", appForm);
        return "applicationCreate";
    }

    @PostMapping("/createApplication")
    public String saveApplication(@Valid @ModelAttribute("appForm") Application appForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("appForm", appForm);
            return "applicationCreate";
        }
        applicationService.register(appForm, (User)model.getAttribute("user"));
        return "redirect:/myApplications";
    }

    @GetMapping("/myApplication/{id}")
    public String myApplication(@PathVariable(value = "id") Long id, Model model) {
        Application app = applicationService.findByUserAndId((User)model.getAttribute("user"), id);
        model.addAttribute("app", app);
        return "myApplicationDetails";
    }
}
