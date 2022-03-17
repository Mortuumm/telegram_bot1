package TGLogic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TGOpenFile extends JFrame{
    ArrayList<String> callDataArray = new ArrayList<>();
    ArrayList<String> questionArray = new ArrayList<>();
    ArrayList<String> diseaseNameArray = new ArrayList<>();
    ArrayList<String> buttonsNameArray = new ArrayList<>();
    ArrayList<String> callBackButtonsArray = new ArrayList<>();
    ArrayList<String> marksArray = new ArrayList<>();
    ArrayList<String> criticalArray = new ArrayList<>();
    ArrayList<String> healthArray = new ArrayList<>();
    ArrayList<String> numberArray = new ArrayList<>();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Excel", "xlsx");
    private  JButton  btnOpenDir    = null;

    private  JFileChooser fileChooser = null;
    String path ;

    private final String[][] FILTERS = {{"xlsx"}};
    public TGOpenFile() {
        super("Telegram Bot");

    }
    public void start(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            // Кнопка создания диалогового окна для выбора директории
            btnOpenDir = new JButton("Открыть директорию");
            // Создание экземпляра JFileChooser
            fileChooser = new JFileChooser();
            // Подключение слушателей к кнопкам
            addFileChooserListeners();

            // Размещение кнопок в интерфейсе
            JPanel contents = new JPanel();
            contents.add(btnOpenDir   );
            setContentPane(contents);
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
            setSize(360, 110);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void readFromExcel(String path) {
        File file = new File(path);
        String value ;
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert wb != null;
        XSSFSheet sheet = wb.getSheetAt(0);
        Row rowCallback;
        Row rowQuestion;
        Row rowDiseaseName;
        Row rowButtonsName;
        Row rowCallbackButtons;
        Row rowMarks;
        Row rowCritical;
        Row rowHealth;
        Row rowNumber;

        Cell callBackCell;
        Cell QuestionCell;
        Cell DiseaseNameCell;
        Cell ButtonsNameCell;
        Cell CallbackButtonsCell;
        Cell MarksCell;
        Cell CriticalCell;
        Cell HealthCell;
        Cell NumberCell;

        Iterator<Row> rowIterator = sheet.iterator();
        rowCallback = sheet.getRow(0);
        rowQuestion = sheet.getRow(1);
        rowDiseaseName =sheet.getRow(2);
        rowButtonsName = sheet.getRow(3);
        rowCallbackButtons = sheet.getRow(4);
        rowMarks = sheet.getRow(5);
        rowCritical = sheet.getRow(6);
        rowHealth = sheet.getRow(7);
        rowNumber= sheet.getRow(8);
        int cn = rowCallback.getLastCellNum();
        int nn = rowCritical.getLastCellNum();
        for (int i=1; i < cn;i++){
            callBackCell = rowCallback.getCell(i);
            if (callBackCell.getCellType() == CellType.STRING) {
                callDataArray.add(callBackCell.getStringCellValue());
            }
        }

        for (int i=1; i < cn;i++){
            QuestionCell = rowQuestion.getCell(i);
            if (QuestionCell.getCellType() == CellType.STRING) {
                questionArray.add(QuestionCell.getStringCellValue());
            }
        }
        for (int i=1; i < cn;i++){
            DiseaseNameCell = rowDiseaseName.getCell(i);
            if (DiseaseNameCell.getCellType() == CellType.STRING) {
                diseaseNameArray.add(DiseaseNameCell.getStringCellValue());
            }
        }
        for (int i=1; i < cn;i++){
            ButtonsNameCell = rowButtonsName.getCell(i);
            if (ButtonsNameCell.getCellType() == CellType.STRING) {
                buttonsNameArray.add(ButtonsNameCell.getStringCellValue());
            }
        }
        for (int i=1; i < cn;i++){
            CallbackButtonsCell = rowCallbackButtons.getCell(i);
            if (CallbackButtonsCell.getCellType() == CellType.STRING) {
                callBackButtonsArray.add(CallbackButtonsCell.getStringCellValue());
            }
        }
        for (int i=1; i < cn;i++){
            MarksCell = rowMarks.getCell(i);
            if (MarksCell.getCellType() == CellType.STRING) {
                marksArray.add(MarksCell.getStringCellValue());
            }
        }
        for (int i=1; i < nn; i++){
            CriticalCell = rowCritical.getCell(i);
            if(CriticalCell.getCellType() == CellType.STRING){
                criticalArray.add(CriticalCell.getStringCellValue());
            }
        }
        for (int i=1; i < nn; i++){
            HealthCell = rowHealth.getCell(i);
            if(HealthCell.getCellType() == CellType.STRING){
                healthArray.add(HealthCell.getStringCellValue());
            }
        }
        for (int i=1; i < nn; i++){
            NumberCell = rowNumber.getCell(i);
            if(NumberCell.getCellType() == CellType.STRING){
                numberArray.add(NumberCell.getStringCellValue());
            }
        }
    }
    private void addFileChooserListeners() {
        btnOpenDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор файла");
                fileChooser.setFileFilter(filter);
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(TGOpenFile.this);
                // Если директория выбрана, покажем ее в сообщении
                if (result == JFileChooser.APPROVE_OPTION ) {
                    JOptionPane.showMessageDialog(TGOpenFile.this,
                            "Файл был выбран");

                    setPather(fileChooser.getSelectedFile().getPath());
                    System.out.println(getPather());
                    readFromExcel(getPather());
                }

            }

        });

    }
    public String getPather() {
        return path;
    }

    public void setPather(String path) {
        this.path = path;
    }
    public ArrayList<String> getCallDataArray() {
        return callDataArray;
    }

    public ArrayList<String> getQuestionArray() {
        return questionArray;
    }

    public ArrayList<String> getDiseaseNameArray() {
        return diseaseNameArray;
    }

    public ArrayList<String> getButtonsNameArray() {
        return buttonsNameArray;
    }

    public ArrayList<String> getCallBackButtonsArray() {
        return callBackButtonsArray;
    }

    public ArrayList<String> getMarksArray() {
        return marksArray;
    }

    public ArrayList<String> getCriticalArray() {
        return criticalArray;
    }

    public ArrayList<String> getHealthArray() {
        return healthArray;
    }

    public ArrayList<String> getNumberArray() {
        return numberArray;
    }
}
