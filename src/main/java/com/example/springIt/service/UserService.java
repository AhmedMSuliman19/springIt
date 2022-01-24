package com.example.springIt.service;

import com.example.springIt.domain.Users;
import com.example.springIt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RolesService rolesService;
    private final MailService mailService;

    public UserService(UserRepository userRepository, RolesService rolesService, MailService mailService) {
        this.userRepository = userRepository;
        this.rolesService = rolesService;
        this.mailService = mailService;
        encoder = new BCryptPasswordEncoder();
    }

    public Users register(Users user){
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);
        user.setConfirmPassword(secret);
        user.addRole(rolesService.findByName("ROLE_USER"));
        user.setActivationCode(UUID.randomUUID().toString());
        save(user);
        sendActivationEmail(user);

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

    public Optional<Users> findById(Long id){
        return userRepository.findById(id);
    }

    public Optional<Users> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void sendActivationEmail(Users user){
        mailService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(Users user){
        mailService.sendWelcomeEmail(user);
    }

    public Optional<Users> findByEmailAndActivationCode(String email, String activationCode){
        return userRepository.findByEmailAndActivationCode(email, activationCode);
    }
}
