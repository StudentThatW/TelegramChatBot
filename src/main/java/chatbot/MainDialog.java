package chatbot;

class MainDialog {
    private BullsAndCowsGame bullsAndCowsGame;
    private HanoiTowerGame hanoiTowerGame;

    Answer getAnswer(String input) {
        Answer answer = new Answer();

        if (input.equals("/start")) {
            bullsAndCowsGame = null;
            hanoiTowerGame = null;
            answer.addStartCommandsRow();
            answer.addText(MainDialogAnswers.introductionLine);
            return answer;
        }
        if (bullsAndCowsGame == null && hanoiTowerGame == null) {
            if (input.equals("/bulls")) {
                bullsAndCowsGame = new BullsAndCowsGame();
                answer = bullsAndCowsGame.getAnswer(input);
            }
            else if (input.equals("/tower")) {
                hanoiTowerGame = new HanoiTowerGame();
                answer = hanoiTowerGame.getAnswer(input);
            }
            else {
                answer.addStartCommandsRow();
                answer.addText(MainDialogAnswers.unknownCommandLine);
                return answer;
            }
        }
        else {
            if (hanoiTowerGame != null) {
                hanoiTowerGame = input.equals("/newgame") ? new HanoiTowerGame() : hanoiTowerGame;
                answer = hanoiTowerGame.getAnswer(input);
            }
            else {
                bullsAndCowsGame = input.equals("/newgame") ? new BullsAndCowsGame() : bullsAndCowsGame;
                answer = bullsAndCowsGame.getAnswer(input);
            }
        }
        answer.addGameCommandsRow();
        return answer;
    }
}
