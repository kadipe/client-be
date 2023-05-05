package com.kadipe.demo.user.service;

import com.kadipe.demo.user.model.AuthorizationRequestRecord;
import com.kadipe.demo.user.model.LoginRequestRecord;
import com.kadipe.demo.user.exception.InvalidPasswordException;
import com.kadipe.demo.user.exception.UserNotFoundException;
import com.kadipe.demo.user.model.TokenOAuthRecord;
import com.kadipe.demo.user.model.TokenRequest;
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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
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

    @Autowired
    RestTemplate restTemplate;

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

    private String makeLoginOAuth(String idKadipe) throws UserNotFoundException, InvalidPasswordException {

        UserEntity userEntity;
        try {
            userEntity = userRepository.findByKadipeKey(idKadipe).orElseThrow();
        } catch (NoSuchElementException nse) {
            throw new UserNotFoundException("Login invalid, check the email");
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

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedString);
        HttpEntity<TokenRequest> tokenRequest = new HttpEntity<>(new TokenRequest(authorizationRequestRecord.code(), "http://localhost:2001/login.oauth"), headers);
        ResponseEntity<TokenOAuthRecord> responseToken = restTemplate.exchange("http://localhost:5000/oauth/api/v1/token.oauth", HttpMethod.POST, tokenRequest, TokenOAuthRecord.class);
        headers.clear();

        if (responseToken.getBody() != null) {
            headers.add("Authorization", "Bearer " + responseToken.getBody().accessToken());
            HttpEntity<TokenRequest> requestUserInfo = new HttpEntity<>(headers);
            ResponseEntity<Object> responseUserInfo = restTemplate.exchange("http://localhost:5000/oauth/api/v1/user.info", HttpMethod.POST, requestUserInfo, Object.class);
            this.makeLoginOAuth(responseUserInfo.getBody());
        }

    }
}
