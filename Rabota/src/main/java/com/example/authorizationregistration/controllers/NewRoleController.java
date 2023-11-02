package com.example.authorizationregistration.controllers;

import com.example.authorizationregistration.models.modelUser;
import com.example.authorizationregistration.models.roleEnum;
import com.example.authorizationregistration.repos.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@PreAuthorize("hasAnyAuthority('NEWROLE')")
public class NewRoleController {
    @Autowired
    private userRepository userRepository;

    @RequestMapping(value = "/add-role", method = RequestMethod.GET)
    public String getAdd(Model model) {
        Iterable<modelUser> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("roles", roleEnum.values());
        return "add-role";
    }

}
