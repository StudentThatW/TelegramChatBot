package chatbot;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HanoiTowerTest {

    @Test
    void getAnswer() {
        HanoiTowerGame game = new HanoiTowerGame();
        String answer = game.getAnswer("/newgame").getText();
        assertEquals(HanoiTowerAnswers.discsCountRequestLine, answer);
        answer = game.getAnswer("a").getText();
        assertEquals(HanoiTowerAnswers.incorrectCountLine, answer);
        int count = 3;
        answer = game.getAnswer(Integer.toString(count)).getText();
        assertEquals(HanoiTowerAnswers.getGameCreationLine(count), answer);
        answer = game.getAnswer("hi").getText();
        assertEquals(HanoiTowerAnswers.wrongCommandTextLine, answer);
        String[][] commands = HanoiTowerAnswers.towersButtonTexts.clone();
        answer = game.getAnswer(commands[0][2]).getText();
        int steps = 2;
        assertEquals(HanoiTowerAnswers.getNumberOfStepsLine(steps++), answer);
        answer = game.getAnswer(commands[0][2]).getText();
        assertEquals(HanoiTowerAnswers.wrongMoveLine, answer);
        answer = game.getAnswer(commands[0][1]).getText();
        assertEquals(HanoiTowerAnswers.getNumberOfStepsLine(steps++), answer);
        answer = game.getAnswer(commands[1][2]).getText();
        assertEquals(HanoiTowerAnswers.wrongMoveLine, answer);
        answer = game.getAnswer(commands[2][1]).getText();
        assertEquals(HanoiTowerAnswers.getNumberOfStepsLine(steps++), answer);
        answer = game.getAnswer(commands[0][2]).getText();
        assertEquals(HanoiTowerAnswers.getNumberOfStepsLine(steps++), answer);
        answer = game.getAnswer(commands[1][0]).getText();
        assertEquals(HanoiTowerAnswers.getNumberOfStepsLine(steps++), answer);
        answer = game.getAnswer(commands[1][2]).getText();
        assertEquals(HanoiTowerAnswers.getNumberOfStepsLine(steps), answer);
        answer = game.getAnswer(commands[0][2]).getText();
        assertEquals(HanoiTowerAnswers.getWonLineMinimalSteps(steps), answer);
        answer = game.getAnswer("I want to play again").getText();
        assertEquals(HanoiTowerAnswers.discsCountRequestLine, answer);
    }

    @Test
    void getAnswerEmojiLines() {
        HanoiTowerGame game = new HanoiTowerGame();
        String answer = game.getAnswer("/newgame").getEmojiLine();
        assertEquals("", answer);
        answer = game.getAnswer("a").getEmojiLine();
        assertEquals("", answer);
        int count = 3;
        answer = game.getAnswer(Integer.toString(count)).getEmojiLine();
        assertEquals(getEmojiLineFromDigitsLine("100200300", 3), answer);
        answer = game.getAnswer("hi").getEmojiLine();
        assertEquals("", answer);
        String[][] commands = HanoiTowerAnswers.towersButtonTexts;
        answer = game.getAnswer(commands[0][2]).getEmojiLine();
        int steps = 2;
        assertEquals(getEmojiLineFromDigitsLine("000200301", 3), answer);
        answer = game.getAnswer(commands[0][2]).getEmojiLine();
        assertEquals("", answer);
        answer = game.getAnswer(commands[0][1]).getEmojiLine();
        assertEquals(getEmojiLineFromDigitsLine("000000321", 3), answer);
        answer = game.getAnswer(commands[1][2]).getEmojiLine();
        assertEquals("", answer);
        answer = game.getAnswer(commands[2][1]).getEmojiLine();
        assertEquals(getEmojiLineFromDigitsLine("000010320", 3), answer);
        answer = game.getAnswer(commands[0][2]).getEmojiLine();
        assertEquals(getEmojiLineFromDigitsLine("000010023", 3), answer);
        answer = game.getAnswer(commands[1][0]).getEmojiLine();
        assertEquals(getEmojiLineFromDigitsLine("000000123", 3), answer);
        answer = game.getAnswer(commands[1][2]).getEmojiLine();
        assertEquals(getEmojiLineFromDigitsLine("000002103", 3), answer);
        answer = game.getAnswer(commands[0][2]).getEmojiLine();
        assertEquals(getEmojiLineFromDigitsLine("001002003", 3), answer);
        answer = game.getAnswer("I want to play again").getEmojiLine();
        assertEquals("", answer);
    }

    private String getEmojiLineFromDigitsLine(String digitsLine, int numberOfTowers) {
        String[] emojies = HanoiTowerAnswers.disksSymbols;
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        int length = digitsLine.length();
        for (int i = 0; i < length; i++) {
            builder.append("\t");
            char digit = digitsLine.charAt(i);
            if(Character.isDigit(digit))
                builder.append(emojies[Character.digit(digit, 10)]);
            if ((i + 1) % numberOfTowers == 0)
                builder.append("\n");
        }
        return builder.toString();
    }
}