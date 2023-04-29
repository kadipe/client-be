package com.kadipe.demo.user.controller;

import com.kadipe.demo.user.model.LoginRecord;
import com.kadipe.demo.user.service.UserService;
import com.kadipe.fw.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @PostMapping("${apiprefix.v1}/login")
    public String makeLogin(@RequestBody LoginRecord loginRecord) {

        return userService.makeLogin(loginRecord, timeZoneHolder.getTimeZone());
    }
}
