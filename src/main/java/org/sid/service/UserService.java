package org.sid.service;

import org.sid.domaine.UserForm;
import org.sid.entities.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public AppUser saveUser(UserForm userForm);


    public Optional<AppUser> findUserByName(String userName);

    public List<AppUser> initData();

    public List<AppUser> findAllUsers();

    public Optional<AppUser> findUserByID(long id);

    public Optional<AppUser> updateUser(long id, UserForm userForm);

    public List<AppUser> deleteUser(long id);
}
