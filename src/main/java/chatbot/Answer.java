package chatbot;

import java.util.ArrayList;
import java.util.List;

class Answer {
    private String emojiLine = "";
    private String text;
    private List<List<String>> keyboard;

    Answer() {
        keyboard = new ArrayList<List<String>>();
    }

    void addEmojiLine(String line) {
        emojiLine = line;
    }

    void  addText(String text) {
        this.text = text;
    }

    void addKeyboardRow(List<String> keyboardRow) {
        keyboard.add(keyboardRow);
    }

    void addStartCommandsRow() {
        List<String> commandsRow = new ArrayList<String>();
        commandsRow.add("/start");
        commandsRow.add("/bulls");
        commandsRow.add("/tower");
        keyboard.add(commandsRow);
    }

    void addGameCommandsRow() {
        List<String> commandsRow = new ArrayList<String>();
        commandsRow.add("/start");
        commandsRow.add("/newgame");
        keyboard.add(commandsRow);
    }

    void addNumbersRow(int start, int finish) {
        List<String> numbersRow = new ArrayList<String>();
        for (int i = start; i <= finish; i++)
            numbersRow.add(Integer.toString(i));
        keyboard.add(numbersRow);
    }

    String getText() {
        return text;
    }

    String getEmojiLine() {
        return emojiLine;
    }

    List<List<String>> getKeyboard() {
        return keyboard;
    }
}
