package com.github.admissionCommittee.web.controllers;

import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.FacultyService;
import com.github.admissionCommittee.service.SheetService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/admin"})
public class AdminController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private UserService userService;
    private FacultyService facultyService;
    private SheetService sheetService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        facultyService = (FacultyService) config.getServletContext().getAttribute("facultyService");
        sheetService = (SheetService) config.getServletContext().getAttribute("sheetService");
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

        if (request.getParameter("faculty") != null) {
            List<Sheet> sheet = sheetService.getByFaculty(facultyService.get(Integer.parseInt(request.getParameter("faculty"))));
            request.setAttribute("listSheets", sheet);
            request.setAttribute("faculty", facultyService.get(Integer.parseInt(request.getParameter("faculty"))));

        }else
        {
            request.setAttribute("listSheets", new ArrayList<>());
        }

        request.setAttribute("user", user);
        request.setAttribute("listFaculty", facultyService.getAll());
        request.setAttribute("now", LocalDate.now());
        request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);

    }
}
