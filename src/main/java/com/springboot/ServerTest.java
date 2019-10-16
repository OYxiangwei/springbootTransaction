package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerTest implements ApplicationRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setUsername("yxw");
        user.setPassword("110");
        userService.save(user);
    }
}
