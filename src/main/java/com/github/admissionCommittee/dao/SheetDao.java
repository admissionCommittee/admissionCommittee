package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.core.Sheet;
import org.hibernate.Session;

import java.util.List;

public class SheetDao extends GenericDao<Sheet> {

    public SheetDao() {
        super(Sheet.class);
    }

}
