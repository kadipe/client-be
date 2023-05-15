package com.kadipe.demo.user.controller;

import com.kadipe.demo.user.service.CheckinService;
import com.kadipe.fw.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/checkin")
public class CheckinController extends AbstractController {

    @Autowired
    CheckinService checkinService;

    @GetMapping("${apiprefix.v1}/kadipe/{code}")
    public void getUserInfo(@PathVariable String code) {

        checkinService.callback(code);
    }

}
