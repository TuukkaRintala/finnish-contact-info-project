package main.java.rintalatuukka.contacts.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validation {
    public static boolean idValidation(String id) {
        final String regex = "^[^0{2}[]]";
        return true;
    }
    public static boolean nameValidation(String name) {
        // The inclusion of nordic letters in the regex is from stackoverflow,
        // slightly modified: https://bit.ly/3ZJnEG9
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{2,50}";
        Pattern namePattern = Pattern.compile(regex);
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.matches();
    }
    public static boolean phoneValidation(String number) {
        // The inclusion of nordic letters in the regex is from stackoverflow:
        // https://bit.ly/3ZJnEG9
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{2,50}";
        Pattern phonePattern = Pattern.compile(regex);
        Matcher phoneMatcher = phonePattern.matcher(name);
        return phoneMatcher.matches();
    }
    public static boolean addressValidation(String address) {
        // The inclusion of nordic letters in the regex is from stackoverflow:
        // https://bit.ly/3ZJnEG9
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{2,50}";
        Pattern addressPattern = Pattern.compile(regex);
        Matcher addressMatcher = addressPattern.matcher(name);
        return addressMatcher.matches();
    }
    public static boolean emailValidation(String email) {
        // The inclusion of nordic letters in the regex is from stackoverflow:
        // https://bit.ly/3ZJnEG9
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{2,50}";
        Pattern emailPattern = Pattern.compile(regex);
        Matcher emailMatcher = emailPattern.matcher(name);
        return emailMatcher.matches();
    }
}