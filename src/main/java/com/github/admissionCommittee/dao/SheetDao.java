package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Sheet;

import java.util.List;

public class SheetDao extends GenericDao<Sheet> {

    public SheetDao() {
        super(Sheet.class);
    }

    //for DB search by mail implementation
    public List<Sheet> getByFaculty(Faculty faculty) {
        openSessionWithTransaction();
        List sheetsByFaculty = getSession().createQuery("SELECT sh " +
                "from Sheet sh where sh.faculty=:faculty").setParameter(
                "faculty", faculty).list();
        closeSessionWithTransaction();
        return sheetsByFaculty;
    }

}
