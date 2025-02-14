package com.example.springapi.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    private int counter = 0;

    /**
     * Counter increases each time the page is rendered.
     * At render, display a "<counter> hello World!" message.
     * A name parameter can be provided to say "<counter> hello <name>!"
     *
     * @param name  optional to say Hello <name>
     * @param model provide data to the html
     * @return helloworld.html
     */
    @GetMapping("/helloworld")
    public String HelloWorld(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {
        counter++;
        String message = String.format("%dx hello %s!", counter, name);
        model.addAttribute("message", message);
        return "helloworld";
    }
}
