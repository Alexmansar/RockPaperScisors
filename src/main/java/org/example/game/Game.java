package org.example.game;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.FileUtils;
import org.example.utils.Utils;
import org.example.utils.Validator;

import java.util.Random;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {
    private static final Random RANDOM = new Random();
    int WIN_USER_COUNT = 0;
    int WIN_BOT_COUNT = 0;
    int GAMES = 0;
    boolean IS_STOP;
    public static final String LOG_FILE = "logger.log";

    public void run() {
        FileUtils.clearFile(LOG_FILE);
        String appName = Game.class.getSimpleName();
        log.info("APP '{}' start success", appName.toUpperCase());
        System.out.println("Please, enter your name");
        String userName = createUserName();
        log.info("User {} ", userName);
        System.out.println("Please, enter games numbers");
        int chances = chances();
        log.info("Chances {}", chances);
        String winMessage = "";
        do {
            Property botValue = createRandomValue();
            log.info("random value  {}", botValue.name());
            Property userValue = createUserValue();
            botWin(botValue, 1, userValue, 2);
            userWin(botValue, 1, userValue, 3);
            botWin(botValue, 2, userValue, 3);
            userWin(botValue, 2, userValue, 1);
            botWin(botValue, 3, userValue, 1);
            userWin(botValue, 3, userValue, 2);
            IS_STOP = chances == WIN_BOT_COUNT || chances == WIN_USER_COUNT;
            if (isContinue()) break;
        } while (!(IS_STOP));
        if (WIN_BOT_COUNT > WIN_USER_COUNT) {
            winMessage = "Win bot";
        } else if (WIN_BOT_COUNT < WIN_USER_COUNT) {
            winMessage = "Win " + userName;
        }
        System.out.println(winMessage);
        log.info(winMessage);
        log.info("APP '{}' finished success", appName.toUpperCase());
        FileUtils.readFile(LOG_FILE);
    }

    private String createUserName() {
        return Validator.validateInputText();
    }

    private void userWin(Property botValue, int x, Property userValue, int x1) {
        if (botValue.getNumber() == x && userValue.getNumber() == x1) {
            WIN_USER_COUNT++;
            logGames(botValue, userValue);
        }
    }

    private void botWin(Property botValue, int x, Property userValue, int x1) {
        if (botValue.getNumber() == x && userValue.getNumber() == x1) {
            WIN_BOT_COUNT++;
            logGames(botValue, userValue);
        }
    }

    private boolean isContinue() {
        if (!IS_STOP) {
            System.out.println("Do you want continue?");
            if ((Validator.Action.validateAction().equals(Validator.Action.NO))) {
                log.info("Win bot, because user stop the game");
                WIN_BOT_COUNT = WIN_USER_COUNT + 1;
                return true;
            }
        }
        return false;
    }

    private void logGames(Property botValue, Property userValue) {
        log.info("game {}, random win - {}:, user win - {},  random value {}, user value {}", GAMES++, WIN_BOT_COUNT, WIN_USER_COUNT, botValue.name(), userValue.name());
    }

    private Property createRandomValue() {
        return Property.values()[RANDOM.nextInt(Property.values().length)];
    }

    private Property createUserValue() {
        System.out.println("Please, choose your value: ");
        Property.printParameters();
        return Property.validateParameter();
    }

    private int chances() {
        int number = Utils.getInt();
        if (number == 0) {
            System.out.println(number + " can't by zero. Try again");
            log.info("number {} - is zero", number);
            return chances();
        }
        if (!Validator.isPositive(number)) {
            System.out.println(number + " is not positive. Try again");
            log.info("not positive {}", number);
            return chances();
        }
        return number;
    }
}