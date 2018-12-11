package chatbot;

class HanoiTowerAnswers {
    static final String discsCountRequestLine = "Go play hanoi tower.\n" +
            "How tall should I make the tower? Enter a number from 2 to 9";
    static final String incorrectCountLine = "It is incorrect height for tower. Please enter a number from 2 to 9";
    static String getGameCreationLine(int count) {
        return "I made a tower. Try to move all the disks from first rod to third.\n" +
                "Minimal number of steps is " + Math.round(Math.pow(2, count) - 1) + "\n" +
                "You can put only smaller disks on larger ones. " + 1 + " Step. Make a move";
    }
    static final String wrongCommandTextLine = "Wrong input. Please use buttons";
    static final String wrongMoveLine = "Wrong move. You can put only smaller disks on larger ones";
    static String getNumberOfStepsLine(int steps) {
        return steps + " Step";
    }
    static String getWonLine(int steps) {
        return "You won! You move all the disks to third rod! Number of steps: " + steps + "\n" +
                "But this is not the minimum number of steps. You can try to do better\n" +
                "Input /start to see bot introduction. Input any message if you want to play again";
    }
    static String getWonLineMinimalSteps(int steps) {
        return "You won! You move all the disks to third rod! Number of steps: " + steps + "\n" +
                "This is the minimum number of steps. It is perfectly played!\n" +
                "Input /start to see bot introduction. Input any message if you want to play again";
    }

    static final String [][] towersButtonTexts = {
            {"", "1>2", "1>>3"},
            {"1<2", "", "2>3"},
            {"1<<3", "2<3", ""}
    };

    static final String [] disksSymbols = {
            "⬜", "1⃣", "2⃣", "3⃣", "4⃣", "5⃣", "6⃣", "7⃣", "8⃣", "9⃣"
    };
}
