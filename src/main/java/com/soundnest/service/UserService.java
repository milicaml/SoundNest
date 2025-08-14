package com.soundnest.service;


import com.soundnest.model.User;
import com.soundnest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> register(String username, String password, String name) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            throw new RuntimeException("User already exists"); // TODO : Handle better
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);

        userRepository.save(user);
        return Optional.of(user);
    }


}