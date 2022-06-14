package co.offcampusjobs.business;

import co.offcampusjobs.model.User;

public interface UserBusiness {
    public User saveUser(User user);

    public User getUserByUserName(String username);
}
