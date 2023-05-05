package com.kadipe.demo.user.client;

import com.kadipe.demo.user.model.TokenOauthRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="TodoClient", url="https://jsonplaceholder.typicode.com")
public interface OAuthClient {

    @PostMapping(value = "/token.ouath")
    TokenOauthRecord generateToken();
}
