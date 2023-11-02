package com.example.authorizationregistration.controllers;

import com.example.authorizationregistration.models.modelUser;
import com.example.authorizationregistration.repos.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private userRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        Iterable<modelUser> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN, NEWROLE')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") long Id, Model model) {
        modelUser user = userRepository.findById(Id).orElseThrow();
        model.addAttribute("user", user);
        return "/show-user";
    }
    @PostMapping("/users/{id}/delete")
    public void deleteUser(@PathVariable long id) {
        modelUser user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }
    @PostMapping("/users/{id}")
    public String putUser(@RequestParam String username, @PathVariable("id") long Id) {
        modelUser user = userRepository.findById(Id).orElseThrow();
        user.setUsername(username);
        userRepository.save(user);
        return "redirect:/main/users";
    }
    @GetMapping("/main")
    String main_get(){
        return "main";
    }

    @GetMapping("/index")
    String index_get(Model model) {
        Iterable<modelUser> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }

}
