package com.shurapili.vitasoft_test.service;

import com.shurapili.vitasoft_test.models.Application.Status;
import com.shurapili.vitasoft_test.models.Application;
import com.shurapili.vitasoft_test.models.User;
import com.shurapili.vitasoft_test.repository.ApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application findByUserAndId(User user, Long id) {
        return applicationRepository.findByUserAndId(user, id);
    }

    public Page<Application> findByStatusAndUserUsernameContaining(Status status, String username, Pageable pageable) {
        return applicationRepository.findByStatusAndUserUsernameContaining(status, username, pageable);
    }

    public Page<Application> findAllByStatus(Status status, Pageable pageable) {
        return applicationRepository.findAllByStatus(Status.SENT, pageable);
    }

    public Application findById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public Page<Application> findAllByUser(User user, Pageable pageable) {
        return applicationRepository.findAllByUser(user, pageable);
    }

    public void update(Application app, Application appForm) {
        app.setText(appForm.getText());
        applicationRepository.save(app);
    }

    public void register(Application appForm, User user) {
        Application app = new Application(appForm.getText());
        app.setUser(user);
        user.addApplication(app);
        applicationRepository.save(app);
    }

    public void updateStatus(Application app, Status status) {
        app.setStatus(status);
        applicationRepository.save(app);
    }

    public Application findByStatusAndId(Status status, Long Id) {
        return applicationRepository.findByStatusAndId(status, Id);
    }
}
