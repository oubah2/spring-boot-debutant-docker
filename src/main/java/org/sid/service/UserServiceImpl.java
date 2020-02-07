package org.sid.service;

import org.sid.domaine.UserForm;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * User service pour gerer les users et ses roles
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    List<AppUser> listUser = new ArrayList<>();


    /**
     * Save User
     *
     * @param userForm
     * @return AppUser
     */
    @Override
    public AppUser saveUser(UserForm userForm) {

        Optional<AppUser> user = findUserByName(userForm.getUserName());

        if (user.isPresent()) throw new RuntimeException("User Exciste");
        if (!userForm.getPassWord().equals(userForm.getPassWordConfirmed()))
            throw new RuntimeException("passWord nN Confirmed");
        AppUser appUser = new AppUser();
        appUser.setPassWord(bCryptPasswordEncoder.encode(userForm.getPassWord()));
        appUser.setUserName(userForm.getUserName());
        appUser.setActived(true);
        appUser.setId(userForm.getId());


        AppRole roleUser = new AppRole();
        roleUser.setId(1L);
        roleUser.setRoleName("USER");
        appUser.getRoles().add(roleUser);
        listUser.add(appUser);

        return appUser;
    }

    /**
     * find User By name
     *
     * @param userName
     * @return Optional<AppUser>
     */
    @Override
    public Optional<AppUser> findUserByName(String userName) {
        return listUser.stream().filter(s -> s.getUserName().equals(userName)).findFirst();
    }

    /**
     * Initialiser la création des users et ses rôles, cette méthode est applée au démarrage de l'application
     *
     * @return List<AppUser>
     */
    @Override
    public List<AppUser> initData() {

        AppRole roleUser = new AppRole();
        roleUser.setId(1L);
        roleUser.setRoleName("USER");

        AppRole roleAdmin = new AppRole();
        roleAdmin.setId(2L);
        roleAdmin.setRoleName("ADMIN");

        AppUser user1 = new AppUser();
        user1.setId(1L);
        user1.setActived(true);
        user1.setPassWord(bCryptPasswordEncoder.encode("1234"));
        user1.setUserName("user1");
        user1.getRoles().add(roleUser);
        listUser.add(user1);


        AppUser user2 = new AppUser();
        user2.setId(2L);
        user2.setActived(true);
        user2.setPassWord(bCryptPasswordEncoder.encode("1234"));
        user2.setUserName("user2");
        user2.getRoles().add(roleUser);
        listUser.add(user2);

        AppUser user3 = new AppUser();
        user3.setId(3L);
        user3.setActived(true);
        user3.setPassWord(bCryptPasswordEncoder.encode("1234"));
        user3.setUserName("admin1");
        user3.getRoles().add(roleAdmin);
        listUser.add(user3);

        AppUser user4 = new AppUser();
        user4.setId(4L);
        user4.setActived(true);
        user4.setPassWord(bCryptPasswordEncoder.encode("1234"));
        user4.setUserName("admin2");
        user4.getRoles().add(roleAdmin);
        listUser.add(user4);

        return listUser;

    }

    /**
     * find all users
     *
     * @return List<AppUser>
     */
    @Override
    public List<AppUser> findAllUsers() {
        return listUser;
    }

    /**
     * find user by id
     *
     * @param id
     * @return
     */
    @Override
    public Optional<AppUser> findUserByID(long id) {

        return listUser.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    /**
     * UPDATE USER
     *
     * @param id
     * @param userForm
     * @return
     */
    @Override
    public Optional<AppUser> updateUser(long id, UserForm userForm) {
        Optional<AppUser> userModified = findUserByID(id);
        if (userModified.isPresent()) {
            if (userForm.getUserName() != null && !userForm.getUserName().isEmpty()) {
                userModified.get().setUserName(userForm.getUserName());
            }

            if (userForm.getPassWord() != null && userForm.getPassWordConfirmed() != null) {
                if (!userForm.getPassWord().equals(userForm.getPassWordConfirmed())) {
                    throw new RuntimeException("passWord no Confirmed");
                } else {
                    userModified.get().setPassWord((bCryptPasswordEncoder.encode(userForm.getPassWord())));
                }

            }
            return userModified;
        }

        return Optional.empty();


    }

    @Override
    public List<AppUser> deleteUser(long id) {
        Optional<AppUser> userDelete = findUserByID(id);
        if (!userDelete.isPresent()) throw new RuntimeException("USER Inconnu");
        listUser.remove(userDelete.get());
        return listUser;
    }
}

