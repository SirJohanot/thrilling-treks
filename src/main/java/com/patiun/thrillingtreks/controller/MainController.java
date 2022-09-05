package com.patiun.thrillingtreks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/campaigns")
    public String campaignsPage() {
        return "campaigns";
    }

    @GetMapping("/campaign-creator")
    public String campaignCreatorPage() {
        return "campaignCreator";
    }

    @GetMapping("/sign-in-page")
    public String signInPage() {
        return "signIn";
    }
}
