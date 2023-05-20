package com.kadipe.demo.user.client;

import com.kadipe.demo.user.model.CheckinRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kadipeCheckinClient", url = "http://localhost:5000/checkin")
public interface WSKadipeCheckinClient {

    @PostMapping(value = "/api/v1/user.info")
    void getUserInfo(@RequestBody CheckinRequest checkinRequest);

}
