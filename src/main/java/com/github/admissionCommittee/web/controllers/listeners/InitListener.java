package com.github.admissionCommittee.web.controllers.listeners;

import com.github.admissionCommittee.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class InitListener implements ServletContextListener{


        /**
         * First initialization web-method
         *
         * Injection Service layer in servlet context
         * @param sce - standard ServletContextEvent event
         */
        @Override
        public void contextInitialized(ServletContextEvent sce) {
            log.info("Start Servlet initialization");

            // Service layer injection
            //ToDo инициализация базы
            UserService userService = new UserService();
            sce.getServletContext().setAttribute("userService", userService);
            log.info("End Servlet initialization");
        }
}
