package chatbot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class NumberForGame {
    private String generatedNumber;
    private int length;

    NumberForGame(int length) {
        this.length = length;
        generatedNumber = this.generateNumber();
    }

    private String generateNumber() {
        StringBuilder generatedNumber = new StringBuilder();
        List<String> notUsedDigits = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
            notUsedDigits.add(Integer.toString(i));
        for (int i = 0; i < length; i++) {
            int size = notUsedDigits.size();
            int randomIndex = (int)(Math.random() * size);
            generatedNumber.append(notUsedDigits.get(randomIndex));
            notUsedDigits.set(randomIndex, notUsedDigits.get(size - 1));
            notUsedDigits.remove(size - 1);
        }
        return generatedNumber.toString();
    }

    Tuple<Integer> compare(String userNumber) {
        return compare(generatedNumber, userNumber);
    }

    static Tuple<Integer> compare(String generatedNumber, String userNumber) {
        Set<Character> digits = new HashSet<Character>();
        int length = generatedNumber.length();
        for (int i = 0; i < length; i++)
            digits.add(generatedNumber.charAt(i));
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < length; i++) {
            char symbol = userNumber.charAt(i);
            if (generatedNumber.charAt(i) == symbol)
                bulls++;
            else if (digits.contains(symbol))
                cows++;
        }
        return new Tuple<Integer>(bulls, cows);
    }

    boolean isEqual(String userNumber) {
        return generatedNumber.equals(userNumber);
    }

    String getGeneratedNumber() {
        return generatedNumber;
    }

    int getLength() {
        return length;
    }
}