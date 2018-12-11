package chatbot;

class BullsAndCowsAnswers {
    static final String numberLengthRequestLine = "Go play bulls and cows.\n" +
            "How long do I guess the number? Enter a number from 2 to 9";
    static final String incorrectLengthLine = "It is incorrect length for number. Please enter a number from 2 to 9";
    static final String NumberGenerationLine = "I made a number. Try to guess it\n" + 1 + " Attempt. Enter your number";
    static String getWonLine(int attempts) {
        return "You won! You guessed the hidden number! Number of attempts: " + attempts + "\n" +
                "Input /start to see bot introduction. Input any message if you want to play again";
    }
    static String getBullsAndCowsLine(Tuple<Integer> comparisonResult, int attempts) {
        return "Bulls: " + comparisonResult.firstElement + ", " + "Cows: " + comparisonResult.secondElement + "\n" +
                attempts + " Attempt. Enter your number";
    }
    static String getWrongNumberLengthLine(int length) {
        return  "You entered a number of wrong length. Enter the number with length: " + length;
    }
    static final String notDigitsLine = "There are not only digits in your input. Input must contain only digits";
    static final String repeatedDigitsLine = "You entered a number with repeated digits. " +
            "Enter a number without duplicate digits.";
}
