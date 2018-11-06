package chatbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainDialogTest {

    @Test
    void getAnswer() {
        MainDialog dialog = new MainDialog();
        String answer = dialog.getAnswer("abc");
        assertEquals(MainDialogAnswers.unknownCommandLine, answer);
        answer = dialog.getAnswer("/start");
        assertEquals(MainDialogAnswers.introductionLine, answer);
        answer = dialog.getAnswer("abc");
        assertEquals(MainDialogAnswers.unknownCommandLine, answer);
        answer = dialog.getAnswer("/game");
        assertEquals(BullsAndCowsAnswers.numberLengthRequestLine, answer);
        answer = dialog.getAnswer("abc");
        assertEquals(BullsAndCowsAnswers.inncorrectLengthLine, answer);
        answer = dialog.getAnswer("/game");
        assertEquals(BullsAndCowsAnswers.numberLengthRequestLine, answer);
        answer = dialog.getAnswer("/start");
        assertEquals(MainDialogAnswers.introductionLine, answer);
        answer = dialog.getAnswer("abc");
        assertEquals(MainDialogAnswers.unknownCommandLine, answer);
    }
}
