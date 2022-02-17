import TGController.TGJavaBotController;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


import java.util.logging.Logger;


//ЗАПОМНИ ПРИДУРОК ЕСЛИ ГОРИТ КРАСНЫМ ЭТО НЕ ЗНАЧИТ,ЧТО ПРОЕКТ НЕ РАБОТАЕТ
/*
ЕСЛИ ВЫЛЕТАЕТ НАДПИСЬ С ДАТОЙ И ИНФОЛОГ О ЗАПУСКЕ ТЕЛЕГРАМ АПИ ТО ВСЁ РАБОТАЕТ, ЗАПОМНИ УЖЕ ЭТО ДЕГЕНЕРАТ
 */
/*
Сделать проверку на отсутствие имени и фамилии
 */

public class Main {

    private static final Logger log = Logger.getLogger(String.valueOf(Main.class));
    public static void main(String[] args) {
        ApiContextInitializer.init();
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        TGJavaBotController TGJavaBotController = new TGJavaBotController(botOptions);
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(TGJavaBotController);
            log.info("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }



}
