package main.java.rintalatuukka.contacts.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validation {
    public static boolean idValidation(final String id) {
        // This regex is from https://regex101.com/library/cIohyA, and has been
        // edited to include A-F and U-W as the century sign.
        final String regex = "^(0[1-9]|[1-2][0-9]|3[0-1])(0[1-9]|1[0-2])[0-9]" +
        "{2}[[A-F][U-Y]+-][0-9]{3}[A-z0-9]$";
        Pattern idPattern = Pattern.compile(regex);
        Matcher idMatcher = idPattern.matcher(id);
        return idMatcher.matches();
    }
    public static boolean nameValidation(final String name) {
        // The inclusion of nordic letters in the regex is from stackoverflow,
        // slightly modified: https://bit.ly/3ZJnEG9
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{2,50}";
        Pattern namePattern = Pattern.compile(regex);
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.matches();
    }
    public static boolean phoneValidation(final String number) {
        // This regex is modified from https://ihateregex.io/expr/phone/ to make
        // it function.
        final String regex = "^[+]?[(]?[0-9]{3}[)]?[-\s\\.]?[0-9]{3}[-\s\\.]?" +
                             "[0-9]{4,6}$";
        Pattern phonePattern = Pattern.compile(regex);
        Matcher phoneMatcher = phonePattern.matcher(number);
        return phoneMatcher.matches();
    }
    public static boolean addressValidation(final String address) {
        // The inclusion of nordic letters in the regex is from stackoverflow:
        // https://bit.ly/3ZJnEG9
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{2,50}";
        Pattern addressPattern = Pattern.compile(regex);
        Matcher addressMatcher = addressPattern.matcher(address);
        return addressMatcher.matches();
    }
    public static boolean emailValidation(final String email) {
        // This regex is from https://regex101.com/library/SOgUIV.
        final String regex = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
        Pattern emailPattern = Pattern.compile(regex);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }
}
