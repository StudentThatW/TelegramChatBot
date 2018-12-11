package chatbot;

import java.util.HashSet;
import java.util.Set;

class BullsAndCowsGame {
    BullsAndCowsGame() {
        toInitialState();
    }

    private enum GameStage {
        numberLengthRequest,
        numberGeneration,
        guessingNumber
    }

    private int attempts;
    private GameStage stage;
    private NumberForGame generatedNumber;

    private void toInitialState() {
        stage = GameStage.numberLengthRequest;
        attempts = 1;
        generatedNumber = null;
    }

    Answer getAnswer(String input) {
        Answer answer = new Answer();
        if (stage == GameStage.numberLengthRequest) {
            stage = GameStage.numberGeneration;
            answer.addNumbersRow(2, 9);
            answer.addText(BullsAndCowsAnswers.numberLengthRequestLine);
            return answer;
        }
        if (stage == GameStage.numberGeneration) {
            if (!isCorrectLength(input)) {
                answer.addText(BullsAndCowsAnswers.incorrectLengthLine);
                return answer;
            }
            int length = Integer.parseInt(input);
            generatedNumber = new NumberForGame(length);
            stage = GameStage.guessingNumber;
            answer.addText(BullsAndCowsAnswers.NumberGenerationLine);
            return answer;
        }
        if (generatedNumber.isEqual(input)){
            answer.addText(BullsAndCowsAnswers.getWonLine(attempts));
            toInitialState();
            return answer;
        }
        String checkResult = checkNumber(input);
        if (!checkResult.equals("")) {
            answer.addText(checkResult);
            return answer;
        }
        Tuple<Integer> comparisonResult = generatedNumber.compare(input);
        attempts++;
        answer.addText(BullsAndCowsAnswers.getBullsAndCowsLine(comparisonResult, attempts));
        return answer;
    }

    private boolean isCorrectLength(String line) {
        if (line.length() != 1 || !Character.isDigit(line.charAt(0)))
            return false;
        int number = Integer.parseInt(line);
        return number >= 2 && number <= 9;
    }

    private String checkNumber(String number) {
        int length = generatedNumber.getLength();
        if (number.length() != length)
            return BullsAndCowsAnswers.getWrongNumberLengthLine(length);
        Set<Character> digits = new HashSet<Character>();
        for (int i = 0; i < length; i++) {
            char digit = number.charAt(i);
            if (!Character.isDigit(digit))
                return BullsAndCowsAnswers.notDigitsLine;
            if (digits.contains(number.charAt(i)))
                return BullsAndCowsAnswers.repeatedDigitsLine;
            digits.add(digit);
        }
        return "";
    }

    NumberForGame getGeneratedNumber() {
        return generatedNumber;
    }
}
