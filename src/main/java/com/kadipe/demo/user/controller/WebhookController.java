package com.kadipe.demo.user.controller;

import com.kadipe.demo.user.model.PersonalInfoRecord;
import com.kadipe.demo.user.service.WebhookService;
import com.kadipe.fw.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/webhook")
public class WebhookController extends AbstractController {

    @Autowired
    WebhookService webhookService;

    @PostMapping("${apiprefix.v1}/kadipe")
    public void makeLogin(@RequestBody PersonalInfoRecord personalInfoRecord) {

        webhookService.callback(personalInfoRecord);
    }

}
