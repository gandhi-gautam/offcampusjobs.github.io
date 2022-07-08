package co.offcampusjobs.service;

import co.offcampusjobs.dto.UserDto;
import co.offcampusjobs.model.User;

public interface UserService {
    UserDto saveUser(UserDto userDto);
}
