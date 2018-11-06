package chatbot;

class MainDialog {
    private BullsAndCowsGame game;

    String getAnswer(String input) {
        if (input.equals("/start")) {
            game = null;
            return MainDialogAnswers.introductionLine;
        }
        else if (input.equals("/game")) {
            game = new BullsAndCowsGame();
            return game.getAnswer(input);
        }
        else if (game == null)
            return MainDialogAnswers.unknownCommandLine;
        else
            return game.getAnswer(input);
    }
}
