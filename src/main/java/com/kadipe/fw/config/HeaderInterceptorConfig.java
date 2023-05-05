package com.kadipe.fw.config;

import com.kadipe.fw.interceptor.TimeZoneHolder;
import com.kadipe.fw.interceptor.TimeZoneInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HeaderInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(timeZoneInterceptor());
    }

    @Bean
    public TimeZoneInterceptor timeZoneInterceptor() {
        return new TimeZoneInterceptor(timeZoneHolder());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TimeZoneHolder timeZoneHolder() {
        return new TimeZoneHolder();
    }
}
