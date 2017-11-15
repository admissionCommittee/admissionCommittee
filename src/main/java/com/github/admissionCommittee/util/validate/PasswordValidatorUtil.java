package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Konstantin Mavropulo.
 *         Class implements password's validating.
 */
public class PasswordValidatorUtil extends ValidatorUtil {
    public boolean isValid;
    private static final Logger log = LoggerFactory.getLogger
            (UserValidatorUtil.class);
    private static String SOURCESYMBOLSALL =
            "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./~!@#$%^&*()" +
                    "_+{}|:\"<>?QWERTYUIOPASDFGHJKLZXCVBNM";

    @Override
    public void validate(AbstractEntity abstractEntityToValidate) {
        User user = (User) abstractEntityToValidate;
        String password = user.getPassword();
        String login = user.getMail();
        if (!checkDigitsNumber(password, 0,
                100)
                | !checkLettersNumber(password, 0,
                100)
                | !checkLength(password, 0, 100)
                | !checkLengthBits(password, 100)
                | isEqualsToLogin(password, login)
                | !checkSymbolsPermitted(password, SOURCESYMBOLSALL)
                | !checkEqualSequence(password, 100)
                | !checkAdjacentKeyboardSequence(password,
                SOURCESYMBOLSALL)
                ) {
            log.debug(String.format("Password is %s not valid", password));
        } else {
        }
        isValid = true;
    }

    /**
     * Count unique symbols in a password.
     */
    public int countChars() {
        String toCheck = "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm," +
                "./!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?";
        Map<Character, Long> charsCounted = toCheck.chars().parallel().boxed()
                .map(e -> ((char) e.intValue()))
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        Map<Character, Long> sortedCharsCounted = new LinkedHashMap<>();
        charsCounted.entrySet()
                .parallelStream()
                .sorted(Map.Entry.<Character, Long>comparingByValue()
                        .reversed())
                .forEachOrdered(e -> sortedCharsCounted.put(e.getKey(),
                        e.getValue()));
        return toCheck.chars().parallel()
                .map(e -> 1)
                .sum();
    }

    public boolean checkDigitsNumber(String passwordToCheck, int
            digitsMinNumber, int digitsMaxNumber) {
        int sum = passwordToCheck.chars().parallel()
                .filter(Character::isDigit)
                .map(e -> 1)
                .sum();
        return sum >= digitsMinNumber && sum <= digitsMaxNumber;
    }

    public boolean checkLettersNumber(String passwordToCheck, int
            lettersMinNumber, int lettersMaxNumber) {
        int sum = passwordToCheck.chars().parallel()
                .filter(Character::isLetter)
                .map(e -> 1)
                .sum();
        return sum >= lettersMinNumber && sum <= lettersMaxNumber;
    }

    public boolean checkLength(String passwordToCheck, int
            lengthMinNumber, int lengthMaxNumber) {

        return passwordToCheck.length() >= lengthMinNumber && passwordToCheck
                .length() <= lengthMaxNumber;
    }

    public boolean isEqualsToLogin(String passwordToCheck, String login) {
        return passwordToCheck.equalsIgnoreCase(login);
    }

    public boolean checkSymbolsPermitted(String passwordToCheck, String
            permittedSymbols) {
        for (int i = 0; i < passwordToCheck.length(); i++) {
            if (!permittedSymbols.contains(passwordToCheck.charAt(i) + "")) {
                return false;
            }
        }
        return true;
    }

    //maxEquals from 0 to length of password
    //not checked for 0 and 1 psw length
    public boolean checkEqualSequence(String passwordToCheck, int
            maxEqualSequence) {
        int equalsCount = 0;
        int iMaxLimit = 0;
        if (maxEqualSequence == 0) {
            iMaxLimit = passwordToCheck.length() - 1;
        } else {
            iMaxLimit = passwordToCheck.length() - maxEqualSequence;
        }

        for (int i = 0; i < iMaxLimit; i++) {
            if (passwordToCheck.charAt(i) == passwordToCheck.charAt(i + 1)) {
                equalsCount++;
                if (equalsCount > maxEqualSequence) {
                    return false;
                }
            } else {
                equalsCount = 0;
            }
        }
        return true;
    }

    public boolean checkLengthBits(String passwordToCheck, long maxLengthBits) {
        try {
            long bytesLength = passwordToCheck.getBytes("UTF-8").length;
            return bytesLength <= maxLengthBits;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean checkAdjacentKeyboardSequence(String passwordToCheck, String
            keyboardSymbolsOrderedByOrigin) {
        for (int i = 0; i < passwordToCheck.length() - 1; i++) {
            String letterToCheck = passwordToCheck.substring(i + 1, i + 2)
                    .toLowerCase();
            if (passwordToCheck.charAt(i) == keyboardSymbolsOrderedByOrigin
                    .charAt(getIndex(keyboardSymbolsOrderedByOrigin,
                            letterToCheck, -1))
                    | passwordToCheck.charAt(i) ==
                    keyboardSymbolsOrderedByOrigin
                            .charAt(getIndex(keyboardSymbolsOrderedByOrigin,
                                    letterToCheck, 1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get index of letter after shifting.
     *
     * @param alphabet the alphabet
     * @param toFind   the letter to find
     * @param shift    the shift number for the current letter's position
     * @return index after shifting
     * @throws IllegalArgumentException if at least one of
     *                                  <code>String</code> parameters is null
     */
    private int getIndex(String alphabet, String toFind, int shift) {
        int index = (alphabet.indexOf(toFind) + shift) % alphabet
                .length();
        if (index < 0) {
            do {
                index = alphabet.length() + index;
            } while (index < 0);
        }
        return index;
    }
}