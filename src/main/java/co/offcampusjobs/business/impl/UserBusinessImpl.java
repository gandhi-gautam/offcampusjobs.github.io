package co.offcampusjobs.business.impl;

import co.offcampusjobs.business.UserBusiness;
import co.offcampusjobs.dto.UserDto;
import co.offcampusjobs.model.User;
import co.offcampusjobs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBusinessImpl implements UserBusiness {
    @Autowired
    private UserService userService;

    /**
     * This method calls the service layer register method to save the user in the database
     * @param userDto
     * @return
     */
    @Override
    public UserDto register(UserDto userDto) {
        return  userService.saveUser(userDto);
    }


}
