package com.patiun.thrillingtreks.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-in")
    public ModelAndView signIn(@RequestParam String name, @RequestParam String password, Model model) {
        User user = userService.signIn(name, password);
        String username = user.getName();
        model.addAttribute("username", username);
        return new ModelAndView("redirect:/");
    }
}
