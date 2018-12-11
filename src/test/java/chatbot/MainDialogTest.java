package chatbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainDialogTest {

    @Test
    void getAnswer() {
        MainDialog dialog = new MainDialog();
        String answer = dialog.getAnswer("abc").getText();
        assertEquals(MainDialogAnswers.unknownCommandLine, answer);
        answer = dialog.getAnswer("/start").getText();
        assertEquals(MainDialogAnswers.introductionLine, answer);
        answer = dialog.getAnswer("abc").getText();
        assertEquals(MainDialogAnswers.unknownCommandLine, answer);
        answer = dialog.getAnswer("/bulls").getText();
        assertEquals(BullsAndCowsAnswers.numberLengthRequestLine, answer);
        answer = dialog.getAnswer("/tower").getText();
        assertEquals(BullsAndCowsAnswers.incorrectLengthLine, answer);
        answer = dialog.getAnswer("abc").getText();
        assertEquals(BullsAndCowsAnswers.incorrectLengthLine, answer);
        answer = dialog.getAnswer("4").getText();
        assertEquals(BullsAndCowsAnswers.NumberGenerationLine, answer);
        answer = dialog.getAnswer("/newgame").getText();
        assertEquals(BullsAndCowsAnswers.numberLengthRequestLine, answer);
        answer = dialog.getAnswer("/start").getText();
        assertEquals(MainDialogAnswers.introductionLine, answer);
        answer = dialog.getAnswer("/tower").getText();
        assertEquals(HanoiTowerAnswers.discsCountRequestLine, answer);
        answer = dialog.getAnswer("/bulls").getText();
        assertEquals(HanoiTowerAnswers.incorrectCountLine, answer);
        answer = dialog.getAnswer("abc").getText();
        assertEquals(HanoiTowerAnswers.incorrectCountLine, answer);
        answer = dialog.getAnswer("4").getText();
        assertEquals(HanoiTowerAnswers.getGameCreationLine(4), answer);
        answer = dialog.getAnswer("/newgame").getText();
        assertEquals(HanoiTowerAnswers.discsCountRequestLine, answer);
        answer = dialog.getAnswer("/start").getText();
        assertEquals(MainDialogAnswers.introductionLine, answer);
        answer = dialog.getAnswer("abc").getText();
        assertEquals(MainDialogAnswers.unknownCommandLine, answer);
    }
}
