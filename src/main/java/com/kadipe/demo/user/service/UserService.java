package com.kadipe.demo.user.service;

import com.kadipe.demo.user.model.AuthorizationRequestRecord;
import com.kadipe.demo.user.model.LoginRequestRecord;
import com.kadipe.demo.user.exception.InvalidPasswordException;
import com.kadipe.demo.user.exception.UserNotFoundException;
import com.kadipe.demo.user.repository.LoginEntity;
import com.kadipe.demo.user.repository.LoginRepository;
import com.kadipe.demo.user.repository.UserEntity;
import com.kadipe.demo.user.repository.UserRepository;
import com.kadipe.fw.interceptor.TimeZoneHolder;
import com.kadipe.fw.util.DateHelp;
import com.kadipe.fw.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    TimeZoneHolder timeZoneHolder;

    public String makeLogin(LoginRequestRecord loginRequestRecord) throws UserNotFoundException, InvalidPasswordException {

        UserEntity userEntity;
        try {
            userEntity = userRepository.findByEmail(loginRequestRecord.userName()).orElseThrow();
        } catch (NoSuchElementException nse) {
            throw new UserNotFoundException("Login invalid, check the email");
        }
        if (!loginRequestRecord.password().equals(userEntity.getPassword())) {
            throw new InvalidPasswordException("Invalid password. Check the correct.");
        }

        saveLogin(timeZoneHolder.getTimeZone(), userEntity);

        return SecurityUtil.generateJWT(userEntity.getEmail(), userEntity.getId(), "Kadipe :: Client Demo");
    }

    private void saveLogin(String timeZone, UserEntity userEntity) {

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUserEntity(userEntity);
        loginEntity.setTimeZone(timeZone);
        loginEntity.setCreateTS(DateHelp.getGMTFromWorld(timeZone));
        loginRepository.save(loginEntity);
    }

    public void generateToken(AuthorizationRequestRecord authorizationRequestRecord) {

        String clientID = "";
        String secretKey = "";

        String encodedString = Base64.getEncoder().encodeToString((clientID + ":" + secretKey).getBytes());

        System.out.println(timeZoneHolder.getTimeZone());
    }
}
