package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.Faculty;

import java.util.LinkedHashSet;
import java.util.Set;

public class FacultyValidatorUtil extends ValidatorUtil {
    @Override
    public Set<String> validate(AbstractEntity entityToValidate) {
        Set<String> errorsLog = new LinkedHashSet<>();
        Faculty faculty = ((Faculty) entityToValidate);
        if (!ValidatorUtil.validateFullName(faculty.getName(), "Faculty Name " +
                "is not valid")) {
            errorsLog.add("Faculty name is not valid: " + faculty.getName() +
                    "!");
        }
        if (!ValidatorUtil.validateNotNegative(faculty.getPeopleLimit(),
                "Attendees override")) {
            errorsLog.add("Faculty's people limit can't be negative: " +
                    "" + faculty.getPeopleLimit());
        }

        if (!ValidatorUtil.validateNotNull(faculty.getSubjects(), faculty
                        .getUsers(),
                "Subjects collection can't ve null",
                "Users collection can't be null")) {
            errorsLog.add("Faculty's subject's and user's can't be null:"
                    + faculty.getSubjects() + ", " + faculty.getUsers() + "!");
        }
        return new LinkedHashSet<>();
    }
}