package ru.stepanov.spring_crud.service;

import ru.stepanov.spring_crud.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();
}
