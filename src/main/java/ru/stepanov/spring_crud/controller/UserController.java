package ru.stepanov.spring_crud.controller;

import net.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.stepanov.spring_crud.dao.UserDAO;
import ru.stepanov.spring_crud.entity.User;
import ru.stepanov.spring_crud.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "list-users";
    }

    @PostMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users/list";
    }

    @PutMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user-form";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }

}
