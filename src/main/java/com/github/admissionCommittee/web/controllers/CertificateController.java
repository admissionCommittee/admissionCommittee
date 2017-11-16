package com.github.admissionCommittee.web.controllers;

import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.FacultyService;
import com.github.admissionCommittee.service.SchoolCertificateService;
import com.github.admissionCommittee.service.SubjectService;
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
import java.util.*;

@WebServlet({"/certificate"})
public class CertificateController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CertificateController.class);
    private UserService userService;
    private SchoolCertificateService certificateService;
    private SubjectService subjectService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        certificateService = (SchoolCertificateService) config.getServletContext().getAttribute("certificateService");
        subjectService = (SubjectService) config.getServletContext().getAttribute("subjectService");
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
        session.removeAttribute("errCertificate");
        User user = userService.get((long) session.getAttribute("user_id"));

        String err = "";
        if (request.getParameter("save") != null) {
            log.info(String.format("Update certificate by User %s ", user.getMail()));

            Map<Subject, Integer> subjectsMap = new HashMap<>();

            ArrayList<String> list = Collections.list(request.getParameterNames());
            for (String param: list) {
                if (param.startsWith("sub_")) {
                    int value = Integer.parseInt(request.getParameter(param));
                    int subject_id = Integer.parseInt(param.substring(4));
                    log.trace("Certificate subjects:" + subject_id + " " + value);
                    subjectsMap.put(subjectService.get(subject_id), value);
                }
            }

            //todo
            SchoolCertificate certificate;
            //user.getSchoolCertificate() == null ? certificate = new SchoolCertificate() : certificate = user.getSchoolCertificate();
            if (user.getSchoolCertificate() != null){
                certificate = user.getSchoolCertificate();
            }else {
                certificate = new SchoolCertificate();
            }

            //SchoolCertificate newCertificate = new SchoolCertificate(user,Integer.parseInt(request.getParameter("year")),subjectsMap);
            certificate.setYear(Integer.parseInt(request.getParameter("year")));
            certificate.setSubjects(subjectsMap);
            certificate.setUser(user);

            // Validation
            List<String> errors = certificateService.save(certificate);
            if (errors.isEmpty()) {
                response.sendRedirect("/user");
                return;
            }

            err = errors.toString().replace("[", "").replace("]", "<br>").replace(",", "<br>");
        }

        SchoolCertificate certificate = user.getSchoolCertificate();
        if(certificate != null){
            Map<Subject, Integer> mapSubjectsScores = certificate.getSubjects();
            request.setAttribute("mapSubjectsScores", mapSubjectsScores);
            request.setAttribute("certificate", user.getSchoolCertificate());
        }else
        {
            request.setAttribute("mapSubjectsScores", new HashMap<>());
        }

        //todo add service injection
        List<Subject> list = new SubjectService().getAll();
        list.sort(Comparator.comparing(p -> p.getName().toString()));
        request.setAttribute("listSubjects", list);
        //Collections.sort(list);

        request.getSession().setAttribute("errCertificate", err);
        request.getRequestDispatcher("/WEB-INF/jsp/certificate.jsp").forward(request, response);
    }
}
