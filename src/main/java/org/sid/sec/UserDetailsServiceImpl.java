package org.sid.sec;

import org.sid.entities.AppUser;
import org.sid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * load User ByU sername in Authentication
     *
     * @param userName
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String userName) {
        Optional<AppUser> appUser = userService.findUserByName(userName);
        System.out.println(appUser.get());
        if (appUser == null) throw new UsernameNotFoundException("Invalid Name");
        Collection<GrantedAuthority> authorities = new ArrayList();
        appUser.get().getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });

        return new User(appUser.get().getUserName(), appUser.get().getPassWord(), authorities);
    }
}
