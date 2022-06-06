package TGLogic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    FileNameExtensionFilter excel = new FileNameExtensionFilter(
            "Excel", "xlsx");
    FileNameExtensionFilter json = new FileNameExtensionFilter(
            "JSON", "json");

    FileNameExtensionFilter xml = new FileNameExtensionFilter(
            "XML", "xml");
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
            btnOpenDir = new JButton("Выбрать файл");
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
        callDataArray.clear();
        criticalArray.clear();
        callBackButtonsArray.clear();
        buttonsNameArray.clear();
        healthArray.clear();
        marksArray.clear();
        questionArray.clear();
        diseaseNameArray.clear();
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
        int nn = rowCritical.getLastCellNum();
        for (int i=1; i < rowCallback.getLastCellNum();i++){
            callBackCell = rowCallback.getCell(i);
            if (callBackCell.getCellType() == CellType.STRING) {
                callDataArray.add(callBackCell.getStringCellValue());
            }
        }

        for (int i=1; i < rowQuestion.getLastCellNum();i++){
            QuestionCell = rowQuestion.getCell(i);
            if (QuestionCell.getCellType() == CellType.STRING) {
                questionArray.add(QuestionCell.getStringCellValue());
            }
        }
        for (int i=1; i < rowDiseaseName.getLastCellNum();i++){
            DiseaseNameCell = rowDiseaseName.getCell(i);
            if (DiseaseNameCell.getCellType() == CellType.STRING) {
                diseaseNameArray.add(DiseaseNameCell.getStringCellValue());
            }
        }
        for (int i=1; i < rowButtonsName.getLastCellNum();i++){
            ButtonsNameCell = rowButtonsName.getCell(i);
            if (ButtonsNameCell.getCellType() == CellType.STRING) {
                buttonsNameArray.add(ButtonsNameCell.getStringCellValue());
            }
        }
        for (int i=1; i < rowCallbackButtons.getLastCellNum();i++){
            CallbackButtonsCell = rowCallbackButtons.getCell(i);
            if (CallbackButtonsCell.getCellType() == CellType.STRING) {
                callBackButtonsArray.add(CallbackButtonsCell.getStringCellValue());
            }
        }
        for (int i=1; i < rowMarks.getLastCellNum();i++){
            MarksCell = rowMarks.getCell(i);
            if (MarksCell.getCellType() == CellType.STRING) {
                marksArray.add(MarksCell.getStringCellValue());
            }
        }
        for (int i=1; i < rowCritical.getLastCellNum(); i++){
            CriticalCell = rowCritical.getCell(i);
            if(CriticalCell.getCellType() == CellType.STRING){
                criticalArray.add(CriticalCell.getStringCellValue());
            }
        }
        for (int i=1; i < rowHealth.getLastCellNum(); i++){
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

    public void readFromJson(String path){
        callDataArray.clear();
        criticalArray.clear();
        callBackButtonsArray.clear();
        buttonsNameArray.clear();
        healthArray.clear();
        marksArray.clear();
        questionArray.clear();
        diseaseNameArray.clear();
        numberArray.clear();
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader file = new FileReader(path);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(file);
            JSONArray jsonCallBackArray = (JSONArray) jsonObject.get("CallBackНомер");
            JSONArray jsonQuestionArray = (JSONArray) jsonObject.get("Вопрос");
            JSONArray jsonDiseaseNameArray = (JSONArray) jsonObject.get("Название болезни");
            JSONArray jsonButtonsNameArray = (JSONArray) jsonObject.get("Названия кнопок чере запятую");
            JSONArray jsonCallBackButtonsArray = (JSONArray) jsonObject.get("CallBackДляСледующихКнопок через запятую");
            JSONArray jsonMarksArray = (JSONArray) jsonObject.get("Баллы");
            JSONArray jsonCriticalArray = (JSONArray) jsonObject.get("Критические пороги");
            JSONArray jsonHealthArray = (JSONArray) jsonObject.get("Оценка здоровья");
            JSONArray jsoNumberArray = (JSONArray) jsonObject.get("Номер телефона");

            for(Object s: jsonCallBackArray){
                callDataArray.add(String.valueOf(s));
            }
            for(Object s: jsonQuestionArray){
                questionArray.add(String.valueOf(s));
            }
            for(Object s: jsonDiseaseNameArray){
                diseaseNameArray.add(String.valueOf(s));
            }
            for(Object s: jsonButtonsNameArray ){
                buttonsNameArray.add(String.valueOf(s));
            }
            for(Object s: jsonCallBackButtonsArray){
                callBackButtonsArray.add(String.valueOf(s));
            }
            for(Object s: jsonMarksArray){
                marksArray.add(String.valueOf(s));
            }
            for(Object s: jsonCriticalArray){
                criticalArray.add(String.valueOf(s));
            }
            for(Object s: jsonHealthArray){
                healthArray.add(String.valueOf(s));
            }
            for(Object s: jsoNumberArray){
                numberArray.add(String.valueOf(s));
            }
            System.out.println(callDataArray);


        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(callDataArray);
        System.out.println(callBackButtonsArray);

    }

    public void readFromXML(String path) throws ParserConfigurationException, IOException, SAXException {
        callDataArray.clear();
        criticalArray.clear();
        callBackButtonsArray.clear();
        buttonsNameArray.clear();
        healthArray.clear();
        marksArray.clear();
        questionArray.clear();
        diseaseNameArray.clear();
        numberArray.clear();
        File fileXML = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fileXML);

            NodeList nameElement =  document.getElementsByTagName("Metrics");

         callDataArray = setMetricArray(document.getElementsByTagName("CallBackНомер"),callDataArray);
         questionArray = setMetricArray(document.getElementsByTagName("Вопрос"),questionArray);
        diseaseNameArray = setMetricArray(document.getElementsByTagName("Название_болезни"),diseaseNameArray);
       buttonsNameArray = setMetricArray(document.getElementsByTagName("Названия_кнопок_череp_запятую"),buttonsNameArray);
       callBackButtonsArray = setMetricArray(document.getElementsByTagName("CallBackДляСледующихКнопок_через_запятую"),callBackButtonsArray);
       marksArray = setMetricArray(document.getElementsByTagName("Баллы"),marksArray);
       criticalArray = setMetricArray(document.getElementsByTagName("Критические_пороги"),criticalArray);
       healthArray = setMetricArray(document.getElementsByTagName("Оценка_здоровья"),healthArray);
       numberArray = setMetricArray(document.getElementsByTagName("Номер_телефона"),numberArray);
        System.out.println(callBackButtonsArray);

    }

    public ArrayList<String> setMetricArray(NodeList nameList, ArrayList<String> list){
        for(int i=0; i < nameList.getLength(); i++){
            if(nameList.item(i).getNodeType() == Node.ELEMENT_NODE){
                String nameListElement =  nameList.item(i).getTextContent();
                list = createArray(nameListElement,list);
            }
        }
        return list;
    }
    public ArrayList<String> createArray(String stroke, ArrayList<String> list){
        for(int i=0; i < stroke.length(); i++){
            //stroke = stroke.replaceAll("\\s+","");
            stroke = stroke.replaceAll("\"","");
            list = new ArrayList<>(Arrays.asList(stroke.split(";")));
        }
        return list;
    }
    private void addFileChooserListeners() {
        btnOpenDir.addActionListener(e -> {
            fileChooser.setDialogTitle("Выбрать файл");
            fileChooser.addChoosableFileFilter(excel);
            fileChooser.addChoosableFileFilter(json);
            fileChooser.addChoosableFileFilter(xml);
            // Определение режима - только каталог
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(TGOpenFile.this);
            // Если директория выбрана, покажем ее в сообщении
            if (result == JFileChooser.APPROVE_OPTION ) {
                setPather(fileChooser.getSelectedFile().getPath());
                System.out.println(getPather());
                if(getPather().contains("xlsx")) {
                    readFromExcel(getPather());
                }
                if(getPather().contains("json")){
                    readFromJson(getPather());
                }
                if(getPather().contains("xml")){
                    try {
                        readFromXML(getPather());
                    } catch (ParserConfigurationException | IOException | SAXException ex) {
                        throw new RuntimeException(ex);
                    }
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
