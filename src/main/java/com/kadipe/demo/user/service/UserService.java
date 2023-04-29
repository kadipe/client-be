package com.kadipe.demo.user.service;

import com.kadipe.demo.user.model.LoginRecord;
import com.kadipe.demo.user.exception.InvalidPasswordException;
import com.kadipe.demo.user.exception.UserNotFoundException;
import com.kadipe.demo.user.repository.LoginEntity;
import com.kadipe.demo.user.repository.LoginRepository;
import com.kadipe.demo.user.repository.UserEntity;
import com.kadipe.demo.user.repository.UserRepository;
import com.kadipe.fw.util.DateHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginRepository loginRepository;

    public String makeLogin(LoginRecord loginRecord, String timeZone) throws UserNotFoundException, InvalidPasswordException {

        UserEntity userEntity;
        try {
            userEntity = userRepository.findByEmail(loginRecord.userName()).orElseThrow();
        } catch (NoSuchElementException nse) {
            throw new UserNotFoundException("Login invalid, check the email");
        }
        if (!loginRecord.password().equals(userEntity.getPassword())) {
            throw new InvalidPasswordException("Invalid password. Check the correct.");
        }

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUserEntity(userEntity);
        loginEntity.setTimeZone(timeZone);
        loginEntity.setCreateTS(DateHelp.getGMTFromWorld(timeZone));
        loginRepository.save(loginEntity);

        return userEntity.getName();
    }
}
