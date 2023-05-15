package com.kadipe.demo.user.service;

import com.kadipe.demo.user.client.WSKadipeCheckinClient;
import com.kadipe.demo.user.model.CheckinRecord;
import com.kadipe.demo.user.model.PersonalInfoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckinService {

    @Autowired
    WSKadipeCheckinClient wsKadipeCheckinClient;

    public void callback(String code) {

        wsKadipeCheckinClient.getUserInfo(new CheckinRecord(code));
    }
}
