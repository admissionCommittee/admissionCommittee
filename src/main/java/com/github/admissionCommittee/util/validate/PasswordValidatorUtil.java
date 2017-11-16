package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Konstantin Mavropulo.
 *         Class implements password's validating.
 */
public class PasswordValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (UserValidatorUtil.class);
    private static String KEYBOARD = "`1234567890-=qwertyuiop[]//asdfghjkl'zxcv"
            + "bnm,./~!@#$%^&*()_+{}|:/<>?QWERTYUIOPASDFGHJKLZXCVBNM";
    private static String DIGITS_SET = "12345678901234567890123456789012345678"
            + "9012345678901234567891234567890123456789012345678901234567890";
    private static String LETTERS_SET = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPA"
            + "SDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXC"
            + "VBNM";
    private static String SPECIAL_SYMBOLS_SET = "`-=[]//;',./~!@#$%^&*()_+{}|:"
            + "/<>?`-=[]//;',./~!@#$%^&*()_+{}|:/<>?`-=[]//;',./~!@#$%^&*()_"
            + "+{}|:/<>?";

    private Random randomSymbol;
    private StringBuilder password = new StringBuilder();
    private int passwordLength;
    private String specialSymbolsSet;
    private String digitsSet;
    private String lettersSet;
    private int lettersNumber;
    private int digitsNumber;
    private int specialSymbolsNumber;
    private int maxBytesLength;
    private int maxSequencePermitted;
    private String loginPair;
    private String symbolsKeyboardOrdered;
    private int maxLength;
    private int minLength;
    private boolean isEqualToLogin;
    private boolean isApproved;

    private PasswordValidatorUtil() {
    }

    private PasswordValidatorUtil(StringBuilder password) {
        this.password = password;
    }

    public StringBuilder getPassword() {
        return password;
    }

    public static PasswordValidatorUtil init() {
        PasswordValidatorUtil password = PasswordValidatorUtil.getBuilder()
                .setDigits(5,
                        DIGITS_SET)
                .setLetters(5, LETTERS_SET)
                .setSpecialSymbols(5, SPECIAL_SYMBOLS_SET)
                .setLength(10)
                .setLoginPair("login")
                .setMaxBytesLength(10)
                .setMaxSequencePermitted(1)
                .build();
        System.out.println(password.password);
        return password;
    }

    @Override
    public Set<String> validate(AbstractEntity abstractEntityToValidate) {
        User user = (User) abstractEntityToValidate;
        String password = user.getPassword();
        String login = user.getMail();
        Set<String> errorsLog = new LinkedHashSet<>();
        if (/*!checkDigitsNumber(password, 3,
                3, errorsLog)
                | !checkLettersNumber(password, 3,
                3, errorsLog)
                | !checkSpecialSymbolsNumber(password,
                3, 3, errorsLog)
                | !checkCapitalLettersNumber(password,
                1, 1, errorsLog)
                 isEqualsToLogin(password, login, errorsLog)*/
                !checkLength(password, 3, 30,
                        errorsLog)
               /* | !checkSymbolsPermitted(password, KEYBOARD, errorsLog)
                | !checkEqualSequence(password, 100, errorsLog)
                | !checkAdjacentKeyboardSequence(password,
                KEYBOARD, errorsLog*/
                ) {
            log.debug(String.format("Password %s is not valid!", password));
        }
        return errorsLog;
    }

    public boolean validateInit(String password, Set<String> errorsLog) {

        if (!checkDigitsNumber(password, 3,
                3, errorsLog)
                | !checkLettersNumber(password, 3,
                3, errorsLog)
                | !checkSpecialSymbolsNumber(password,
                3, 3, errorsLog)
                | !checkCapitalLettersNumber(password,
                1, 1, errorsLog)
                //| isEqualsToLogin(password, login, errorsLog)
                | !checkSymbolsPermitted(password, KEYBOARD, errorsLog)
                | !checkEqualSequence(password, 100, errorsLog)
                | !checkAdjacentKeyboardSequence(password,
                KEYBOARD, errorsLog)
                ) {
            return false;
        }
        return true;
    }

    public static PasswordBuilder getBuilder() {
        return new PasswordValidatorUtil().new PasswordBuilder();
    }

    /**
     * Count unique symbols in a password.
     */
    /*public void countChars() {

        String toCheck = "`1234567890-=qwertyuiop[]//asdfghjkl;'zxcvbnm," +
                "./!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:/"ZXCVBNM<>?";
        Map<Character, Long> charsCounted = toCheck.chars().parallel()
                .boxed()
                .map(e -> ((char) e.intValue()))
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        System.out.println(charsCounted);
        Map<Character, Long> sortedCharsCounted = new LinkedHashMap<>();

        charsCounted.entrySet()
                .parallelStream()
                .sorted(Map.Entry.<Character, Long>comparingByValue()
                        .reversed())
                .forEachOrdered(e -> sortedCharsCounted.put(e.getKey(),
                        e.getValue()));
        System.out.println(sortedCharsCounted);
        int sum = toCheck.chars().parallel()
                .map(e -> 1)
                .sum();
        System.out.println(sum);
    }*/
    public boolean checkDigitsNumber(String passwordToCheck, int
            digitsMinNumber, int digitsMaxNumber, Set<String> errorLog) {
        int sum = passwordToCheck.chars()
                .filter(Character::isDigit)
                .map(e -> 1)
                .sum();
        isApproved = sum >= digitsMinNumber && sum <= digitsMaxNumber;
        if (!isApproved) {
            String error = "Password's number of digits can't be " + Integer
                    .toString(sum) + ", the number must be between " +
                    digitsMinNumber + " and " + digitsMaxNumber + "!";
            log.debug(error);
            errorLog.add(error);
            return false;
        }
        return true;
    }

    public boolean checkLettersNumber(String passwordToCheck, int
            lettersMinNumber, int lettersMaxNumber, Set<String> errorLog) {
        int sum = passwordToCheck.chars()
                .filter(Character::isLetter)
                .map(e -> 1)
                .sum();
        isApproved = sum >= lettersMinNumber && sum <= lettersMaxNumber;
        if (!isApproved) {
            String error = "Password's number of letters can't be " + Integer
                    .toString(sum) + ", the number must be between " +
                    lettersMinNumber + " and " + lettersMaxNumber + "!";
            log.debug(error);
            errorLog.add(error);
            return false;
        }
        return true;
    }

    public boolean checkSpecialSymbolsNumber(String passwordToCheck, int
            specialSymbolsNumberMin, int specialSymbolsNumberMax,
                                             Set<String> errorLog) {
        int sum = passwordToCheck.chars()
                .filter(c -> SPECIAL_SYMBOLS_SET.contains(Character
                        .toString(
                                (char) c)))
                .map(c -> 1)
                .sum();
        isApproved = sum >= specialSymbolsNumberMin && sum <=
                specialSymbolsNumberMax;
        if (!isApproved) {
            String error = "Password's special symbol's number can't be " +
                    Integer.toString(sum) + ", the number must be between " +
                    specialSymbolsNumberMin + " and " +
                    specialSymbolsNumberMax + "!";
            log.debug(error);
            errorLog.add(error);
            return false;
        }
        return true;
    }

    public boolean checkCapitalLettersNumber(String passwordToCheck, int
            capitalLettersNumberMin, int capitalLettersNumberMax,
                                             Set<String> errorLog) {
        int sum = passwordToCheck.chars()
                .filter(Character::isUpperCase)
                .map(c -> 1)
                .sum();
        boolean isApproved = sum >= capitalLettersNumberMin && sum <=
                capitalLettersNumberMax;
        if (!isApproved) {
            String error = "Password's capital symbol's number can't be " +
                    Integer.toString(sum) + ", the number must be between " +
                    capitalLettersNumberMax + " and " +
                    capitalLettersNumberMax + "!";
            log.debug(error);
            errorLog.add(error);
            return false;
        }
        return true;
    }

    public boolean checkLength(String passwordToCheck, int
            lengthMinNumber, int lengthMaxNumber, Set<String> errorLog) {
        boolean isLengthValid = passwordToCheck.length() >= lengthMinNumber &&
                passwordToCheck.length() <= lengthMaxNumber;
        if (!isLengthValid) {
            String error = "Password's length length must be between " +
                    lengthMinNumber + " and " + lengthMaxNumber + "!";
            log.debug(error);
            errorLog.add(error);
            return false;
        }
        return true;
    }

    public boolean isEqualsToLogin(String passwordToCheck, String login,
                                   List<String> errorLog) {
        isEqualToLogin = passwordToCheck.equalsIgnoreCase(login);
        if (isEqualToLogin) {
            String error = "Password can't be equal to login !";
            log.debug(error);
            errorLog.add(error);
            return false;
        }
        return true;
    }

    public boolean checkSymbolsPermitted(String passwordToCheck, String
            permittedSymbols, Set<String> errorLog) {
        for (int i = 0; i < passwordToCheck.length(); i++) {
            if (!permittedSymbols.contains(passwordToCheck.charAt(i) +
                    "")) {
                String error = "Permitted symbols for password are: " +
                        "`1234567890-=qwertyuiop"
                        + "[]//asdfghjkl'zxcvbnm,./~!@#$%^&*()_+{}|:/<>?QWERTY"
                        + "UIOPASDFGHJKLZXCVBNM !";
                log.debug(error);
                errorLog.add(error);
                return false;
            }
        }
        return true;
    }

    //maxEquals from 0 to length of password
    //not checked for 0 and 1 psw length
    public boolean checkEqualSequence(String passwordToCheck, int
            maxEqualSequence, Set<String> errorLog) {
        int equalsCount = 0;
        int iMaxLimit = 0;
        if (maxEqualSequence == 0) {
            iMaxLimit = passwordToCheck.length() - 1;
        } else {
            iMaxLimit = passwordToCheck.length() - maxEqualSequence;
        }

        for (int i = 0; i < iMaxLimit; i++) {
            if (passwordToCheck.charAt(i) == passwordToCheck.charAt(i +
                    1)) {
                equalsCount++;
                if (equalsCount > maxEqualSequence) {
                    String error = "Max permitted sequence of equal " +
                            "characters in password is : " +
                            maxEqualSequence + "!";
                    log.debug(error);
                    errorLog.add(error);
                    return false;
                }
            } else {
                equalsCount = 0;
            }
        }
        return true;
    }

    /*
    public boolean checkLengthBits(String passwordToCheck, long
            maxLengthBits, Set<String> errorLog) {
        try {
            long bytesLength = passwordToCheck.getBytes("UTF-8").length;

            System.out.println("passwords length: " + bytesLength);
            return bytesLength <= maxLengthBits;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return false;
    }*/

    public boolean checkAdjacentKeyboardSequence(
            String passwordToCheck, String keyboardSymbolsOrderedByOrigin,
            Set<String> errorLog) {
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
                String error = "Using the adjacent keyboard symbols " +
                        "in the password is prohibited!";
                log.debug(error);
                errorLog.add(error);
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
     *                                  <code>String</code> parameters is
     *                                  null
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

    class PasswordBuilder {
        private PasswordBuilder() {
        }

        public PasswordBuilder setLength(int passwordLenth) {
            PasswordValidatorUtil.this.passwordLength = passwordLenth;
            return this;
        }

        public PasswordBuilder setSpecialSymbols(int specialSymbolsNumber,
                                                 String specialSymbolsSet) {
            PasswordValidatorUtil.this.specialSymbolsNumber =
                    specialSymbolsNumber;
            PasswordValidatorUtil.this.specialSymbolsSet = specialSymbolsSet;
            return this;
        }

        public PasswordBuilder setDigits(int digitsNumber, String
                digitsSet) {
            PasswordValidatorUtil.this.digitsNumber = digitsNumber;
            PasswordValidatorUtil.this.digitsSet = digitsSet;
            return this;
        }

        public PasswordBuilder setLetters(int lettersNumber, String
                lettersSet) {
            PasswordValidatorUtil.this.lettersNumber = lettersNumber;
            PasswordValidatorUtil.this.lettersSet = lettersSet;
            return this;
        }

        public PasswordBuilder setSymbolsKeyboardOrdered(String symbolsKeyboardOrdered) {
            PasswordValidatorUtil.this.symbolsKeyboardOrdered =
                    symbolsKeyboardOrdered;
            return this;
        }

        public PasswordBuilder setMaxBytesLength(int maxBytesLength) {
            PasswordValidatorUtil.this.maxBytesLength = maxBytesLength;
            return this;
        }

        public PasswordBuilder setMaxSequencePermitted(int maxSequencePermitted) {
            PasswordValidatorUtil.this.maxSequencePermitted =
                    maxSequencePermitted;
            return this;
        }

        public PasswordBuilder setMaxLength(int maxLength) {
            PasswordValidatorUtil.this.maxLength = maxLength;
            return this;
        }

        public PasswordBuilder setMinLength(int minLength) {
            PasswordValidatorUtil.this.minLength = minLength;
            return this;
        }

        public PasswordBuilder setLoginPair(String loginPair) {
            PasswordValidatorUtil.this.loginPair = loginPair;
            return this;
        }

        public PasswordValidatorUtil build() {
            return PasswordValidatorUtil.this;
        }
    }
}