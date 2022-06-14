package co.offcampusjobs.service.impl;

import co.offcampusjobs.business.UserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserBusiness userBusiness;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new RuntimeException("Username is null");
        }

        co.offcampusjobs.model.User user = userBusiness.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
