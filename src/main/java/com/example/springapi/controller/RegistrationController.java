
package com.example.springapi.controller;

import com.example.springapi.model.User;
import com.example.springapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Render register path
     *
     * @return login.html
     */
    @GetMapping("/register")
    public String register() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // check if user is authenticated
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/auth";
        }

        // render register if user is not authenticated
        return "register";
    }

    /**
     * Create user
     *
     * @param username to register
     * @param password to encode
     * @param model    provide data to the html
     * @return
     */
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        User newUser = new User(username, passwordEncoder.encode(password));
        userRepository.save(newUser);

        return "redirect:/login";
    }
}
