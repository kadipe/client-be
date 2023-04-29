package com.kadipe.fw.controller;

import com.kadipe.fw.interceptor.TimeZoneHolder;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {

    @Autowired
    protected TimeZoneHolder timeZoneHolder;

}
