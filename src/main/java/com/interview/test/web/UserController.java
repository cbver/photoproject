package com.interview.test.web;

import com.interview.test.entity.User;
import com.interview.test.service.SecurityService;
import com.interview.test.service.UserService;
import com.interview.test.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
/**
 * @author  Chandra Bhushan Verma
 * User Controle REST endpoint with Spring Security and Spring  MVC
 */

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {

        logger.info("Calling registrations ...");

        model.addAttribute("userForm", new User());

        logger.info("registrations completed...");

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        logger.info("Calling registrations ...");

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.error("Registrations error with {} ...",bindingResult.getFieldError());
            return "registration";
        }
        logger.info("Saving registrations with users details...");
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        logger.info("Login  endpoints called with ...");
        if (error != null) {
            logger.error("Your username and password is invalid");
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null) {
            logger.info("logged out successfully");
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}