package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.service.SubjectService;
import com.github.admissionCommittee.util.validate.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FacultyInitializerUtil implements EntityInitializerUtil<Faculty> {
    @Override
    public List<Faculty> initEntities(int entitiesNumber, String inputFile,
                                      String outputFile) {
        try {
            List<Faculty> facultyList = new ArrayList<>();
            Files.lines(Paths.get(inputFile))
                    .map(line -> line.split("\u200B"))
                    .forEach(substring -> {
                        Faculty faculty = new Faculty(substring[2], Integer
                                .parseInt(substring[3]), getRandomSubject(
                                3));
                        ValidatorUtil.getValidator(Faculty.class)
                                .validateEntity(faculty);
                        facultyList.add(faculty);
                    });
            return facultyList;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Set<Subject> getRandomSubject(int subjectsNumber) {
        SubjectService subjectService = new SubjectService();
        List<Subject> subjectList = subjectService.getAll();
        Set<Subject> subjectSet = new HashSet<>();
        for (int i = 0; i < subjectsNumber; i++) {
            Random random = new Random();
            int nextInt = random.nextInt(subjectList.size());
            subjectSet.add(subjectList.get(nextInt));
            subjectList.remove(nextInt);
        }
        return subjectSet;
    }
}