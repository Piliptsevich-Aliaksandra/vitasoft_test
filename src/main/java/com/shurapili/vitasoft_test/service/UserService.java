package com.shurapili.vitasoft_test.service;

import com.shurapili.vitasoft_test.models.Application;
import com.shurapili.vitasoft_test.models.Application.Status;
import com.shurapili.vitasoft_test.models.User;
import com.shurapili.vitasoft_test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findByUsernameContaining(String username) {
        return userRepository.findByUsernameContaining(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
