package co.offcampusjobs.controller;

import co.offcampusjobs.business.UserBusiness;
import co.offcampusjobs.dto.UserDto;
import co.offcampusjobs.exception.UserNotSavedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RegisterController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private UserBusiness userBusiness;

    /**
     * This method lets user register themselves, it need user data to save in the database
     * @param userDto
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        if(userDto == null) {
            log.error("User Data not recieved");
            throw new UserNotSavedException("User Not Found");
        }
        UserDto registerUser = userBusiness.register(userDto);
        if(registerUser == null){
            log.error(userDto.getName() + "not able to register");
            throw new UserNotSavedException("User Not registered");
        }
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
