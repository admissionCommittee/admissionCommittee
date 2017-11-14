package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.service.SheetService;
import com.github.admissionCommittee.util.validate.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SheetInitializerUtil implements EntityInitializerUtil<Sheet> {
    @Override
    public List<Sheet> initEntities(int entitiesNumber, String outputFile,
                                    String inputFile) {
        ArrayList<Sheet> sheets = new ArrayList<>();
        List<User> userList = ServiceFactory.getServiceFactory()
                .getUserService().getAll();
        SheetService sheetService = ServiceFactory.getServiceFactory()
                .getSheetService();
        ValidatorUtil validator = ValidatorUtil.getValidator(Sheet
                .class);
        userList.forEach(user -> {
            Sheet sheet = new Sheet(user, user.getFaculty(),
                    calculateScoreSum(user.getSchoolCertificate().getSubjects
                            ()), calculateScoreSum(
                    user.getExamCertificate()
                            .getSubjects()) / user.getExamCertificate()
                    .getSubjects()
                    .size());
            validator.validateEntity(sheet);
            sheets.add(sheet);
            //assign sheet to user
            user.setSheet(sheet);
        });
        ServiceFactory.getServiceFactory().getSheetService().save
                (sheets);
        validator.validateInit(sheets);
        //update users
        ServiceFactory.getServiceFactory().getUserService().save(userList);
        System.out.println("SHEETS INIT DONE");
        return sheets;
    }

    private int calculateScoreSum(Map<Subject, Integer> scoresMap) {
        return scoresMap.keySet().stream().mapToInt(scoresMap::get)
                .sum();
    }
}