package TGLogic;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    public InlineKeyboardMarkup addInlineButtons1(InlineKeyboardButton[] button){
        List<List<InlineKeyboardButton>> keyBoard = new ArrayList<>();

        for(int i=0; i < button.length; i++ ){
            List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>();
            inlineKeyboardRow.add(button[i]);
            Collections.addAll(keyBoard, inlineKeyboardRow);
        }
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyBoard);
            return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup setInlineKeyboard(List<InlineKeyboardButton>... keyboardRowName){
        List<List<InlineKeyboardButton>> keyBoard = new ArrayList<>();
        Collections.addAll(keyBoard, keyboardRowName);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyBoard);
        return inlineKeyboardMarkup;
    }
}
