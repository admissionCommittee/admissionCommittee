package com.github.admissionCommittee.util.init;

import java.util.Set;

public interface InitializerUtil {
    Set<String> init(int entitiesNumber, String inputFile,
                     String outputFile);
}