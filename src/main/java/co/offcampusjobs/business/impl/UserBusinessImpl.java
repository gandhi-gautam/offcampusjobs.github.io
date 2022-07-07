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


    @Override
    public UserDto register(UserDto userDto) {
        User user = convertUserDtoToEntity(userDto);
        user = userService.saveUser(user);
        return convertUserEntityToDto(user);
    }

    private UserDto convertUserEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    private User convertUserDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
