package com.github.admissionCommittee.service;

import com.github.admissionCommittee.core.Sheet;
import com.github.admissionCommittee.dao.SheetDao;

public class SheetService extends GenericService<Sheet> {
    public SheetService(SheetDao sheetDao) {
        super(Sheet.class, sheetDao);
    }

}
