package com.github.admissionCommittee.web.listeners;

import com.github.admissionCommittee.service.*;
import com.github.admissionCommittee.util.init.DBInitializerUtil;
import org.h2.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


@WebListener
public class InitListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(InitListener.class);

    /**
     * First initialization web-method
     * <p>
     * Injection Service layer in servlet context
     *
     * @param sce - standard ServletContextEvent event
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Start Servlet initialization");

        final DBInitializerUtil dbInitializerUtil = new DBInitializerUtil();
        dbInitializerUtil.init(16403, "", "");

        // Service layer injection
        sce.getServletContext().setAttribute("userService", new UserService());
        sce.getServletContext().setAttribute("certificateService", new SchoolCertificateService());
        sce.getServletContext().setAttribute("facultyService", new FacultyService());
        sce.getServletContext().setAttribute("sheetService", new SheetService());
        sce.getServletContext().setAttribute("subjectService", new SubjectService());
        sce.getServletContext().setAttribute("examService", new ExamCertificateService());

        log.info("End Servlet initialization");
    }
}
