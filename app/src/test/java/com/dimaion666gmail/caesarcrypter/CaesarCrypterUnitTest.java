package com.dimaion666gmail.caesarcrypter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaesarCrypterUnitTest {
    CaesarCrypter caesarCrypter;

    @Before
    public void setUp() throws Exception{
        caesarCrypter = new CaesarCrypter(1);
    }

    @Test
    public void translateTest() {
        assertEquals("Б", caesarCrypter.translate(1, "А"));
    }
}
