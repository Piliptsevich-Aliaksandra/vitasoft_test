package com.shurapili.vitasoft_test.repository;

import com.shurapili.vitasoft_test.models.Application;
import com.shurapili.vitasoft_test.models.Application.Status;
import com.shurapili.vitasoft_test.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Page<Application> findAllByStatus(Status status, Pageable pageable);
    Page<Application> findByStatusAndUserUsernameContaining(Status status, String username, Pageable pageable);
    Page<Application> findAllByUser(User user, Pageable pageable);
    Application findByStatusAndId(Status status, Long Id);
    Application findByUserAndId(User user, Long Id);
}
