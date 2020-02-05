package org.sid.controller;


import org.sid.domaine.UserForm;
import org.sid.entities.AppUser;
import org.sid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * URL /register  save user
     *
     * @param userForm
     * @return AppUser
     */
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm) {
        return userService.saveUser(userForm);

    }

    /**
     * find all users
     *
     * @return List<AppUser>
     */
    @GetMapping("users")
    public List<AppUser> findAllUsers() {
        return userService.findAllUsers();
    }
}
