package com.kadipe.demo.user.client;

import com.kadipe.demo.user.model.CheckinRecord;
import com.kadipe.demo.user.model.TokenOAuthRecord;
import com.kadipe.demo.user.model.TokenRequest;
import com.kadipe.demo.user.model.UserInfoRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kadipeClient", url = "http://localhost:5000/checkin")
public interface WSKadipeCheckinClient {

    @PostMapping(value = "/api/v1/user.info")
//    TokenOAuthRecord getUserInfo(@RequestHeader(name = "Authorization") String accessCode, @RequestBody CheckinRecord checkinRecord);
    TokenOAuthRecord getUserInfo(@RequestBody CheckinRecord checkinRecord);

}
