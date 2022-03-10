import TGController.TGJavaBotController;
import TGLogic.TGOpenFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(String.valueOf(Main.class));
    TGOpenFile tgOpenFile = new TGOpenFile();
    public static void main(String[] args) throws IOException, InvalidFormatException {
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
