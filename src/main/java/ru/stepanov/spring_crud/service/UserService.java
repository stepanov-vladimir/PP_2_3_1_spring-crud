package ru.stepanov.spring_crud.service;

import ru.stepanov.spring_crud.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);
}
