package com.rungroop.web.controller;

import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.models.UserEntity;
import com.rungroop.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("users",user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("users")RegistrationDto user,  BindingResult result,Model model){
        UserEntity existingEmail = userService.findByEmail(user.getEmail());
        if (existingEmail != null && existingEmail.getEmail() != null && !existingEmail.getEmail().isEmpty()){

            return "redirect:/register?fail";
        }

        UserEntity existingUsername = userService.findByUsername(user.getUsername());
        if (existingUsername != null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }

        if (result.hasErrors()){
            model.addAttribute("users",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

}
