package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.service.ServiceFactory;

import java.util.List;

public class SchoolCertificateValidatorUtil extends ValidatorUtil {
    @Override
    public void validateEntity(AbstractEntity entityToValidate) {
        //TODO
    }

    @Override
    public void validateInit(List<? extends AbstractEntity> toValidate) {
        List<? extends AbstractEntity> entitiesList = ServiceFactory
                .getServiceFactory().getService(toValidate.get(0).getClass())
                .getAll();
        //why id 1
        System.out.println("CHECK > from DB: " + entitiesList);
        System.out.println("initial: " + toValidate);
    }
}