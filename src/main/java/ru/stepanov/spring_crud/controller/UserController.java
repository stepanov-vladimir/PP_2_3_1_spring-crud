package ru.stepanov.spring_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.stepanov.spring_crud.dao.UserDAO;
import ru.stepanov.spring_crud.entity.User;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userDAO.getUsers();
        model.addAttribute("users", users);
        return "list-users";
    }

}
