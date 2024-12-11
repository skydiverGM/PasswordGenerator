import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassGenTest {
    private static PassGen passGen;

    @BeforeAll
    static void setUp() {
        passGen = new PassGen();
    }

    @Test
    void testMinLength() {
        String pass = passGen.generatePassword(7);
        assertEquals(8, pass.length(), "Password should have a minimum length of 8 characters.");
    }

    @Test
    void testLength(){
        int length = 15;
        String pass = passGen.generatePassword(length);
        assertEquals(length, pass.length(),"Password length should match the requested length.");
    }

    @Test
    void testContainsAllCharTypes(){
        String pass = passGen.generatePassword(8);

        boolean hasUppercase = pass.chars().anyMatch(Character::isUpperCase);
        boolean hasLowercase = pass.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = pass.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = pass.chars().anyMatch(c -> "!@#$%&*()_+-=[]?".indexOf(c) != -1);

        assertTrue(hasUppercase, "Password should contain at least one uppercase letter.");
        assertTrue(hasLowercase, "Password should contain at least one lowercase letter.");
        assertTrue(hasDigit, "Password should contain at least one digit.");
        assertTrue(hasSpecialChar, "Password should contain at least one special character.");
    }

    @Test
    void testRandomness(){
        String pass1 = passGen.generatePassword(8);
        String pass2 = passGen.generatePassword(8);

        assertNotEquals(pass1, pass2, "Passwords should be unique across multiple calls.");
    }

    @Test
    void testLargePass(){
        int length = 1000;
        String pass = passGen.generatePassword(length);

        assertEquals(length, pass.length(), "Password length should support very large values.");
    }
}
