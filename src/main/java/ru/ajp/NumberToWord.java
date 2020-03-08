package ru.ajp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ajp
 * @date 08.03.2020
 */
public class NumberToWord {
    private static final int MAX = 10_000;
    private static final int DOZEN = 10;
    private static final int ONE_HUNDRED = 100;
    private static final int ONE_THOUSAND = 1000;
    private static final int UNIT_MAX_VAL = 20;
    private static final int NUMBER_LENGTH_TWO = 2;
    private static final int NUMBER_LENGTH_THREE = 3;
    private static final int NUMBER_LENGTH_FORE = 4;

    private static final String DEFAULT = "десять тысяч";
    private static final String ZERO = "ноль";
    private static final String SPACE_STR = " ";

    private static final Map<Integer, String> HUNDRED_MAP = new HashMap();
    static {
        Integer[] keys = { 100, 200, 300, 400, 500, 600, 700, 800, 900 };
        String[] values = { "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот" };
        for (int i = 0; i < keys.length; i++) {
            HUNDRED_MAP.put(keys[i], values[i]);
        }
    }

    private static final Map<Integer, String> THOUSANDS_MAP = new HashMap();
    static {
        Integer[] keys = { 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000 };
        String[] values = { "одна тысячв", "две тысячи", "три тысячи", "четыре тысячи", "пять тысяч", "шесть тысяч", "семь тысяч", "восемь тысяч", "девять тысяч" };
        for (int i = 0; i < keys.length; i++) {
            THOUSANDS_MAP.put(keys[i], values[i]);
        }
    }

    private static final String[] UNITS = { "", "один", "два", "три", "четыре", "пять", "шесь", "семь", "восемь", "девять", "десять",
            "одинадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };
    private static final String[] TENS = {
            "", //0
            "", //1
            "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    };
    private static final String[] HUNDREDS = { "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот" };
    private static final String[] THOUSANDS = { "одна тысяча", "две тысячи", "три тысячи", "четыре тысячи", "пять тысяч",
            "шесть тысяч", "семь тысяч", "восемь тысяч", "девять тысяч"
    };

    /**
     * Конвертация целого числа в число прописью
     *
     * было 24      стало двадцать четыре
     * было 102     стало сто два
     * было 368     стало триста шестьдесят восемь
     * было 1867    стало одна тысяча восемьсот шестьдесят семь
     *
     * @param number число
     * @return число прописью
     */
    public String convert(final Integer number) {
        if (number == null) { return ""; }
        if (number >= MAX) { return DEFAULT; }
        if (number == 0) { return ZERO; }

        if (number < UNIT_MAX_VAL) {
            return UNITS[number].trim();
        } else {
            switch (number.toString().length()) {
                case NUMBER_LENGTH_TWO:
                    return String.join("", getTens(number), getBlank(number), getUnit(number));
                case NUMBER_LENGTH_THREE:
                    return convertHundred(number);
                case NUMBER_LENGTH_FORE:
                    return convertThousand(number);
                default: return DEFAULT;
            }
        }
    }

    /**
     * Конвертация сотой части числа в число прописью
     *
     * @param number число
     * @return число прописью
     */
    private String convertHundred(final Integer number) {
        if (HUNDRED_MAP.containsKey(number)) {
            return HUNDRED_MAP.get(number);
        } else {
            return String.join("",
                    getHundred(number),
                    SPACE_STR,
                    convert(number % ONE_HUNDRED));
        }
    }

    /**
     * Конвертация тысячной части числа в число прописью
     *
     * @param number число
     * @return число прописью
     */
    private String convertThousand(final Integer number) {
        if (THOUSANDS_MAP.containsKey(number)) {
            return THOUSANDS_MAP.get(number);
        } else {
            return String.join("",
                    getThousand(number),
                    SPACE_STR,
                    convert(number % ONE_THOUSAND));
        }
    }

    /**
     * Возвращает числа прописью
     *
     * @param number число
     * @return число прописью
     */
    private String getUnit(final Integer number) {
        return UNITS[number % DOZEN];
    }

    /**
     * Возвращает десятичную часть числа прописью
     *
     * @param number число
     * @return число прописью
     */
    private String getTens(final Integer number) {
        return TENS[number / DOZEN];
    }

    /**
     * Возвращает сотую часть числа прописью
     *
     * @param number число
     * @return число прописью
     */
    private String getHundred(final Integer number) {
        return HUNDREDS[(number / ONE_HUNDRED) - 1];
    }

    /**
     * Возвращает тысячную часть числа прописью
     *
     * @param number число
     * @return число прописью
     */
    private String getThousand(final Integer number) {
        return THOUSANDS[(number / ONE_THOUSAND) - 1];
    }

    /**
     * Добавление отступа между двумя словами
     *
     * @param number число
     * @return если число десятичное, то строка с отступом, иначе пустая строка
     */
    private String getBlank(final Integer number) {
        return (number % DOZEN != 0) ? SPACE_STR : "";
    }

}
