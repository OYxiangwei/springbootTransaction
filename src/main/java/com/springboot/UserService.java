package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //@Transactional(rollbackFor = Exception.class)
    public User save(User user){
        userRepository.save(user);
        int yxw = 1 / 0;
        user.setPassword("123456");
        return userRepository.save(user);
    }
}
