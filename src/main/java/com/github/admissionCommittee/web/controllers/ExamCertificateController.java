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
    private SheetService sheetService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        examService = (ExamCertificateService) config.getServletContext().getAttribute("examService");
        facultyService = (FacultyService) config.getServletContext().getAttribute("facultyService");
        subjectService = (SubjectService) config.getServletContext().getAttribute("subjectService");
        sheetService = (SheetService) config.getServletContext().getAttribute("sheetService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExamCertificate certificate = null;
        Map<Subject, Integer> subjectsMap = new HashMap<>();
        int examCertificateYear = 0;

        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        session.removeAttribute("errCertificate");
        User user = userService.get((long) session.getAttribute("user_id"));
        Faculty faculty = facultyService.get(Long.parseLong(request.getParameter("faculty")));
        log.info(String.format("Enter examination sheet of User %s ", user.getMail()));

        String err = "";
        if (request.getParameter("save") != null) {
            log.info(String.format("Update examination sheet of User %s ", user.getMail()));

            subjectsMap = new HashMap<>();

            ArrayList<String> list = Collections.list(request.getParameterNames());
            for (String param : list) {
                if (param.startsWith("sub_")) {
                    int value = Integer.parseInt(request.getParameter(param));
                    int subject_id = Integer.parseInt(param.substring(4));
                    log.info("Exam subjects:" + subject_id + " " + value);
                    subjectsMap.put(subjectService.get(subject_id), value);
                }
            }


            //user.getSchoolCertificate() == null ? certificate = new SchoolCertificate() : certificate = user.getSchoolCertificate();
            if (user.getExamCertificate() != null) {
                certificate = user.getExamCertificate();
            } else {

                certificate = new ExamCertificate();
            }
            examCertificateYear = Integer.parseInt(request.getParameter("year"));
            certificate.setYear(examCertificateYear);
            certificate.setSubjects(subjectsMap);
            certificate.setUser(user);
            // Validation
            Set<String> errors = examService.save(certificate);

            //check certificate errors
            if (errors.isEmpty()) {
                user.setExamCertificate(certificate);
                userService.save(user);
                Sheet sheet = null;
                if (user.getSheet() != null) {
                    sheet = ServiceFactory.getSheetService().getByUser(user);
                } else {
                    sheet = new Sheet();
                }

                //todo Так быть в сервисах не должно

                sheet.setUser(userService.get(user.getId())); // temporal workaround
                sheet.setFaculty(faculty);
                sheet.setSumExamCertificateScore(0);

                //sum scores calculating during save
                Set<String> errorsSheet = sheetService.save(sheet);

                //check sheet errors
                if (errorsSheet.isEmpty()) {
                    user.setSheet(sheet);
                    userService.save(user);
                    response.sendRedirect("/user");
                    return;
                }
            }

            err = errors.toString().replace("[", "").replace("]", "<br>").replace(",", "<br>");
        }

        certificate = user.getExamCertificate();
        if (certificate != null) {
            Map<Subject, Integer> mapSubjectsScores = certificate.getSubjects();
            request.setAttribute("mapSubjectsScores", mapSubjectsScores);
            request.setAttribute("certificate", certificate);
        } else {
            request.setAttribute("mapSubjectsScores", subjectsMap);
            certificate = new ExamCertificate();
            certificate.setYear(examCertificateYear);
            request.setAttribute("certificate", certificate);
        }

        List<Subject> list = new ArrayList<>(faculty.getSubjects());
        list.sort(Comparator.comparing(p -> p.getName().toString()));
        request.setAttribute("listSubjects", list);

        request.setAttribute("faculty", faculty);
        request.getSession().setAttribute("errCertificate", err);
        request.getRequestDispatcher("/WEB-INF/jsp/exam.jsp").forward(request, response);
    }
}
