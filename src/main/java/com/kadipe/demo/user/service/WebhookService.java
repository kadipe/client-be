package com.kadipe.demo.user.service;

import com.kadipe.demo.user.model.PersonalInfoRecord;
import org.springframework.stereotype.Service;

@Service
public class WebhookService {

    public void callback(PersonalInfoRecord personalInfoRecord) {

        System.out.println(personalInfoRecord.name());
    }
}
