package com.kadipe.demo.user.service;

import com.kadipe.demo.user.client.WSKadipeOAuthClient;
import com.kadipe.demo.user.exception.InvalidPasswordException;
import com.kadipe.demo.user.exception.UserNotFoundException;
import com.kadipe.demo.user.model.*;
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
import java.util.Optional;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    TimeZoneHolder timeZoneHolder;

    @Autowired
    WSKadipeOAuthClient wsKadipeOAuthClient;

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

    private String makeLoginOAuth(UserInfoRecord userInfoRecord) throws UserNotFoundException, InvalidPasswordException {

        UserEntity userEntity = new UserEntity();
        Optional<UserEntity> optUserEntity = userRepository.findByKadipeKey(userInfoRecord.personalInfo().id());
        if (optUserEntity.isEmpty()) {
            userEntity.setEmail(userInfoRecord.listEmail().get(0).email());
            userEntity.setName(userInfoRecord.personalInfo().name());
            userEntity.setKadipeKey(userInfoRecord.personalInfo().id());
            userEntity.setTimeZone(timeZoneHolder.getTimeZone());
            userEntity.setCreateTS(DateHelp.getGMTFromWorld(timeZoneHolder.getTimeZone()));
            userEntity = userRepository.save(userEntity);
        } else {
            userEntity = optUserEntity.get();
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

    public String generateToken(AuthorizationRequestRecord authorizationRequestRecord) {

        String clientID = "9ac7f411-7c40-439a-b794-66ad16476db8";
        String secretKey = "0a4dcf173c03a9e3f6a68c8695059f6e63a72d8fdeb6eeda5493171790692c04";
        String encodedString = Base64.getEncoder().encodeToString((clientID + ":" + secretKey).getBytes());

        TokenOAuthRecord tokenOAuthRecord = wsKadipeOAuthClient.callTokenOAuth("Basic " + encodedString, new TokenRequest(authorizationRequestRecord.code(), "http://localhost:2001/login.oauth"));

        UserInfoRecord userInfoRecord = wsKadipeOAuthClient.callUserInfo("Bearer " + tokenOAuthRecord.accessToken());

        return this.makeLoginOAuth(userInfoRecord);
    }

    public void updateUser(PersonalInfoRecord personalInfoRecord) {

        UserEntity userEntity = userRepository.findByKadipeKey(personalInfoRecord.id()).orElseThrow();
        userEntity.setName(personalInfoRecord.name());
        userRepository.save(userEntity);
    }
}
