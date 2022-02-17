package TGController;

import TGLogic.SendMessageService;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static TGConstant.Constant.*;

public class TGJavaBotController extends TelegramLongPollingBot {
    private static final String TOKEN = "2018829476:AAFcvR0sF_Y63CdZ3r9frf97hqIxvZLzNNs";
    private static final String USERNAME ="DiplomJavaStudy_Bot";
    public TGJavaBotController(DefaultBotOptions options) { super(options); }

    @Override
    public void onUpdateReceived(Update update) {
        /*
        update.getUpdateId();
        chat_id = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        String text = update.getMessage().getText();
        sendMessage.setReplyMarkup(tgMessageLogic.getInlineKeyboardMarkup());

        System.out.println(update);

        try{

            sendMessage.setText(tgMessageLogic.getMessage(text));
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
         */

        SendMessageService sendMessageService = new SendMessageService();
        if(update.hasMessage() && update.getMessage().hasText()){
            System.out.println(update.getMessage().getFrom().getId());
            switch (update.getMessage().getText()) {
                case START ->
                        //executeMessage(sendMessageService.createPhoneMessage(update));
                        executeMessage(sendMessageService.createGreetingInformation(update));
                case START_PLANNING ->
                        //executeMessage(sendMessageService.createPlanningMessage(update));
                        executeMessage(sendMessageService.createTabletsInstruction(update));
                case END_PLANNING -> executeMessage(sendMessageService.createEndMessage(update));
                case HELP -> executeMessage(sendMessageService.createHelpMessage(update));
                case SHOW_DEALS -> {
                    executeMessage(sendMessageService.createShowMessage(update));
                    executePhoto(sendMessageService.createShowPhoto(update));
                }
                case BACK -> executeMessage(sendMessageService.createBackMessage(update));
            }
        }
        if(update.hasCallbackQuery()){
            String callDate = update.getCallbackQuery().getData();

            List<EditMessageText> meningocMessage = sendMessageService.createMeningocMessage(update,
                    "Для начала определим наличие и характер сыпи:");
            EditMessageText meningocMessage1 = sendMessageService.createMeningocMessage1(update,
                    "Преимущественная локализация сыпи:");
            EditMessageText meningocMessage2 = sendMessageService.createMeningocMessage2(update,
                    "Яркость сыпи:");
            EditMessageText meningocMessage3 = sendMessageService.createMeningocMessage3(update,
                    "Цианоз:");
            EditMessageText meningocMessage4 = sendMessageService.createMeningocMessage4(update,
                    "Общее состояние до лечения:");
            EditMessageText meningocMessage5 = sendMessageService.createMeningocMessage5(update,
                    "Динамика температуры тела С°:");
            EditMessageText meningocMessage6= sendMessageService.createMeningocMessage6(update,
                    "Олигурия:");
            EditMessageText meningocMessage7= sendMessageService.createMeningocMessage7(update,
                    "Менингеальные симптомы:");
            EditMessageText meningocMessage8= sendMessageService.createMeningocMessage8(update,
                    "Спасибо за обращение, ваши данные были переданы врачу  " +
                            "для повторного тестирования нажмите кнопку начать");
            EditMessageText asthmaMessage = sendMessageService.createAsthmaMessage(update,
                    "Аллергические заболевания(АЗ) у родных раньше и теперь:");
            EditMessageText asthmaMessage1 = sendMessageService.createAsthmaMessage1(update,
                    "АЗ у других родственников и теперь:");
            EditMessageText asthmaMessage2 = sendMessageService.createAsthmaMessage2(update,
                    "Наличие профвредности у родителей до или во время беременности:");
            EditMessageText asthmaMessage3 = sendMessageService.createAsthmaMessage3(update,
                    "Аллергенность быта на первом году жизни ребёнка:");
            EditMessageText asthmaMessage4 = sendMessageService.createAsthmaMessage4(update,
                    "Продолжительность грудного вскармливания:");
            EditMessageText asthmaMessage5 = sendMessageService.createAsthmaMessage5(update,
                    "Злоупотребление облигатными аллергеннами матерью:");
            EditMessageText asthmaMessage6= sendMessageService.createAsthmaMessage6(update,
                    "Злоупотребление молочными продуктами матерью:");
            EditMessageText asthmaMessage7= sendMessageService.createAsthmaMessage7(update,
                    "Кожные изменения, их связь с молочным докормом:");
            EditMessageText asthmaMessage8= sendMessageService.createAsthmaMessage8(update,
                    "ОРВИ с острой бронхиальной обструкцией(ОБО):");
            EditMessageText asthmaMessage9= sendMessageService.createAsthmaMessage9(update,
                    "Пневмонии на 1-м году жизни:");
            EditMessageText asthmaMessage10= sendMessageService.createAsthmaMessage10(update,
                    "Антибиотики на первом году жизни");
            EditMessageText asthmaMessage11= sendMessageService.createAsthmaMessage11(update,
                    "Введение глобулина");
            EditMessageText asthmaMessage12= sendMessageService.createAsthmaMessage12(update,
                    "Спасибо за обращение, ваши данные были переданы врачу  " +
                            "для повторного тестирования нажмите кнопку начать");
            System.out.println(callDate);
            switch (callDate) {
                case "inf-0" ->
                   /*SendMessage sendMessage = new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId());
                    sendMessage.setText("Ye");
                   try {
                        execute(sendMessage);
                   } catch (TelegramApiException e) {
                        e.printStackTrace();
                  }*/
                        //executeMessage(sendMessageService.sendAnswerCallBack("Признаки до начала лечения",false,update.getCallbackQuery()));
                        executeMessage(meningocMessage.get(0));
                case "0-1" -> {
                    System.out.println("-9");
                    executeMessage(meningocMessage1);
                }
                case "0-2" -> {
                    System.out.println("-3");
                    executeMessage(meningocMessage1);
                }
                case "0-3" -> {
                    System.out.println("+3");
                    executeMessage(meningocMessage1);
                }
                case "0-4" -> {
                    System.out.println("+4");
                    executeMessage(meningocMessage1);
                }
                case "0-5" -> {
                    System.out.println("+9");
                    executeMessage(meningocMessage1);
                }
                case "0-6" -> {
                    System.out.println("+10");
                    executeMessage(meningocMessage1);
                }
                case "1-1" -> {
                    System.out.println("-8");
                    executeMessage(meningocMessage2);
                }
                case "1-2" -> {
                    System.out.println("-4");
                    executeMessage(meningocMessage2);
                }
                case "1-3" -> {
                    System.out.println("-3");
                    executeMessage(meningocMessage2);
                }
                case "1-4" -> {
                    System.out.println("+5");
                    executeMessage(meningocMessage2);
                }
                case "2-1" -> {
                    System.out.println("-4");
                    executeMessage(meningocMessage3);
                }
                case "2-2" -> {
                    System.out.println("+6");
                    executeMessage(meningocMessage3);
                }
                case "2-3" -> {
                    System.out.println("+8");
                    executeMessage(meningocMessage3);
                }
                case "3-1" -> {
                    System.out.println("0");
                    executeMessage(meningocMessage4);
                }
                case "3-2" -> {
                    System.out.println("+6");
                    executeMessage(meningocMessage4);
                }
                case "4-1" -> {
                    System.out.println("-7");
                    executeMessage(meningocMessage5);
                }
                case "4-2" -> {
                    System.out.println("+2");
                    executeMessage(meningocMessage5);
                }
                case "4-3" -> {
                    System.out.println("+5");
                    executeMessage(meningocMessage5);
                }
                case "5-1" -> {
                    System.out.println("-3");
                    executeMessage(meningocMessage6);
                }
                case "5-2" -> {
                    System.out.println("-2");
                    executeMessage(meningocMessage6);
                }
                case "5-3" -> {
                    System.out.println("0");
                    executeMessage(meningocMessage6);
                }
                case "5-4" -> {
                    System.out.println("+5");
                    executeMessage(meningocMessage6);
                }
                case "6-1" -> {
                    System.out.println("-1");
                    executeMessage(meningocMessage7);
                }
                case "6-2" -> {
                    System.out.println("+5");
                    executeMessage(meningocMessage7);
                }
                case "7-1" -> {
                    System.out.println("-3");
                    executeMessage(meningocMessage8);
                }
                case "7-2" -> {
                    System.out.println("-4");
                    executeMessage(meningocMessage8);
                }
                case "inf-1" -> executeMessage(asthmaMessage);
                case "A0-1" -> {
                    System.out.println("-1");
                    executeMessage(asthmaMessage1);
                }
                case "A0-2" -> {
                    System.out.println("+2");
                    executeMessage(asthmaMessage1);
                }
                case "A0-3" -> {
                    System.out.println("+5");
                    executeMessage(asthmaMessage1);
                }
                case "A1-1" -> {
                    System.out.println("-1");
                    executeMessage(asthmaMessage2);
                }
                case "A1-2" -> {
                    System.out.println("+1");
                    executeMessage(asthmaMessage2);
                }
                case "A1-3" -> {
                    System.out.println("+3");
                    executeMessage(asthmaMessage2);
                }
                case "A2-1" -> {
                    System.out.println("-1");
                    executeMessage(asthmaMessage3);
                }
                case "A2-2" -> {
                    System.out.println("+1");
                    executeMessage(asthmaMessage3);
                }
                case "A2-3" -> {
                    System.out.println("+3");
                    executeMessage(asthmaMessage3);
                }
                case "A3-1" -> {
                    System.out.println("-2");
                    executeMessage(asthmaMessage4);
                }
                case "A3-2" -> {
                    System.out.println("+1");
                    executeMessage(asthmaMessage4);
                }
                case "A3-3" -> {
                    System.out.println("+3");
                    executeMessage(asthmaMessage4);
                }
                case "A4-1" -> {
                    System.out.println("+2");
                    executeMessage(asthmaMessage5);
                }
                case "A4-2" -> {
                    System.out.println("0");
                    executeMessage(asthmaMessage5);
                }
                case "A4-3" -> {
                    System.out.println("-2");
                    executeMessage(asthmaMessage5);
                }
                case "A5-1" -> {
                    System.out.println("0");
                    executeMessage(asthmaMessage6);
                }
                case "A5-2" -> {
                    System.out.println("+2");
                    executeMessage(asthmaMessage6);
                }
                case "A5-3" -> {
                    System.out.println("+3");
                    executeMessage(asthmaMessage6);
                }
                case "A6-1" -> {
                    System.out.println("-1");
                    executeMessage(asthmaMessage7);
                }
                case "A6-2" -> {
                    System.out.println("+3");
                    executeMessage(asthmaMessage7);
                }
                case "A6-3" -> {
                    System.out.println("+4");
                    executeMessage(asthmaMessage7);
                }
                case "A7-1" -> {
                    System.out.println("-2");
                    executeMessage(asthmaMessage8);
                }
                case "A7-2" -> {
                    System.out.println("0");
                    executeMessage(asthmaMessage8);
                }
                case "A7-3" -> {
                    System.out.println("+4");
                    executeMessage(asthmaMessage8);
                }
                case "A7-4" -> {
                    System.out.println("+3");
                    executeMessage(asthmaMessage8);
                }
                case "A7-5" -> {
                    System.out.println("+6");
                    executeMessage(asthmaMessage8);
                }
                case "A8-1" -> {
                    System.out.println("-2");
                    executeMessage(asthmaMessage9);
                }
                case "A8-2" -> {
                    System.out.println("+1");
                    executeMessage(asthmaMessage9);
                }
                case "A8-3" -> {
                    System.out.println("+5");
                    executeMessage(asthmaMessage9);
                }
                case "A8-4" -> {
                    System.out.println("+9");
                    executeMessage(asthmaMessage9);
                }
                case "A9-1" -> {
                    System.out.println("0");
                    executeMessage(asthmaMessage10);
                }
                case "A9-2" -> {
                    System.out.println("+3");
                    executeMessage(asthmaMessage10);
                }
                case "A10-1" -> {
                    System.out.println("-1");
                    executeMessage(asthmaMessage11);
                }
                case "A10-2" -> {
                    System.out.println("+5");
                    executeMessage(asthmaMessage11);
                }
                case "A11-1" -> {
                    System.out.println("-1");
                    executeMessage(asthmaMessage12);
                }
                case "A11-2" -> {
                    System.out.println("0");
                    executeMessage(asthmaMessage12);
                }
                case "A11-3" -> {
                    System.out.println("+2");
                    executeMessage(asthmaMessage12);
                }
                case "A11-4" -> {
                    System.out.println("+4");
                    executeMessage(asthmaMessage12);
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
    
    @SafeVarargs
    private <T extends BotApiMethod> void executeMessage(T ...sendMessage){
        try {
            for (T n: sendMessage){
                execute(n);
            }
           // execute(sendMessage);
        }
        catch (TelegramApiException e){
             e.printStackTrace();
        }
    }
    private <E extends BotApiMethod> void executePhoto(SendPhoto sendPhoto){
        try {
            execute(sendPhoto);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
