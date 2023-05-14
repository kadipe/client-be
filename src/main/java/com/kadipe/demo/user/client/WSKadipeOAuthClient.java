package com.kadipe.demo.user.client;

import com.kadipe.demo.user.model.TokenOAuthRecord;
import com.kadipe.demo.user.model.TokenRequest;
import com.kadipe.demo.user.model.UserInfoRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kadipeClient", url = "http://localhost:5000/oauth")
public interface WSKadipeOAuthClient {

    @PostMapping(value = "/api/v1/token.oauth")
    TokenOAuthRecord callTokenOAuth(@RequestHeader(name = "Authorization") String accessCode, @RequestBody TokenRequest tokenRequest);

    @GetMapping(value = "/api/v1/user.info")
    UserInfoRecord callUserInfo(@RequestHeader(name = "Authorization") String accessCode);

}
