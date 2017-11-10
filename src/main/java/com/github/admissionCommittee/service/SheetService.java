package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.dao.FacultyDao;
import com.github.admissionCommittee.dao.SheetDao;
import com.github.admissionCommittee.model.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SheetService extends GenericService<Sheet> {
    public SheetService() {
        super(Sheet.class, DaoFactory.getDaoFactory().getSheetDao());
    }

    public List<Sheet> getByFaculty(Faculty faculty) {
        return ((SheetDao) getDao()).getByFaculty(faculty);
    }
}