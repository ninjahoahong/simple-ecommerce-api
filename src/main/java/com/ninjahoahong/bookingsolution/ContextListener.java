package com.ninjahoahong.bookingsolution;

import com.googlecode.objectify.ObjectifyService;
import com.ninjahoahong.bookingsolution.entities.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ObjectifyService.init();
        ObjectifyService.register(User.class);
    }
}
