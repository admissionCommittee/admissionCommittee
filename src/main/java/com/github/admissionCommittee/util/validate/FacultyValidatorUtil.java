package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.Faculty;

public class FacultyValidatorUtil extends ValidatorUtil {
    @Override
    public void validate(AbstractEntity entityToValidate) {
        Faculty faculty = ((Faculty) entityToValidate);
        ValidatorUtil.validateFullName(faculty.getName(), "Faculty Name is not valid");
        ValidatorUtil.validateNotNegative(faculty.getPeopleLimit(), "Attendees " +
                "override");
        ValidatorUtil.validateNotNull(faculty.getSubjects(), faculty.getUsers(),
                "Subjects collection can't ve null",
                "Users collection can't be null");
    }
}