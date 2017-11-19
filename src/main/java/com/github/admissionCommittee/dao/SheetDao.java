package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.User;

import java.util.List;

public class SheetDao extends GenericDao<Sheet> {

    protected SheetDao() {
        super(Sheet.class);
    }

    //for DB search by mail implementation
    @SuppressWarnings("unchecked")
    public List<Sheet> getByFaculty(Faculty faculty) {
        openSessionWithTransaction();
        final List<Sheet> sheetsByFaculty = (List<Sheet>) getSession()
            .createQuery("from Sheet s where s.faculty=:faculty")
            .setParameter("faculty", faculty).list();
        closeSessionWithTransaction();
        return sheetsByFaculty;
    }
    //for DB search by mail implementation
    @SuppressWarnings("unchecked")
    public Sheet getByUser(User user) {
        openSessionWithTransaction();
        final Sheet sheetByUser = (Sheet) getSession()
                .createQuery("from Sheet s where s.user=:user")
                .setParameter("user", user).uniqueResult();
        closeSessionWithTransaction();
        return sheetByUser;
    }

}
