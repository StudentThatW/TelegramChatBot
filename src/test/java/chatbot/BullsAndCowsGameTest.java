package chatbot;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BullsAndCowsGameTest {

    @Test
    void getAnswerWrongInputs() {
        List<String> incorrectLengths = new ArrayList<String>();
        incorrectLengths.add("1");
        incorrectLengths.add("12");
        incorrectLengths.add("a");
        Map<Integer, String> notDigitsInputs = new HashMap<Integer, String>();
        notDigitsInputs.put(2, "1a");
        notDigitsInputs.put(3, "b2c");
        notDigitsInputs.put(4, "?d4!");
        List<String> repeatedDigitsInputs = new ArrayList<String>();
        repeatedDigitsInputs.add("11");
        repeatedDigitsInputs.add("232");
        repeatedDigitsInputs.add("4545");
        for (int length = 2; length <= 4; length++) {
            BullsAndCowsGame game = new BullsAndCowsGame();
            String answer = game.getAnswer("/game");
            assertEquals(BullsAndCowsAnswers.numberLengthRequestLine, answer);
            for (String incorrectLength : incorrectLengths) {
                answer = game.getAnswer(incorrectLength);
                assertEquals(BullsAndCowsAnswers.inncorrectLengthLine, answer);
            }
            answer = game.getAnswer(Integer.toString(length));
            assertEquals(BullsAndCowsAnswers.NumberGenerationLine, answer);
            answer = game.getAnswer(notDigitsInputs.get(length));
            assertEquals(BullsAndCowsAnswers.notDigitsLine, answer);
            for (String repeatedDigitsInput : repeatedDigitsInputs) {
                answer = game.getAnswer(repeatedDigitsInput);
                if (repeatedDigitsInput.length() == length)
                    assertEquals(BullsAndCowsAnswers.repeatedDigitsLine, answer);
                else
                    assertEquals(BullsAndCowsAnswers.getWrongNumberLengthLine(length), answer);
            }
        }
    }

    @Test
    void getAnswer() {
        for (int length = 2; length <= 4; length++) {
            BullsAndCowsGame game = new BullsAndCowsGame();
            String answer = game.getAnswer("/game");
            assertEquals(BullsAndCowsAnswers.numberLengthRequestLine, answer);
            answer = game.getAnswer(Integer.toString(length));
            assertEquals(BullsAndCowsAnswers.NumberGenerationLine, answer);
            String generatedNumber = game.getGeneratedNumber().getGeneratedNumber();
            int numberOfGuesses = 10;
            for (int i = 0; i < numberOfGuesses; i++) {
                NumberForGame randomNumber = new NumberForGame(length);
                answer = game.getAnswer(randomNumber.getGeneratedNumber());
                if (randomNumber.isEqual(generatedNumber)) {
                    assertEquals(BullsAndCowsAnswers.getWonLine(i + 1), answer);
                    break;
                }
                Tuple<Integer> comparisonResult = randomNumber.compare(generatedNumber);
                assertEquals(BullsAndCowsAnswers.getBullsAndCowsLine(comparisonResult, i + 2), answer);
            }
            answer = game.getAnswer(generatedNumber);
            assertEquals(BullsAndCowsAnswers.getWonLine(numberOfGuesses + 1), answer);
            answer = game.getAnswer("I want to play again");
            assertEquals(BullsAndCowsAnswers.numberLengthRequestLine, answer);
        }
    }
}