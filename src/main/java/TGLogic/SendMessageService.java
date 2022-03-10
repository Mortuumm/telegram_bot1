package TGLogic;

import org.checkerframework.checker.units.qual.A;
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
    private final File SHOW_LINK =  new File("D:\\DOWLOADS\\6I8qlFecAkw.jpg");
    private final TGButtonsLogic tgButtonsLogic = new TGButtonsLogic();

    public SendMessage createGreetingInformation(Update update){
        SendMessage message = createSimpleMessage(update,GREETING_MESSAGE);
        ArrayList<String> buttonList = new ArrayList<>();
        buttonList.add(START_PLANNING);
        buttonList.add(SHOW_DEALS);
        ReplyKeyboardMarkup keyboardMarkup = tgButtonsLogic.setButtons(tgButtonsLogic.createCommonButtons(buttonList));
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    public EditMessageText createInlineButtonsMessage(Update update, String infection,
                                                      String[] buttonNames, String[] buttonNumbers){
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);
        InlineKeyboardButton[] buttons = new InlineKeyboardButton[buttonNumbers.length];
        for(int i=0; i < buttonNumbers.length; i++ ){
            buttons[i] = tgButtonsLogic.createInlineButton(buttonNames[i],buttonNumbers[i]);
        }
        InlineKeyboardMarkup replyKeyboardMarkup = tgButtonsLogic.setGenerateInlineKeybord(buttons);
        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public SendMessage createButtonsMessage(Update update, String infection,
                                            ArrayList<String> buttonNames, ArrayList<String> buttonNumbers){
        SendMessage sendMessage = createSimpleMessage(update,infection );
        InlineKeyboardButton[] buttons = new InlineKeyboardButton[buttonNumbers.size()];
        for(int i=0; i < buttonNumbers.size(); i++ ){
            buttons[i] = tgButtonsLogic.createInlineButton(buttonNames.get(i),buttonNumbers.get(i));
            //tgButtonsLogic.addInlineButtons(buttons[i]);
        }
        InlineKeyboardMarkup replyKeyboardMarkup = tgButtonsLogic.setGenerateInlineKeybord(buttons);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }

    /*public SendMessage createTabletsInstruction(Update update){
        SendMessage sendMessage = createSimpleMessage(update,START_DISEASES_MESSAGE );
        InlineKeyboardButton button1 = tgButtonsLogic.createInlineButton(MENINGOC,"inf-0");
        InlineKeyboardButton button2 = tgButtonsLogic.createInlineButton(ASTHMA,"inf-1");
        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(
                        tgButtonsLogic.addInlineButtons(button1),
                        tgButtonsLogic.addInlineButtons(button2));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }*/

    public SendMessage createHelpMessage(Update update){
        return createSimpleMessage(update, HELP_MESSAGE);
    }

    public SendMessage createShowMessage(Update update){
        return createSimpleMessage(update, SHOW_MESSAGE);
    }

    public SendPhoto createShowPhoto(Update update){
        return createSimplePhoto(update, SHOW_LINK);
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
/*
    public EditMessageText createSelectMessage(Update update){
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(SELECT_MESSAGE);
        return editMessageText;
    }*/


   /* public EditMessageText createMeningocMessage(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("сыпи  нет","0-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("не геморрагическая","0-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("геморрагическая  мелкоточечная","0-3");
        InlineKeyboardButton button4 = tgButtonsLogic.
                createInlineButton("сочетание  геморрагической  и другой","0-4");
        InlineKeyboardButton button5 = tgButtonsLogic.
                createInlineButton("геморрагическая  звездчатая","0-5");
        InlineKeyboardButton button6 = tgButtonsLogic.
                createInlineButton("геморрагическая  сливная","0-6");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(
                        tgButtonsLogic.addInlineButtons(button1),
                        tgButtonsLogic.addInlineButtons(button2),
                        tgButtonsLogic.addInlineButtons(button3),
                        tgButtonsLogic.addInlineButtons(button4),
                        tgButtonsLogic.addInlineButtons(button5),
                        tgButtonsLogic.addInlineButtons(button6));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);

        return editMessageText;
    }

    public EditMessageText createAsthmaMessage(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("не отмечено","A0-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("АЗ, кроме бронхиальной астмы(БА)","A0-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("БА или сочетание БА с другими","A0-3");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(
                        tgButtonsLogic.addInlineButtons(button1),
                        tgButtonsLogic.addInlineButtons(button2),
                        tgButtonsLogic.addInlineButtons(button3));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }*/

    /*public EditMessageText createMeningocMessage1(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("на конечностях","1-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("на лице","1-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("на туловище","1-3");
        InlineKeyboardButton button4 = tgButtonsLogic.
                createInlineButton("равномерно по телу","1-4");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(
                        tgButtonsLogic.addInlineButtons(button1),
        tgButtonsLogic.addInlineButtons(button2),
        tgButtonsLogic.addInlineButtons(button3),
        tgButtonsLogic.addInlineButtons(button4));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createMeningocMessage2(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("бледная","2-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("яркая","2-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("цианиточная","2-3");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(
                        tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createMeningocMessage3(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("нет или приоральный","3-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("разлитой или акроцианоз","3-2");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createMeningocMessage4(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("удовлетворительное или средней тяжести","4-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("тяжёлое","4-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("терминальное","4-3");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(
                        tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createMeningocMessage5(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("В анамнезе <= 38,7 \n " +
                        "Сейчас >= 37,6 ","5-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("В анамнезе >= 38,7 \n " +
                        "Сейчас >= 37,6 ","5-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("В анамнезе <= 38,7 \n " +
                        "Сейчас <= 37,5 ","5-3");
        InlineKeyboardButton button4 = tgButtonsLogic.
                createInlineButton("В анамнезе >= 38,7 \n " +
                        "Сейчас <= 37,5 ","5-4");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3),
                                tgButtonsLogic.addInlineButtons(button4));
        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createMeningocMessage6(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("нет","6-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("есть","6-2");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createMeningocMessage7(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("нет или слабо выраженны","7-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("выражены умеренно или резко","7-2");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(
                        tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }



    public EditMessageText createAsthmaMessage1(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("не отмечено","A1-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("АЗ, кроме бронхиальной астмы(БА)","A1-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("БА или сочетание БА с другими","A1-3");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage2(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("не было","A2-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("любые, кроме аспирационных","A2-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("аспирационные","A2-3");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(
                        tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage3(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("отсутствие аллергизирующих факторов","A3-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("один аллергизирующий фактор","A3-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("два и более факторов","A3-3");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3));
        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage4(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("не было или до 1 месяца","A4-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("свыше 1 до 4 месяцев","A4-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("свыше 4 месяцев","A4-3");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage5(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("не было при беременности и лактации","A5-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("только в период беременности","A5-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("в период лактации","A5-3");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3));
        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage6(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("не было при беременности и лактации","A6-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("только в период беременности","A6-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("в период лактации","A6-3");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage7(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("нет кожных изменений","A7-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("эксуддативный диатез или себорейный дерматит" +
                        "не связан с введением смесей","A7-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("то же, связан с введением смесей","A7-3");
        InlineKeyboardButton button4 = tgButtonsLogic.
                createInlineButton("мокнущая экзема, не связана с введением смесей","A7-4");
        InlineKeyboardButton button5 = tgButtonsLogic.
                createInlineButton("то же, связана с введением смесей","A7-5");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3),
                                tgButtonsLogic.addInlineButtons(button4),
                                tgButtonsLogic.addInlineButtons(button5));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage8(Update update, String infection){
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("ОРВИ не болел","A8-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("ОРВИ без ОБО","A8-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("ОРВИ с ОБО однократно","A8-3");
        InlineKeyboardButton button4 = tgButtonsLogic.
                createInlineButton("ОРВИ с ОБО повторно","A8-4");
        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3),
                                tgButtonsLogic.addInlineButtons(button4));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage9(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("не было","A9-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("были","A9-2");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage10(Update update, String infection) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("не применялись","A10-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("применялись","A10-2");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage11(Update update, String infection){
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);

        InlineKeyboardButton button1 = tgButtonsLogic.
                createInlineButton("не вводился","A11-1");
        InlineKeyboardButton button2 = tgButtonsLogic.
                createInlineButton("2 раза, не сопровождалось реакциями","A11-2");
        InlineKeyboardButton button3 = tgButtonsLogic.
                createInlineButton("1 раза, не сопровождалось реакциями","A11-3");
        InlineKeyboardButton button4 = tgButtonsLogic.
                createInlineButton("сопровождалось реакциями","A11-4");

        InlineKeyboardMarkup replyKeyboardMarkup =
                tgButtonsLogic.setInlineKeyboard(tgButtonsLogic.addInlineButtons(button1),
                                tgButtonsLogic.addInlineButtons(button2),
                                tgButtonsLogic.addInlineButtons(button3),
                                tgButtonsLogic.addInlineButtons(button4));

        editMessageText.setReplyMarkup(replyKeyboardMarkup);
        return editMessageText;
    }

    public EditMessageText createAsthmaMessage12(Update update, String infection){
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(infection);
        return editMessageText;
    }*/


}
