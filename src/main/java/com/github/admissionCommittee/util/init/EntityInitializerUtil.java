package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.AbstractEntity;

import java.util.List;

public interface EntityInitializerUtil<T extends AbstractEntity> {
    public List<T> initEntities(int entitiesNumber, String outputFile,
                                   String inputFile);
}