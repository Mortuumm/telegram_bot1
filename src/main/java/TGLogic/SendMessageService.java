package TGLogic;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.File;
import java.util.ArrayList;
import static TGConstant.Constant.*;
import static java.lang.Math.toIntExact;

public class SendMessageService {
    private final TGButtonsLogic tgButtonsLogic = new TGButtonsLogic();

    public SendMessage createGreetingInformation(Update update){
        SendMessage message = createSimpleMessage(update,GREETING_MESSAGE);
        ArrayList<String> buttonList = new ArrayList<>();
        buttonList.add(START_PLANNING);
        ReplyKeyboardMarkup keyboardMarkup = tgButtonsLogic.setButtons(tgButtonsLogic.createCommonButtons(buttonList));
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    public EditMessageText createInlineButtonsMessage(Update update, String infection,
                                                      ArrayList<String> buttonNames, ArrayList<String>  buttonNumbers){
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);
        ArrayList<InlineKeyboardButton> buttons = new ArrayList<>();
        for(int i=0; i < buttonNumbers.size(); i++ ){
            buttons.add(tgButtonsLogic.createInlineButton(buttonNames.get(i),buttonNumbers.get(i)));
        }
        InlineKeyboardMarkup replyKeyboardMarkup = tgButtonsLogic.setGenerateInlineKeybord(buttons);
        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public SendMessage createButtonsMessage(Update update, String infection,
                                            ArrayList<String> buttonNames, ArrayList<String> buttonNumbers){
        SendMessage sendMessage = createSimpleMessage(update,infection );
        ArrayList<InlineKeyboardButton> buttons = new ArrayList<>();
        for(int i=0; i < buttonNumbers.size(); i++ ){
            buttons.add(tgButtonsLogic.createInlineButton(buttonNames.get(i),buttonNumbers.get(i)));
        }
        InlineKeyboardMarkup replyKeyboardMarkup = tgButtonsLogic.setGenerateInlineKeybord(buttons);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage createHelpMessage(Update update){
        return createSimpleMessage(update, HELP_MESSAGE);
    }

    private SendMessage createSimpleMessage(Update update, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(message);
        return sendMessage;
    }

    private SendPhoto createSimplePhoto(Update update, File photo){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId());
        sendPhoto.setPhoto(photo);
        return sendPhoto;
    }

    public EditMessageText createExitMessage(Update update, String infection){
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);
        return editMessageText;
    }

}
