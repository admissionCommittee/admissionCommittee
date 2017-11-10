package com.github.admissionCommittee.web.controllers;

import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.UserService;
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
import java.util.ResourceBundle;

/**
 * The first web servlet who mapped on root domain /
 * <p>
 * Works on Login to the system, logout and registration
 * By default anonymous user redirect to index.jsp
 */

@WebServlet({"", "/login", "/logout", "/registration"})
public class LoginController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private UserService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (UserService) config.getServletContext().getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        setLocale(request, session);
        session.removeAttribute("errLogin");


        // Logout - clearing all session data
        if ("/logout".equalsIgnoreCase(request.getServletPath())) {
            log.debug("Logout user session");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", 0);
            session.invalidate();
            response.sendRedirect("/");
            return;
        }

        // Login - check user login and password hash
        if ("/login".equalsIgnoreCase(request.getServletPath())) {
            User tmpUser = new User();
            tmpUser.setMail(request.getParameter("login_email"));
            //tmpUser.setPassword(request.getParameter("password"));
            log.debug(String.format("Try to login with login: %s", tmpUser.getMail()));


            User logUser = service.getByMail(tmpUser.getMail());

            if (logUser != null) {
                log.debug(String.format("!!! User with login: %s was founded", logUser.getMail()));
                if (tmpUser.getPassword().equals(logUser.getPassword())) {
                    log.info(String.format("User %s logged and going to user page", logUser.getMail()));
                    session.setAttribute("user_id", logUser.getId());
                    session.setAttribute("login", logUser.getMail());
                    response.sendRedirect("/user");
                    return;
                }
            }

            // Если попытка входа была неудачной - кидаем в сессию сообщение об ошибке
            //ToDo разобраться почему не подхватывается локализация
            ResourceBundle bundle = ResourceBundle.getBundle("localization");
            session.setAttribute("errLogin", bundle.getString("errLogin"));
            //request.setAttribute("user", tmpUser);
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doPost(request, response);
    }


    /**
     * If user change localization then we save it in the session
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
