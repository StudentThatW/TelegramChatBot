package chatbot;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NumberForGameTest {

    @Test
    void generateNumber() {
        for (int length = 2; length <= 9; length++){
            for (int i = 0; i < 100; i++) {
                Set<Character> digits = new HashSet<Character>();
                String generatedNumber = new NumberForGame(length).getGeneratedNumber();
                assertEquals(length, generatedNumber.length());
                for (int j = 0; j < length; j++) {
                    char digit = generatedNumber.charAt(j);
                    assertEquals(Character.isDigit(digit), true);
                    assertEquals(digits.contains(digit), false);
                    digits.add(digit);
                }
            }
        }
    }

    @Test
    void compareAllCows() {
        Tuple<Integer> comparisonResult = NumberForGame.compare("1234", "4321");
        int bulls = comparisonResult.firstElement;
        int cows = comparisonResult.secondElement;
        assertEquals(0, bulls);
        assertEquals(4, cows);
    }

    @Test
    void compareAllBulls() {
        Tuple<Integer> comparisonResult = NumberForGame.compare("123456", "123456");
        int bulls = comparisonResult.firstElement;
        int cows = comparisonResult.secondElement;
        assertEquals(6, bulls);
        assertEquals(0, cows);
    }

    @Test
    void compareNoBullsNoCows() {
        Tuple<Integer> comparisonResult = NumberForGame.compare("1234", "5678");
        int bulls = comparisonResult.firstElement;
        int cows = comparisonResult.secondElement;
        assertEquals(0, bulls);
        assertEquals(0, cows);
    }

    @Test
    void compareOnlyCows() {
        Tuple<Integer> comparisonResult = NumberForGame.compare("1234", "5128");
        int bulls = comparisonResult.firstElement;
        int cows = comparisonResult.secondElement;
        assertEquals(0, bulls);
        assertEquals(2, cows);
    }

    @Test
    void compareOnlyBulls() {
        Tuple<Integer> comparisonResult = NumberForGame.compare("123456", "728950");
        int bulls = comparisonResult.firstElement;
        int cows = comparisonResult.secondElement;
        assertEquals(2, bulls);
        assertEquals(0, cows);
    }

    @Test
    void compareBullsAndCows() {
        Tuple<Integer> comparisonResult = NumberForGame.compare("12345", "32140");
        int bulls = comparisonResult.firstElement;
        int cows = comparisonResult.secondElement;
        assertEquals(2, bulls);
        assertEquals(2, cows);
    }
}