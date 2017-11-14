package com.github.admissionCommittee.util;

import com.github.admissionCommittee.model.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 14.11.2017.
 */
public class ScoresUtil {
    public static HashMap<Subject, Integer> getRandomScores(
            List<Subject> subjectList, int minimumScore, int randomAddict) {
        HashMap<Subject, Integer> randomScores = new HashMap<>();
        Random random = new Random();
        subjectList.forEach(subject -> {
                    randomScores.put(subject, minimumScore + random.nextInt
                            (randomAddict+1));
                }
        );
        return randomScores;
    }
}
