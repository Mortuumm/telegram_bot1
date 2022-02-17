package TGLogic;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TGButtonsLogic {

    public ReplyKeyboardMarkup setButtons(List<KeyboardRow> keyboardRowList){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public List<KeyboardRow> createButtons(List<String> buttons){
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        for (String buttonName : buttons){
            keyboardRow.add(new KeyboardButton(buttonName));
        }
        keyboardRows.add(keyboardRow);
        return keyboardRows;
    }
    public InlineKeyboardButton createInlineButton(String buttonName,String number){
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonName);
        inlineKeyboardButton.setCallbackData(number);

        return inlineKeyboardButton;
    }

    public List<InlineKeyboardButton> addInlineButtons(InlineKeyboardButton button){
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>();
        inlineKeyboardRow.add(button);
        return inlineKeyboardRow;
    }

    @SafeVarargs
    public final List<List<InlineKeyboardButton>> createInlineButtons(List<InlineKeyboardButton>... keyboardRowName){
        List<List<InlineKeyboardButton>> keyBoard = new ArrayList<>();
        Collections.addAll(keyBoard, keyboardRowName);
        return keyBoard;
    }

    public InlineKeyboardMarkup setInlineKeyboard(List<List<InlineKeyboardButton>> keyboardList){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboardList);
        return inlineKeyboardMarkup;
    }
}
