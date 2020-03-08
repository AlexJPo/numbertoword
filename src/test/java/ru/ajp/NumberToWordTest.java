package ru.ajp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ajp
 * @date 08.03.2020
 */
class NumberToWordTest {
    private NumberToWord numberToWord = new NumberToWord();

    @Test
    public void convert() {
        assertEquals("двадцать четыре", numberToWord.convert(24));
        assertEquals("сто два", numberToWord.convert(102));
        assertEquals("триста шестьдесят восемь", numberToWord.convert(368));
        assertEquals("одна тысяча восемьсот шестьдесят семь", numberToWord.convert(1867));
    }
}