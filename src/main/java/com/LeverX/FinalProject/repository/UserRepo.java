package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.User;

import java.util.List;

public interface UserRepo {
    List allUser();

    void add(User user);

    void delete(User user);

    void edit(User user);

    User findUserByEmailFromSecure(String email);

    User findUserById(int id);

    User findUserByEmail(String email);

    User finByActivationCode(String code);

    void saveUser(User user);

    List<User> getAllTrader();
}
