package com.github.admissionCommittee.web.controllers;

import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.FacultyService;
import com.github.admissionCommittee.service.SchoolCertificateService;
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

@WebServlet({"/user"})
public class UserController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private SchoolCertificateService certificateService;
    private FacultyService facultyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        certificateService = (SchoolCertificateService) config.getServletContext().getAttribute("certificateService");
        facultyService = (FacultyService) config.getServletContext().getAttribute("facultyService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        User user = userService.get((long) session.getAttribute("user_id"));
        SchoolCertificate certificate = user.getSchoolCertificate();
        final Sheet sheet = user.getSheet();

        if (user == null) {
            log.trace(String.format("User with id %s not found. Redirect to error page", session.getAttribute("user_id")));
            response.sendRedirect("/error404");
        }

        request.setAttribute("user", user);
        request.setAttribute("certificate", certificate);
        request.setAttribute("sheet", sheet);
        request.setAttribute("listFaculty", facultyService.getAll());
        request.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);
    }
}
