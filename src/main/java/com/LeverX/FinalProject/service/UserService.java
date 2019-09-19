package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUser();

    UserDetails loadUserByUsername(String email);

    boolean registrationUser(User user);

    User findUserById(int id);

    boolean activateUser(String code);

    void rememberPassword(String email);

    User findUserByCode(String code);

    void createNewPassword(User user);
}
