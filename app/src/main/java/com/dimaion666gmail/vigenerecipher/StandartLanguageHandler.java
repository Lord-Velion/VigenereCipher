package com.dimaion666gmail.vigenerecipher;

public class StandartLanguageHandler extends LanguageHandler {
    private int theStartInUnicode;
    private int theEndInUnicode;
    private int conversion; // Переменная для перехода между unicode и упрощённой кодировкой (порядки в алфавите, начиная с 0)
    private int alphabetLength;

    public StandartLanguageHandler(int theStartInUnicode, int theEndInUnicode) {
        this.theStartInUnicode = theStartInUnicode;
        this.theEndInUnicode = theEndInUnicode;
        this.conversion = theStartInUnicode;
        this.alphabetLength = theEndInUnicode - theStartInUnicode + 1;
    }

    @Override
    public boolean doesTheLetterExistHere(char letter) {
        letter = Character.toLowerCase(letter);

        if(theStartInUnicode <= (int)letter && (int)letter <= theEndInUnicode)
            return true;
        else
            return false;
    }

    @Override
    public int getOrderInAlphabet(char letter) {
        letter = Character.toLowerCase(letter);
        return ((int)letter - conversion + 1);
    }

    @Override
    public char shiftLetter(int shiftStep, char letter) {
        shiftStep = shiftStep % alphabetLength; // Отбрасываем лишнюю длину сдвига.

        // Если буква в верхнем регистре, то запоминаем, потом возвращаем.
        boolean isUpperCase = Character.isUpperCase(letter);
        // В алфавите мы работаем с буквами в нижнем регистре.
        letter = Character.toLowerCase(letter);

        // Получаем порядковый номер буквы в алфавите.
        int letterIndex = (int)letter - conversion;

        // Сдвигаем порядковый номер и ищем букву. Если номер уходит за границы алфавита в конце,
        // то он всё равно уходит в начало по формуле.
        letterIndex = (letterIndex + shiftStep) % alphabetLength;

        // Если номер уходит за границы алфавита в начале, то он всё равно уходит в конец по
        // условию.
        if (letterIndex < 0) {
            letterIndex = alphabetLength - Math.abs(letterIndex);
        }

        // Приводим упрощённый порядковый номер к виду unicode.
        letterIndex += conversion;

        // Получаем символ.
        char shiftedLetter = (char)letterIndex;

        // Возвращаем верхний регистр, если он был.
        if (isUpperCase) shiftedLetter = Character.toUpperCase(shiftedLetter);

        // Вовзращаем смещённую букву.
        return shiftedLetter;
    }
}