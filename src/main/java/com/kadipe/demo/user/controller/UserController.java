package com.kadipe.demo.user.controller;

import com.kadipe.demo.user.model.LoginRequestRecord;
import com.kadipe.demo.user.model.LoginResponseRecord;
import com.kadipe.demo.user.service.UserService;
import com.kadipe.fw.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @PostMapping("${apiprefix.v1}/login")
    public LoginResponseRecord makeLogin(@RequestBody LoginRequestRecord loginRequestRecord) {

        return new LoginResponseRecord(userService.makeLogin(loginRequestRecord, timeZoneHolder.getTimeZone()));
    }
}
