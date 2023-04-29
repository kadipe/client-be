package com.kadipe.fw.interceptor;

import com.kadipe.fw.util.DateHelp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class TimeZoneInterceptor  implements HandlerInterceptor {

    private final TimeZoneHolder timeZoneHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String timeZone = request.getHeader("X-KDP-TZ");
        timeZoneHolder.setTimeZone((timeZone != null ? timeZone : DateHelp.TZ_LISBON));
        return true;
    }

    public TimeZoneInterceptor(TimeZoneHolder timeZoneHolder) {
        this.timeZoneHolder = timeZoneHolder;
    }
}
