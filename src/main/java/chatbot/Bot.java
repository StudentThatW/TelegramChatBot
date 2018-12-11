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
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

public class Bot extends TelegramLongPollingBot {
//    private Map<String, MainDialog> dialogs = new HashMap<String, MainDialog>();
    private ConcurrentMap<String, MainDialog> dialogs = new ConcurrentHashMap<String, MainDialog>();

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
//            if (!dialogs.containsKey(chatId))
//                dialogs.put(chatId, new MainDialog());
//            Answer answer = dialogs.get(chatId).getAnswer(text);
            dialogs.putIfAbsent(chatId, new MainDialog());
            Answer answer = dialogs.get(chatId).getAnswer(text);
            sendMsg(message, answer);
        }
    }

    private ReplyKeyboardMarkup getReplyKeyboardMarkup(Answer answer) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<List<String>> keyboardList = answer.getKeyboard();
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        for (List<String> row : keyboardList) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (String line : row)
                keyboardRow.add(line);
            keyboard.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    @SuppressWarnings("deprecation")
    private void sendMsg(Message message, Answer answer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(getReplyKeyboardMarkup(answer));
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(answer.getText() + answer.getEmojiLine());
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