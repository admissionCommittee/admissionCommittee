package com.github.admissionCommittee.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The first web servlet who mapped on root domain /
 * <p>
 * Works on Login to the system, logout and registration
 * By default anonymous user redirect to index.jsp
 */

@WebServlet({"", "/login", "/logout", "/registration"})
public class LoginController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        // ToDo при инициализации запросить слой сервисов

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        setLocale(request, session);

        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    /**
     *
     * @param request
     * @param session
     */
    private void setLocale(HttpServletRequest request, HttpSession session) {
        if (request.getParameter("lang") != null) {
            switch (request.getParameter("lang")) {
                case "ru": {
                    log.debug("Set default locale to russian");
                    session.setAttribute("lang", "ru");
                    break;
                }
                case "en": {
                    log.debug("set default locale to english");
                    session.setAttribute("lang", "en");
                    break;
                }
                default: {
                    log.debug("set locale by default");
                    session.setAttribute("lang", "ru");
                }
            }
        }
    }
}
