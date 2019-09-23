package com.LeverX.FinalProject.controller;

import com.LeverX.FinalProject.entity.User;
import com.LeverX.FinalProject.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@Valid User user, BindingResult bindingResult) throws JsonProcessingException {
        String message;

        if (user.getPassword().equals(null) || !user.getPassword().equals(user.getPassword2())) {
            message = objectMapper.writeValueAsString("Passwords are different!");
            return ResponseEntity.ok(message);
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            message = objectMapper.writeValueAsString(errors);
            return ResponseEntity.ok(message);
        }

        if (!userService.registrationUser(user)) {
            message = objectMapper.writeValueAsString("User exists!");
            return ResponseEntity.ok(message);
        }

        message = objectMapper.writeValueAsString("User created!");
        return ResponseEntity.ok(message);
    }

    @RequestMapping(value = "/activate/{code}", method = RequestMethod.GET)
    public ResponseEntity<String> activate(@PathVariable String code) throws JsonProcessingException {
        boolean isActivated = userService.activateUser(code);
        String message;

        if (isActivated) {
            message = objectMapper.writeValueAsString("User successfully activated");
        } else {
            message = objectMapper.writeValueAsString("Activation code is not found!");
        }

        return ResponseEntity.ok(message);
    }

    @RequestMapping(value = "/remember", method = RequestMethod.GET)
    public ResponseEntity<String> rememberPassword(@RequestParam String email) throws JsonProcessingException {
        userService.rememberPassword(email);

        String message = objectMapper.writeValueAsString("Check your email!");
        return ResponseEntity.ok(message);
    }

    @RequestMapping(value = "/newPassword/{code}", method = RequestMethod.GET)
    public ResponseEntity<String> userForgotPassword(@PathVariable String code) throws JsonProcessingException {
        User user = userService.findUserByCode(code);

        String message = objectMapper.writeValueAsString(user);
        return ResponseEntity.ok(message);
    }

    @RequestMapping(value = "/newPassword", method = RequestMethod.PUT)
    public ResponseEntity<String> createNewPassword(@Valid User user, BindingResult bindingResult) throws JsonProcessingException {
        String message;

        if (user.getPassword().equals(null) || !user.getPassword().equals(user.getPassword2())) {
            message = objectMapper.writeValueAsString("Passwords are different!");
            return ResponseEntity.ok(message);
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            message = objectMapper.writeValueAsString(errors);
            return ResponseEntity.ok(message);
        }

        userService.createNewPassword(user);
        message = objectMapper.writeValueAsString("Password has been updated");
        return ResponseEntity.ok(message);
    }
}
