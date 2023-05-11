package com.kadipe.demo.user.service;

import com.kadipe.demo.user.model.PersonalInfoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebhookService {

    @Autowired
    UserService userService;

    public void callback(PersonalInfoRecord personalInfoRecord) {

        userService.updateUser(personalInfoRecord);
    }
}
