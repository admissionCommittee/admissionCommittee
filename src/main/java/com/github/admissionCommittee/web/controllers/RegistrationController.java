package com.github.admissionCommittee.web.controllers;

import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.UserAttendeeState;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@WebServlet({"/registration", "/profile"})
public class RegistrationController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    private UserService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (UserService) config.getServletContext().getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        session.removeAttribute("errProfile");

        User editUser = new User();

        if (session.getAttribute("user_id") != null) {
            User logUser = service.get((long) session.getAttribute("user_id"));
            if (logUser != null) {
                editUser = logUser;
            }
        }

        //Validation user in Hibernate Validator or in Service layer
        String err = "";

        if (request.getParameter("save") != null) {
            editUser.setMail(request.getParameter("regEmail"));
            editUser.setPassword(request.getParameter("regPassword"));
            editUser.setLastName(request.getParameter("regLastName"));
            editUser.setFirstName(request.getParameter("regFirstName"));
            editUser.setPatronymic(request.getParameter("regMiddleName"));
            if (editUser.getUserRole() == null) {
                editUser.setUserRole(UserTypeEnum.USER);
                editUser.setUserAttendeeState(UserAttendeeState.NONPARTICIPANT);
            }
            String birthDate = request.getParameter("regBirthDate");
            if (birthDate != null && !birthDate.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
                LocalDate date = LocalDate.parse(birthDate, formatter);
                editUser.setBirthDate(date);
            }

            // Validation
            // если пытаемся сохранить нового пользователя с email r
            Set<String> errors = new HashSet<>();
            if (editUser.getId() == null && service.getByMail(editUser.getMail()) != null) {
                errors.add("Current mail already exists");
            } else {
                errors = service.save(editUser);
            }

            if (errors.isEmpty()) {
                editUser = service.getByMail(editUser.getMail());
                log.info(String.format("User %s is registered and going to user page", editUser.getMail()));
                session.setAttribute("user_id", editUser.getId());
                session.setAttribute("login", editUser.getMail());

                if (editUser.getUserRole() == UserTypeEnum.ADMIN) {
                    response.sendRedirect("/admin");
                }else{
                    response.sendRedirect("/user");
                }

                return;
            }

            err = errors.toString().replace("[", "").replace("]", "<br>").replace(",", "<br>");
            //errors.stream().forEach(e -> err=err + e);
        }

        // if validation contains errors pass to JSP
        request.getSession().setAttribute("errEditProfile", err);
        request.setAttribute("user", editUser);
        request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
    }
}
