package co.offcampusjobs.business.impl;

import co.offcampusjobs.business.UserBusiness;
import co.offcampusjobs.model.User;
import co.offcampusjobs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBusinessImpl implements UserBusiness {
    @Autowired
    private UserService userService;

    @Override
    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    @Override
    public User getUserByUserName(String username) {
        return userService.getUserByUserName(username);
    }
}
