package com.github.admissionCommittee.web.controllers.listeners;

import com.github.admissionCommittee.dao.UserDao;
import com.github.admissionCommittee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class InitListener implements ServletContextListener{
        private static final Logger log = LoggerFactory.getLogger(InitListener.class);

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
            //ToDo добавить параметр экземпляр DAO вместо null
            UserService userService = new UserService(new UserDao());
            sce.getServletContext().setAttribute("userService", userService);
            log.info("End Servlet initialization");
        }
}
