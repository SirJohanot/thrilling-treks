package com.patiun.thrillingtreks.user;

import com.patiun.thrillingtreks.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto, HttpServletRequest request, Model model) throws ServletException {
        try {
            userService.signUp(userRegistrationDto);
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "signUp";
        }
        String name = userRegistrationDto.getName();
        String password = userRegistrationDto.getPassword();
        request.logout();
        request.login(name, password);
        return "redirect:/";
    }

    @GetMapping("/sign-up-page")
    public String signUpPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "signUp";
    }
}
