package com.techelevator.dao.users;

import com.techelevator.model.users.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User getTeacherName(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role);

    boolean addRole(Long userId);

    boolean checkRole(Long userId);

    boolean removeTeacherRole(Long userId);

    void deleteUser(long id);
}
