package com.example.springIt.service;

import com.example.springIt.domain.Users;
import com.example.springIt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users register(Users user){
        return user;
    }

    public Users save(Users user){
        return userRepository.save(user);
    }

    @Transactional
    public void saveUsers(Users... users){
        for(Users user : users){
            logger.info("Saving user: " + user.getEmail());
            userRepository.save(user);
        }
    }
}
