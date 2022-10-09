package org.example.game;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.Utils;
import org.example.utils.Validator;

import java.util.Locale;

@Getter
@Slf4j
public enum Property {
    PAPER(1),
    ROCK(2),
    SCISSORS(3);
    private final int number;

    Property(int number) {
        this.number = number;
    }

    public static void printParameters() {
        for (Property property : Property.values()) {
            System.out.println(Utils.firstUpperCase(String.valueOf(property)));
        }
    }

    public static Property validateParameter() {
        Property property;
        try {
            property = Property.valueOf(Validator.validateInputText().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            System.out.println("Enter from the list : ");
            printParameters();
            log.error(exception.getMessage());
            return validateParameter();
        }
        return property;
    }
}