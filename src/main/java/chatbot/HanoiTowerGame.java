package chatbot;

import java.util.ArrayList;
import java.util.List;

class HanoiTowerGame {
    private final static String [][] towersButtonTexts = HanoiTowerAnswers.towersButtonTexts.clone();

    private final static String [] disksSymbols = HanoiTowerAnswers.disksSymbols.clone();

    HanoiTowerGame () {
        toInitialState();
    }

    private void toInitialState() {
        count = 0;
        steps = 1;
        stage = GameStage.discsCountRequest;
        number_of_towers = 3;
        towers = new Tower[number_of_towers];
        for (int i = 0; i < number_of_towers; i++)
            towers[i] = new Tower();
        buttonTextsRow = getButtonTextsRow();
    }

    private enum GameStage {
        discsCountRequest,
        gameCreation,
        towerAssembly
    }

    private Tower[] towers;
    private GameStage stage;
    private int steps;
    private int count;
    private int number_of_towers;
    private List<String> buttonTextsRow;

    Answer getAnswer(String input) {
        Answer answer = new Answer();
        if (stage == GameStage.discsCountRequest) {
            stage = GameStage.gameCreation;
            answer.addNumbersRow(2, 9);
            answer.addText(HanoiTowerAnswers.discsCountRequestLine);
            return answer;
        }
        if (stage == GameStage.gameCreation) {
            if (!isCorrectCount(input)) {
                answer.addText(HanoiTowerAnswers.incorrectCountLine);
                return answer;
            }
            stage = GameStage.towerAssembly;
            count = Integer.parseInt(input);
            towers[0].fillTower(count);
            answer.addKeyboardRow(buttonTextsRow);
            answer.addText(HanoiTowerAnswers.getGameCreationLine(count));
            answer.addEmojiLine(getDiscsString());
            return answer;
        }
        for (int i = 0; i < number_of_towers; i++) {
            for (int j = 0; j < number_of_towers; j++) {
                if (i != j && input.equals(towersButtonTexts[i][j])) {
                    if (!towers[i].shiftDisk(towers[j]))
                        answer.addText(HanoiTowerAnswers.wrongMoveLine);
                    else if (towers[number_of_towers - 1].getCount() == count) {
                        if (steps == Math.round(Math.pow(2, count) - 1))
                            answer.addText(HanoiTowerAnswers.getWonLineMinimalSteps(steps));
                        else
                            answer.addText(HanoiTowerAnswers.getWonLine(steps));
                        answer.addEmojiLine(getDiscsString());
                        toInitialState();
                        return answer;
                    }
                    else {
                        steps++;
                        answer.addText(HanoiTowerAnswers.getNumberOfStepsLine(steps));
                        answer.addEmojiLine(getDiscsString());
                    }
                    answer.addKeyboardRow(buttonTextsRow);
                    return answer;
                }
            }
        }
        answer.addText(HanoiTowerAnswers.wrongCommandTextLine);
        return answer;
    }

    private boolean isCorrectCount(String line) {
        if (line.length() != 1 || !Character.isDigit(line.charAt(0)))
            return false;
        int number = Integer.parseInt(line);
        return number >= 2 && number <= 9;
    }

    private List<String> getButtonTextsRow() {
        List<String> row = new ArrayList<String>();
        for (int i = 0; i < number_of_towers; i++) {
            for (int j = 0; j < number_of_towers; j++) {
                if (i != j)
                    row.add(towersButtonTexts[i][j]);
            }
        }
        return row;
    }

    private String getDiscsString() {
        List<List<Integer>> discs_lists = new ArrayList<List<Integer>>();
        for (Tower tower : towers) {
            List<Integer> discs = tower.getDiscs();
            while (discs.size() < count)
                discs.add(0);
            discs_lists.add(discs);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int i = count - 1; i >= 0; i--) {
            StringBuilder lineBuilder = new StringBuilder();
            for (List<Integer> discs : discs_lists) {
                lineBuilder.append("\t");
                lineBuilder.append(disksSymbols[discs.remove(i)]);
            }
            builder.append(lineBuilder.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
