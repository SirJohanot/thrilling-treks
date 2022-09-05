package com.patiun.thrillingtreks.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signIn(String name, String password) {
        userRepository.save(new User(name, password));
        return true;
    }
}
