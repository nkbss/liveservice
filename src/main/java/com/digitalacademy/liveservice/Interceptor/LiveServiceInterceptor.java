package com.digitalacademy.liveservice.Interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LiveServiceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws
            Exception {
        log.info("Live Interceptor: preHandle");
        return true;
    }

    private static final Logger log = LogManager.getLogger(LiveServiceInterceptor.class.getName());
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView)
            throws Exception {
        log.info("Live Interceptor: postHandle");
    }
}
