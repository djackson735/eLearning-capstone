package com.techelevator.controller;

import com.techelevator.dao.users.UserDao;
import com.techelevator.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin

public class UserController {
    
    @Autowired
    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    //TODO May want to include the admin's id in the path here
    @GetMapping("/admin/user-list")
    public List<User> getListOfUsers() {
        return userDao.findAll();
    }

    @PutMapping("/admin/{id}")
    public void promoteToTeacher(@PathVariable long id) {
        userDao.addRole(id);
    }

   @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
   public User getUserById(@PathVariable Long id) {
        return userDao.getUserById(id);
   }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public User getTeacherName(@PathVariable Long id) {
       return userDao.getTeacherName(id);
   }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Boolean checkUserRole(@PathVariable Long id) {
        return userDao.checkRole(id);
    }

   @DeleteMapping("/admin/{id}")
    public void deleteUser(@PathVariable long id) {
        userDao.deleteUser(id);
   }
}