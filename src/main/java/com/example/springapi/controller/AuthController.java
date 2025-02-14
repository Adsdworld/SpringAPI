package com.example.springapi.controller;

import com.example.springapi.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    /**
     * This page is only accessible by authenticated users cf SecurityConfig
     *
     * @param model render parameters
     * @return auth.html
     */
    @GetMapping("/auth")
    public String authPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // retrieve the User
        User user = (User) authentication.getPrincipal();

        // pass username to the html
        model.addAttribute("username", "(id=%d) %s".formatted(user.getId(), user.getUsername()));

        return "auth";
    }
}

