package TGController;

import DataBaseLogic.DataBaseConnection;
import TGLogic.SendMessageService;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.sql.SQLException;
import java.util.List;

import static TGConstant.Constant.*;

public class TGJavaBotController extends TelegramLongPollingBot {
    private static final String TOKEN = "2018829476:AAFcvR0sF_Y63CdZ3r9frf97hqIxvZLzNNs";
    private static final String USERNAME ="DiplomJavaStudy_Bot";
    public TGJavaBotController(DefaultBotOptions options) { super(options); }

    public Integer user_id = 1;
    public  void updateMetrics(String metric,int number,Update update){
        try {
            DataBaseConnection.updateDb("UPDATE test_bd.test_table " +
                    "SET "+metric+"  = '" +number+"' " +
                    "ORDER BY idtest_table DESC LIMIT 1");
            System.out.println(number);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public  void insertDiseases(String diseas,Update update){
        try {
            DataBaseConnection.updateDb("INSERT INTO test_bd.test_table " +
                    "(test_bd.test_table.client_message, " +
                    "test_bd.test_table.diseas_test)" +
                    "VALUES('" + user_id + "','" +diseas+"' ) ");
            System.out.println(user_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void onUpdateReceived(Update update) {

        SendMessageService sendMessageService = new SendMessageService();
        if(update.hasMessage() && update.getMessage().hasText()){
            System.out.println(update.getMessage().getFrom().getId());
            System.out.println(update.getMessage().getFrom());
            user_id = update.getMessage().getFrom().getId();
            switch (update.getMessage().getText()) {
                case START ->
                        executeMessage(sendMessageService.createGreetingInformation(update));
                case START_PLANNING ->
                        executeMessage(sendMessageService.createTabletsInstruction(update));
                case HELP -> executeMessage(sendMessageService.createHelpMessage(update));
                case SHOW_DEALS -> {
                    executeMessage(sendMessageService.createShowMessage(update));
                    executePhoto(sendMessageService.createShowPhoto(update));
                }
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
                case "inf-0" :
                    executeMessage(meningocMessage.get(0));
                    insertDiseases(MENINGOC,update);
                break;
                case "0-1" : {
                    System.out.println("-9");
                    executeMessage(meningocMessage1);
                    updateMetrics("metric1",-9,update);
                }
                break;
                case "0-2" : {
                    System.out.println("-3");
                    executeMessage(meningocMessage1);
                    updateMetrics("metric1",-3,update);
                }
                break;
                case "0-3" : {
                    System.out.println("+3");
                    executeMessage(meningocMessage1);
                    updateMetrics("metric1",3,update);
                }
                break;
                case "0-4" :{
                    System.out.println("+4");
                    executeMessage(meningocMessage1);
                    updateMetrics("metric1",4,update);
                }
                break;
                case "0-5": {
                    System.out.println("+9");
                    executeMessage(meningocMessage1);
                    updateMetrics("metric1",9,update);
                }
                break;
                case "0-6": {
                    System.out.println("+10");
                    executeMessage(meningocMessage1);
                    updateMetrics("metric1",10,update);
                }
                break;
                case "1-1": {
                    System.out.println("-8");
                    executeMessage(meningocMessage2);
                    updateMetrics("metric2",-8,update);
                }
                break;
                case "1-2": {
                    System.out.println("-4");
                    executeMessage(meningocMessage2);
                    updateMetrics("metric2",-4,update);
                }
                break;
                case "1-3": {
                    System.out.println("-3");
                    executeMessage(meningocMessage2);
                    updateMetrics("metric2",-3,update);
                }
                break;
                case "1-4": {
                    System.out.println("+5");
                    executeMessage(meningocMessage2);
                    updateMetrics("metric2",5,update);
                }
                break;
                case "2-1": {
                    System.out.println("-4");
                    executeMessage(meningocMessage3);
                    updateMetrics("metric3",-4,update);
                }
                break;
                case "2-2": {
                    System.out.println("+6");
                    executeMessage(meningocMessage3);
                    updateMetrics("metric3",6,update);
                }
                break;
                case "2-3": {
                    System.out.println("+8");
                    executeMessage(meningocMessage3);
                    updateMetrics("metric3",8,update);
                }
                break;
                case "3-1": {
                    System.out.println("0");
                    executeMessage(meningocMessage4);
                    updateMetrics("metric4",0,update);
                }
                break;
                case "3-2": {
                    System.out.println("+6");
                    executeMessage(meningocMessage4);
                    updateMetrics("metric4",6,update);
                }
                break;
                case "4-1": {
                    System.out.println("-7");
                    executeMessage(meningocMessage5);
                    updateMetrics("metric5",-7,update);
                }
                break;
                case "4-2": {
                    System.out.println("+2");
                    executeMessage(meningocMessage5);
                    updateMetrics("metric5",2,update);
                }
                break;
                case "4-3": {
                    System.out.println("+5");
                    executeMessage(meningocMessage5);
                    updateMetrics("metric5",5,update);
                }
                break;
                case "5-1": {
                    System.out.println("-3");
                    executeMessage(meningocMessage6);
                    updateMetrics("metric6",-3,update);
                }
                break;
                case "5-2":{
                    System.out.println("-2");
                    executeMessage(meningocMessage6);
                    updateMetrics("metric6",-2,update);
                }
                break;
                case "5-3": {
                    System.out.println("0");
                    executeMessage(meningocMessage6);
                    updateMetrics("metric6",0,update);
                }
                break;
                case "5-4": {
                    System.out.println("+5");
                    executeMessage(meningocMessage6);
                    updateMetrics("metric6",5,update);
                }
                break;
                case "6-1": {
                    System.out.println("-1");
                    executeMessage(meningocMessage7);
                    updateMetrics("metric7",-1,update);
                }
                break;
                case "6-2": {
                    System.out.println("+5");
                    executeMessage(meningocMessage7);
                    updateMetrics("metric7",5,update);
                }
                break;
                case "7-1": {
                    System.out.println("-3");
                    executeMessage(meningocMessage8);
                    updateMetrics("metric8",-3,update);
                }
                break;
                case "7-2": {
                    System.out.println("-4");
                    executeMessage(meningocMessage8);
                    updateMetrics("metric8",-4,update);
                }
                break;
                case "inf-1": executeMessage(asthmaMessage);
                    insertDiseases(ASTHMA,update);
                break;
                case "A0-1": {
                    System.out.println("-1");
                    executeMessage(asthmaMessage1);
                    updateMetrics("metric1",-1,update);
                }
                break;
                case "A0-2":{
                    System.out.println("+2");
                    executeMessage(asthmaMessage1);
                    updateMetrics("metric1",2,update);
                }
                break;
                case "A0-3":{
                    System.out.println("+5");
                    executeMessage(asthmaMessage1);
                    updateMetrics("metric1",5,update);
                }
                break;
                case "A1-1": {
                    System.out.println("-1");
                    executeMessage(asthmaMessage2);
                    updateMetrics("metric2",-1,update);
                }
                break;
                case "A1-2": {
                    System.out.println("+1");
                    executeMessage(asthmaMessage2);
                    updateMetrics("metric2",1,update);
                }
                break;
                case "A1-3":{
                    System.out.println("+3");
                    executeMessage(asthmaMessage2);
                    updateMetrics("metric2",3,update);
                }
                break;
                case "A2-1": {
                    System.out.println("-1");
                    executeMessage(asthmaMessage3);
                    updateMetrics("metric3",-1,update);
                }
                break;
                case "A2-2": {
                    System.out.println("+1");
                    executeMessage(asthmaMessage3);
                    updateMetrics("metric3",1,update);
                }
                break;
                case "A2-3": {
                    System.out.println("+3");
                    executeMessage(asthmaMessage3);
                    updateMetrics("metric3",3,update);
                }
                break;
                case "A3-1": {
                    System.out.println("-2");
                    executeMessage(asthmaMessage4);
                    updateMetrics("metric4",-2,update);
                }
                break;
                case "A3-2": {
                    System.out.println("+1");
                    executeMessage(asthmaMessage4);
                    updateMetrics("metric4",1,update);
                }
                break;
                case "A3-3": {
                    System.out.println("+3");
                    executeMessage(asthmaMessage4);
                    updateMetrics("metric4",3,update);
                }
                break;
                case "A4-1": {
                    System.out.println("+2");
                    executeMessage(asthmaMessage5);
                    updateMetrics("metric5",2,update);
                }
                break;
                case "A4-2": {
                    System.out.println("0");
                    executeMessage(asthmaMessage5);
                    updateMetrics("metric5",0,update);
                }
                break;
                case "A4-3": {
                    System.out.println("-2");
                    executeMessage(asthmaMessage5);
                    updateMetrics("metric5",-2,update);
                }
                break;
                case "A5-1": {
                    System.out.println("0");
                    executeMessage(asthmaMessage6);
                    updateMetrics("metric6",0,update);
                }
                break;
                case "A5-2": {
                    System.out.println("+2");
                    executeMessage(asthmaMessage6);
                    updateMetrics("metric6",2,update);
                }
                break;
                case "A5-3": {
                    System.out.println("+3");
                    executeMessage(asthmaMessage6);
                    updateMetrics("metric6",3,update);
                }
                break;
                case "A6-1": {
                    System.out.println("-1");
                    executeMessage(asthmaMessage7);
                    updateMetrics("metric7",-1,update);
                }
                break;
                case "A6-2": {
                    System.out.println("+3");
                    executeMessage(asthmaMessage7);
                    updateMetrics("metric7",3,update);
                }
                break;
                case "A6-3": {
                    System.out.println("+4");
                    executeMessage(asthmaMessage7);
                    updateMetrics("metric7",4,update);
                }
                break;
                case "A7-1": {
                    System.out.println("-2");
                    executeMessage(asthmaMessage8);
                    updateMetrics("metric8",-2,update);
                }
                break;
                case "A7-2": {
                    System.out.println("0");
                    executeMessage(asthmaMessage8);
                    updateMetrics("metric8",0,update);
                }
                break;
                case "A7-3": {
                    System.out.println("+4");
                    executeMessage(asthmaMessage8);
                    updateMetrics("metric8",4,update);
                }
                break;
                case "A7-4": {
                    System.out.println("+3");
                    executeMessage(asthmaMessage8);
                    updateMetrics("metric8",3,update);
                }
                break;
                case "A7-5": {
                    System.out.println("+6");
                    executeMessage(asthmaMessage8);
                    updateMetrics("metric8",6,update);
                }
                break;
                case "A8-1": {
                    System.out.println("-2");
                    executeMessage(asthmaMessage9);
                    updateMetrics("metric9",-2,update);
                }
                break;
                case "A8-2": {
                    System.out.println("+1");
                    executeMessage(asthmaMessage9);
                    updateMetrics("metric9",1,update);
                }
                break;
                case "A8-3": {
                    System.out.println("+5");
                    executeMessage(asthmaMessage9);
                    updateMetrics("metric9",5,update);
                }
                break;
                case "A8-4": {
                    System.out.println("+9");
                    executeMessage(asthmaMessage9);
                    updateMetrics("metric9",9,update);
                }
                break;
                case "A9-1": {
                    System.out.println("0");
                    executeMessage(asthmaMessage10);
                    updateMetrics("metric10",0,update);
                }
                break;
                case "A9-2": {
                    System.out.println("+3");
                    executeMessage(asthmaMessage10);
                    updateMetrics("metric10",3,update);
                }
                break;
                case "A10-1": {
                    System.out.println("-1");
                    executeMessage(asthmaMessage11);
                    updateMetrics("metric11",-1,update);
                }
                break;
                case "A10-2": {
                    System.out.println("+5");
                    executeMessage(asthmaMessage11);
                    updateMetrics("metric11",5,update);
                }
                break;
                case "A11-1": {
                    System.out.println("-1");
                    executeMessage(asthmaMessage12);
                    updateMetrics("metric12",-1,update);
                }
                break;
                case "A11-2": {
                    System.out.println("0");
                    executeMessage(asthmaMessage12);
                    updateMetrics("metric12",0,update);
                }
                break;
                case "A11-3": {
                    System.out.println("+2");
                    executeMessage(asthmaMessage12);
                    updateMetrics("metric12",2,update);
                }
                break;
                case "A11-4": {
                    System.out.println("+4");
                    executeMessage(asthmaMessage12);
                    updateMetrics("metric12",4,update);
                }
                break;
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
