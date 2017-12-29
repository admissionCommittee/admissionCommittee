package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.UserAttendeeState;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.util.validate.UserValidatorUtil;
import com.github.admissionCommittee.util.validate.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserInitializerUtil implements InitializerUtil {
    private static final Logger log = LoggerFactory.getLogger
            (UserInitializerUtil.class);

    @Override
    public Set<String> init(int entitiesNumber, String inputFile,
                            String outputFile) {
        Set<String> errorsLog = new LinkedHashSet<>();
        ValidatorUtil validator = new UserValidatorUtil();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader
                    (inputFile));
            ArrayList<User> userList = new ArrayList<>();
            String line = "";
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null && counter <
                    entitiesNumber) {
                String[] splittedLine = line.split("\u200B");
                User user = new User(UserAttendeeState.valueOf
                        (splittedLine[3]), UserTypeEnum.valueOf
                        (splittedLine[4]), splittedLine[7], splittedLine[5],
                        splittedLine[6], splittedLine[8], splittedLine[9],
                        LocalDate.of(Integer.parseInt(splittedLine[10]
                                        .substring(6, 10)),
                                Integer.parseInt(splittedLine[10].substring
                                        (3, 5)),
                                Integer.parseInt(splittedLine[10].substring
                                        (0, 2))));
                errorsLog.addAll(validator.validate(user));
                userList.add(user);
                counter++;
            }
            ServiceFactory.getUserService().save(userList);
            errorsLog.addAll(validator.validateInit(userList));
            log.info(String.format("Users have been initialized successfully," +
                    " total %d users", counter));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorsLog;
    }
}