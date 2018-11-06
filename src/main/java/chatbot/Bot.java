package chatbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    private Map<String, MainDialog> dialogs = new HashMap<String, MainDialog>();

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botApi = new TelegramBotsApi();
        try {
            botApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return System.getenv("BOTNAME");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String text = message.getText();
            String chatId = message.getChatId().toString();
            if (!dialogs.containsKey(chatId))
                dialogs.put(chatId, new MainDialog());
            String answer = dialogs.get(chatId).getAnswer(text);
            sendMsg(message, answer);
        }
    }

    private ReplyKeyboardMarkup getReplyKeyboardMarkup(String text) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        if (text.equals(BullsAndCowsAnswers.numberLengthRequestLine)) {
            KeyboardRow numbersRow = new KeyboardRow();
            for (int i = 2; i <= 9; i++)
                numbersRow.add(Integer.toString(i));
            keyboard.add(numbersRow);
        }
        KeyboardRow commandsRow = new KeyboardRow();
        commandsRow.add("/start");
        commandsRow.add("/game");
        keyboard.add(commandsRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    @SuppressWarnings("deprecation")
    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(getReplyKeyboardMarkup(text));
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOTTOKEN");
    }
}