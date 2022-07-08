package co.offcampusjobs.service.impl;

import co.offcampusjobs.dto.UserDto;
import co.offcampusjobs.model.User;
import co.offcampusjobs.repository.UserRepository;
import co.offcampusjobs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * This method saves the new user in the database
     * @param userDto
     * @return
     */
    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = convertUserDtoToEntity(userDto);
        return convertUserEntityToDto(userRepository.save(user));
    }

    /**
     * This method converts user entity to user dto class
     * @param user
     * @return
     */
    private UserDto convertUserEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    /**
     * This method converts dto to entity
     * @param userDto
     * @return
     */
    private User convertUserDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
