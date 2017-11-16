package com.github.admissionCommittee.web.controllers;

import com.github.admissionCommittee.model.*;
import com.github.admissionCommittee.service.*;
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

@WebServlet({"/examinations"})
public class ExamCertificateController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ExamCertificateController.class);
    private UserService userService;
    private ExamCertificateService examService;
    private FacultyService facultyService;
    private SubjectService subjectService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        examService = (ExamCertificateService) config.getServletContext().getAttribute("examService");
        facultyService = (FacultyService) config.getServletContext().getAttribute("facultyService");
        subjectService = (SubjectService) config.getServletContext().getAttribute("subjectService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        session.removeAttribute("errCertificate");
        User user = userService.get((long) session.getAttribute("user_id"));
        Faculty faculty = facultyService.get(Long.parseLong(request.getParameter("faculty")));
        log.info(String.format("Enter examination sheet of User %s ", user.getMail()));

        String err = "";
        if (request.getParameter("save") != null) {
            log.info(String.format("Update examination sheet of User %s ", user.getMail()));

            Map<Subject, Integer> subjectsMap = new HashMap<>();

            ArrayList<String> list = Collections.list(request.getParameterNames());
            for (String param: list) {
                if (param.startsWith("sub_")) {
                    int value = Integer.parseInt(request.getParameter(param));
                    int subject_id = Integer.parseInt(param.substring(4));
                    log.info("Exam subjects:" + subject_id + " " + value);
                    subjectsMap.put(subjectService.get(subject_id), value);
                }
            }

            ExamCertificate certificate;
            //user.getSchoolCertificate() == null ? certificate = new SchoolCertificate() : certificate = user.getSchoolCertificate();
            if (user.getExamCertificate() != null){
                certificate = user.getExamCertificate();
            }else {

                certificate = new ExamCertificate();
            }

            certificate.setYear(Integer.parseInt(request.getParameter("year")));
            certificate.setSubjects(subjectsMap);
            certificate.setUser(user);
            // Validation
            List<String> errors = examService.save(certificate);

            if (errors.isEmpty()) {
                //todo Так быть в сервисах не должно
                Sheet sheet = new Sheet();
                sheet.setUser(userService.get(user.getId())); // temporal workaround
                sheet.setFaculty(faculty);
                sheet.setSumExamCertificateScore(0);
                new SheetService().save(sheet);

                response.sendRedirect("/user");
                return;
            }

            err = errors.toString().replace("[","").replace("]","<br>");
        }

        ExamCertificate certificate = user.getExamCertificate();
        if(certificate != null){
            Map<Subject, Integer> mapSubjectsScores = certificate.getSubjects();
            request.setAttribute("mapSubjectsScores", mapSubjectsScores);
            request.setAttribute("certificate", certificate);
        }else
        {
            request.setAttribute("mapSubjectsScores", new HashMap<>());
        }

        List<Subject> list = new ArrayList<>(faculty.getSubjects());
        list.sort(Comparator.comparing(p -> p.getName().toString()));
        request.setAttribute("listSubjects", list);

        request.setAttribute("faculty", faculty);
        request.getSession().setAttribute("errCertificate", err);
        request.getRequestDispatcher("/WEB-INF/jsp/exam.jsp").forward(request, response);
    }
}
