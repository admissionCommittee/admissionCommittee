package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.UserAttendeeState;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import com.github.admissionCommittee.util.validate.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserInitializerUtil implements EntityInitializerUtil<User> {

    @Override
    public List<User> initEntities(int entitiesNumber, String inputFile,
                                   String outputFile) {
        try {
            List<User> userList = new ArrayList<>();
            Files.lines(Paths.get(inputFile))
                    .map(line -> line.split("\u200B"))
                    .forEach(substring -> {
                        System.out.println("SUBSTRING" + substring);
                        User user = new User(UserAttendeeState.valueOf
                                (substring[3]), UserTypeEnum.valueOf
                                (substring[4]), substring[5], substring[6],
                                substring[7], substring[8], substring[9],
                                LocalDate.of(
                                        Integer.parseInt(substring[10]
                                                .substring(6, 8)),
                                        Integer.parseInt(substring[10]
                                                .substring(3, 5)),
                                        Integer.parseInt(substring[10]
                                                .substring(0, 2))));
                        ValidatorUtil.getValidator(User.class)
                                .validateEntity(user);
                        userList.add(user);
                    });
            return userList;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}