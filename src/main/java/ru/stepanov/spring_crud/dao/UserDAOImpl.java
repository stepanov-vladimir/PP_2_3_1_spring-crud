package ru.stepanov.spring_crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.stepanov.spring_crud.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }
}
