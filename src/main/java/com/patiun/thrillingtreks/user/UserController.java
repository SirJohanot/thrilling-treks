package com.patiun.thrillingtreks.user;

import com.patiun.thrillingtreks.exception.ServiceException;
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
    public String signUp(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto, final Model model, final HttpServletRequest request) throws ServletException {
        try {
            userService.signUp(userRegistrationDto);
        } catch (ServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "signUp";
        }
        String email = userRegistrationDto.getEmail();
        String password = userRegistrationDto.getPassword();

        request.login(email, password);
        return "redirect:/";
    }

    @PostMapping("/edit-profile")
    public String editProfile(@ModelAttribute("user") @Valid UserEditProfileDto userEditProfileDto, final HttpServletRequest request, final Model model) throws ServletException {
        User currentUser = (User) request.getUserPrincipal();
        Long currentUserId = currentUser.getId();
        try {
            userService.editProfile(currentUserId, userEditProfileDto);
        } catch (ServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "editProfilePage";
        }
        String email = userEditProfileDto.getEmail();
        String password = currentUser.getPassword();

        request.login(email, password);
        return "redirect:/user-profile";
    }

    @GetMapping("/sign-up-page")
    public String signUpPage(final Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "signUp";
    }

}
