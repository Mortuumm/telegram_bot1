package TGController;

import DataBaseLogic.DataBaseConnection;
import TGLogic.SendMessageService;
import TGLogic.TGMath;
import TGLogic.TGOpenFile;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import static TGConstant.Constant.*;
import static java.lang.Integer.parseInt;


public class TGJavaBotController extends TelegramLongPollingBot {
    private static final Logger log = Logger.getLogger(String.valueOf(TGJavaBotController.class));
    TGOpenFile tgOpenFile = new TGOpenFile();
    TGMath tgMath = new TGMath();
    
    public TGJavaBotController(DefaultBotOptions options) { super(options);
        tgOpenFile.start();
    }

    public Integer user_id = 1;
    String path ;
    ArrayList<String> buttonNames = new ArrayList<>();
    ArrayList<String> buttonCallDateNames = new ArrayList<>();
    ArrayList<Integer> testNumbers = new ArrayList<>();
    SendMessageService sendMessageService = new SendMessageService();

    public  void updateMetrics(int i, int number,Update update){
        try {
                DataBaseConnection.updateDb("UPDATE test_bd.test_table " +
                        "SET metric" + i + "  = '" + number + "' " +
                        "ORDER BY idtest_table DESC LIMIT 1");
                System.out.println(number);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public  void insertDiseases(String diseas, Update update){
        try {
            DataBaseConnection.updateDb("INSERT INTO test_bd.test_table " +
                    "(test_bd.test_table.client_message, " +
                    "test_bd.test_table.diseas_test)" +
                    "VALUES('" + user_id + "','" +diseas+"' ) ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createButtonNameArr(String sched2, String sched3){
        for(int i=0; i < sched2.length(); i++){
            buttonNames = new ArrayList<>(Arrays.asList(sched2.split(",")));
        }
        for(int i=0; i < sched3.length(); i++){
            buttonCallDateNames = new ArrayList<>(Arrays.asList(sched3.split(",")));
        }

    }

    public void parserArray(HashMap<String, String[]> scheduler){
        for (int i=0; i < tgOpenFile.getCallDataArray().size(); i++){
                putInHash(scheduler, tgOpenFile.getCallDataArray().get(i),
                        new String[]{tgOpenFile.getQuestionArray().get(i) +"/"
                                +tgOpenFile.getDiseaseNameArray().get(i) + "/"
                                +tgOpenFile.getButtonsNameArray().get(i) + "/"
                                +tgOpenFile.getCallBackButtonsArray().get(i) + "/"
                                +tgOpenFile.getMarksArray().get(i)});
        }
    }

    public void putInHash(HashMap<String, String[]> scheduler,
                          String callBack, String[] descriptions){
        for (String description : descriptions) {
            scheduler.put(callBack, new String[]{description});
        }
    }
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            System.out.println(update.getMessage().getFrom().getId());
            System.out.println(update.getMessage().getFrom());
            user_id = update.getMessage().getFrom().getId();
            ArrayList<String> callDataListNew = new ArrayList<>();
            for (String s : tgOpenFile.getCallDataArray()) {
                if (s.startsWith("inf")) {
                    callDataListNew.add(s);
                }
            }
            Set<String> buttonNamesSet = new LinkedHashSet<>(tgOpenFile.getDiseaseNameArray());
            ArrayList<String> buttonNamesList = new ArrayList<>(buttonNamesSet);
            switch (update.getMessage().getText()) {
                case START -> {
                    try {
                        executeMessage(sendMessageService.createGreetingInformation(update));
                        log.info("Started");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case START_PLANNING -> {
                    try {
                        executeMessage(sendMessageService.createButtonsMessage(update, START_DISEASES_MESSAGE,
                               buttonNamesList, callDataListNew));
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case HELP -> executeMessage(sendMessageService.createHelpMessage(update));
            }
        }

        if(update.hasCallbackQuery()) {
            String callDate = update.getCallbackQuery().getData();
            HashMap<String, String[]> scheduler = new HashMap<>();
            System.out.println(callDate);
            parserArray(scheduler);

            for (String sch : scheduler.get(callDate)) {
                String[] sched = sch.split("/");
                    createButtonNameArr(sched[2], sched[3]);
                testNumbers.add(parseInt(sched[4]));
                        if(sched[2].equals("-")) {
                            executeMessage(sendMessageService.createExitMessage(update, sched[0]
                                    + " Для дальнейшей консультации обратитесь по данному номеру "
                                    + tgMath.parsingInfoArray(testNumbers,tgOpenFile)));
                            System.out.println(testNumbers);

                        }else {
                            executeMessage(sendMessageService.createInlineButtonsMessage(update, sched[0],
                                    buttonNames, buttonCallDateNames));
                        }
                if (callDate.startsWith("inf")) {
                    insertDiseases(sched[1], update);
                } else {
                    updateMetrics(parseInt(String.valueOf(callDate.charAt(1))),parseInt(sched[4]), update);
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
