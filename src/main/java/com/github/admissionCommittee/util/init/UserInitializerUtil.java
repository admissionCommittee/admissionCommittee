package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.UserAttendeeState;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.util.validate.UserValidatorUtil;
import com.github.admissionCommittee.util.validate.ValidatorUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserInitializerUtil implements EntityInitializerUtil<User> {

  /*  @Override
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
    }*/

    @Override
    public List<User> initEntities(int entitiesNumber, String inputFile,
                                   String outputFile) {
        ValidatorUtil validator = new UserValidatorUtil();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader
                    (inputFile));
            ArrayList<User> userList = new ArrayList<>();
            String line = "";
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null && counter <=
                    entitiesNumber) {
                String[] splittedLine = line.split("\u200B");

                User user = new User(UserAttendeeState.valueOf
                        (splittedLine[3]), UserTypeEnum.valueOf
                        (splittedLine[4]), splittedLine[5], splittedLine[6],
                        splittedLine[7], splittedLine[8], splittedLine[9],
                        LocalDate.of(Integer.parseInt(splittedLine[10]
                                        .substring(6, 8)),
                                Integer.parseInt(splittedLine[10].substring
                                        (3, 5)),
                                Integer.parseInt(splittedLine[10].substring
                                        (0, 2))));
                validator.validateEntity(user);
                userList.add(user);
                counter++;
            }
            ServiceFactory.getServiceFactory().getUserService().save(userList);
            validator.validateInit(userList);
            System.out.println("USERS INIT DONE");
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}