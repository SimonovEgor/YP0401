package com.example.authorizationregistration.controllers;

import com.example.authorizationregistration.models.modelUser;
import com.example.authorizationregistration.models.roleEnum;
import com.example.authorizationregistration.repos.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;

@Controller
@RequestMapping(value = "/registration")
public class UserController {
    @Autowired
    private userRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getReg() {
        return "registration";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postReg(modelUser user, Model model) {
        modelUser _user = userRepository.findByUsername(user.getUsername());
        if (_user != null) {
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(roleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}