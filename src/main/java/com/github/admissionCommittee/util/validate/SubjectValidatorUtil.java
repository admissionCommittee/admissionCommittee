package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class SubjectValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (SubjectValidatorUtil.class);

    @Override
    public Set<String> validate(AbstractEntity entityToValidate) {
        String name = ((Subject) entityToValidate).getName().toString();
        boolean matches = Pattern.compile("^[\\p{L} .-]+$").matcher
                (name).matches();
        if (!matches) {
            log.debug(String.format("Subject's name %s is not valid.", name),
                    name);
            throw new IllegalStateException(String.format("Subject's name %s is not valid.", name));
        }
        //TODO can be added some validation
        return new LinkedHashSet();
    }
}