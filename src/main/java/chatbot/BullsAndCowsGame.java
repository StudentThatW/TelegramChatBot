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

    String getAnswer(String input) {
        if (stage == GameStage.numberLengthRequest) {
            stage = GameStage.numberGeneration;
            return BullsAndCowsAnswers.numberLengthRequestLine;
        }
        if (stage == GameStage.numberGeneration) {
            if (!isCorrectLength(input))
                return BullsAndCowsAnswers.inncorrectLengthLine;
            int length = Integer.parseInt(input);
            generatedNumber = new NumberForGame(length);
            stage = GameStage.guessingNumber;
            return BullsAndCowsAnswers.NumberGenerationLine;
        }
        if (generatedNumber.isEqual(input)){
            String answer = BullsAndCowsAnswers.getWonLine(attempts);
            toInitialState();
            return answer;
        }
        String checkResult = checkNumber(input);
        if (!checkResult.equals("")) {
            return checkResult;
        }
        Tuple<Integer> comparisonResult = generatedNumber.compare(input);
        attempts++;
        return BullsAndCowsAnswers.getBullsAndCowsLine(comparisonResult, attempts);
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
