package ru.stepanov.spring_crud.controller;

import net.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-form";
    }


}
