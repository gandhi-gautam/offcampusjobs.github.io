package co.offcampusjobs.service.impl;

import co.offcampusjobs.model.User;
import co.offcampusjobs.repository.UserRepository;
import co.offcampusjobs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
