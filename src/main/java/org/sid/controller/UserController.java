package org.sid.controller;

import org.sid.domaine.UserForm;
import org.sid.entities.AppUser;
import org.sid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/users")
    public List<AppUser> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/user/{id}")

    public Optional<AppUser> findAllUserById(
            @PathVariable("id") long id) {

        return userService.findUserByID(id);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public List<AppUser> deleteUser(@PathVariable("id") long id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PATCH)

    public Optional<AppUser> updateUser(@PathVariable("id") long id, @RequestBody UserForm userForm) {
        return userService.updateUser(id, userForm);
    }

}
