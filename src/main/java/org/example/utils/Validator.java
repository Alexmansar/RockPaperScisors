package org.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Slf4j
public class Validator {
    public static String validateInputText() {
        String str = Utils.getString();
        if (str.trim().isEmpty()) {
            log.error("empty string");
            System.out.println("You have not entered any value. Please, try again ");
            return validateInputText();
        }
        return str;
    }

    public static boolean isPositive(int number) {
        return number > ((number + 1) % number);
    }

    public enum Action {
        YES, NO;

        public static void printAction() {
            for (Action type : Action.values()) {
                System.out.print(Utils.firstUpperCase(String.valueOf(type)) + ", ");
            }
        }

        public static Action validateAction() {
            Action action;
            try {
                action = Action.valueOf(validateInputText().toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception) {
                System.out.println("Enter from the list : ");
                printAction();
                log.error("not valid value {}", exception.getMessage());
                return validateAction();
            }
            return action;
        }
    }
}