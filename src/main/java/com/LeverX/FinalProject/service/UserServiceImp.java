package com.LeverX.FinalProject.service;


import com.LeverX.FinalProject.entity.Rating;
import com.LeverX.FinalProject.entity.Role;
import com.LeverX.FinalProject.entity.User;
import com.LeverX.FinalProject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser() {
        return userRepo.allUser();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findUserByEmailFromSecure(email);
    }

    @Override
    public boolean registrationUser(User user) {
        if (userRepo.findUserByEmail(user.getEmail()) == null) {
            Rating rating = ratingService.addRating();

            LocalDate created_date = LocalDate.now();
            String encryptPassword = passwordEncoder.encode(user.getPassword());

            user.setPassword(encryptPassword);
            user.setCreated_at(created_date);
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setRating(rating);
            user.setActivationCode(UUID.randomUUID().toString());

            userRepo.add(user);

            String message = String.format(
                    "Hello, %s! \n" + "Welcome to GameTradePlatform." +
                            " Please, visit next link: http://localhost:8080/activate/%s",
                    user.getEmail(),
                    user.getActivationCode());

            mailService.send(user.getEmail(), "Activation code", message);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findUserById(int id) {
        return userRepo.findUserById(id);
    }

    @Override
    public boolean activateUser(String code) {
        try {
            User user = userRepo.finByActivationCode(code);

            user.setActivationCode(null);
            userRepo.saveUser(user);

            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }
}
