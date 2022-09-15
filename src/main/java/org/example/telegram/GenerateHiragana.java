package org.example.telegram;

import java.util.Random;

public class GenerateHiragana {
    private static final char[] cons = new char[] {'к', 'с', 'т', 'н', 'х', 'м', 'р', 'в', 'з', 'д', 'б', 'п', 'в'};
    private static final char[] vowels = new char[] {'а', 'и', 'э', 'о', 'у', 'я', 'ю', 'ё'};
    private static final char[] restVowels = new char[] {'а', 'и', 'э', 'о'};
    private static final char[] singleChars = new char[] {'я', 'ю', 'ё', 'н'};

    private static final Random random = new Random();

    public static String randomHiragana() {
        StringBuilder result = new StringBuilder();
        int num = random.nextInt(100 + 1);
        if (num < 10) {
            result.append(randInArray(singleChars));
        } else {
            char con = randInArray(cons);

            if (con == 'з') {
                result.append('д');
            }

            if (con == 'в') {
                result.append(con).append(randInArray(restVowels));
            } else {
                result.append(con).append(randInArray(vowels));
            }

            if (result.toString().equals("ху")) {
                result.replace(0, 1, "ф");
            }
        }

        return result.toString();
    }

    private static char randInArray(char[] array) {
        return array[random.nextInt(array.length)];
    }
}
