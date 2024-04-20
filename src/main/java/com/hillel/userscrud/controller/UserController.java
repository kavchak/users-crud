package com.hillel.userscrud.controller;

import com.hillel.userscrud.domain.AppUser;
import com.hillel.userscrud.domain.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/add")
    public String userForm(AppUser appUser) {
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUser(@Valid AppUser appUser, BindingResult result) {

        if (result.hasErrors()) {
            return "add-user";
        }

        userRepository.save(appUser);
        return "redirect:/users";

    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {

        Optional<AppUser> appUser = userRepository.findById(id);

        if (appUser.isPresent()) {
            userRepository.delete(appUser.get());
        }
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {

        Optional<AppUser> appUser = userRepository.findById(id);

        if (appUser.isEmpty()) {
            throw new IllegalArgumentException("Invalid user id: " + id);
        }

        model.addAttribute("user", appUser.get());
        return "update-user";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid AppUser appUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            appUser.setId(id);
            return "update-user";
        }

        userRepository.save(appUser);
        return "redirect:/users";
    }
}
