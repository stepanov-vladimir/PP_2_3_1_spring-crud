package ru.stepanov.spring_crud.dao;

import ru.stepanov.spring_crud.entity.User;

import java.util.List;

public interface UserDAO {

    public List<User> getUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);
}
