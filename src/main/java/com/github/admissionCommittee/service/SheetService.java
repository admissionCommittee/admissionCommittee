package com.github.admissionCommittee.service;

import com.github.admissionCommittee.core.Sheet;
import com.github.admissionCommittee.dao.SheetDao;
import com.github.admissionCommittee.util.Validator;

public class SheetService extends GenericService<Sheet> {
    public SheetService(SheetDao sheetDao) {
        super(Sheet.class, sheetDao);
    }

    @Override
    public void save(Sheet sheet) {
        Validator.validateNotNull(sheet, Validator
                .MESSAGE_FOR_SOURCE_IF_NULL);
        //TODO save/update
    }
}
