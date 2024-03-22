package com.fmsia2.user.service.services.impls;

import com.fmsia2.user.service.entities.User;
import com.fmsia2.user.service.entities.UserAuth;
import com.fmsia2.user.service.repositories.UserAuthRepository;
import com.fmsia2.user.service.services.UserAuthService;
import com.fmsia2.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAuthServiceImplements implements UserAuthService {
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private UserService userService;
    @Override
    public UserAuth saveUser(UserAuth userauth){
        String randomUserId = UUID.randomUUID().toString();
        userauth.setUserId(randomUserId);
        User user = new User(userauth.getUserId(),userauth.getName(),userauth.getEmail(),userauth.getAbout());
        user = userService.saveUser(user);
        return userAuthRepository.save(userauth);
    }


}
