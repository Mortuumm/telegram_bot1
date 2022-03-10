package TGParser;
import TGLogic.TGOpenFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.*;

public class Parser {
    TGOpenFile tgOpenFile = new TGOpenFile();
    ArrayList<String> callDataArray = new ArrayList<>();
    ArrayList<String> questionArray = new ArrayList<>();
    ArrayList<String> diseaseNameArray = new ArrayList<>();
    ArrayList<String> buttonsNameArray = new ArrayList<>();
    ArrayList<String> callBackButtonsArray = new ArrayList<>();
    ArrayList<String> marksArray = new ArrayList<>();
    public void readFromExcel(String path) {
        File file = new File(tgOpenFile.getPath());
        String value = "";
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheetAt(0);
        Row rowCallback;
        Row rowQuestion;
        Row rowDiseaseName;
        Row rowButtonsName;
        Row rowCallbackButtons;
        Row rowMarks;
        Cell callBackCell;
        Cell QuestionCell;
        Cell DiseaseNameCell;
        Cell ButtonsNameCell;
        Cell CallbackButtonsCell;
        Cell MarksCell;

        Iterator<Row> rowIterator = sheet.iterator();
            rowCallback = sheet.getRow(0);
            rowQuestion = sheet.getRow(1);
            rowDiseaseName =sheet.getRow(2);
            rowButtonsName = sheet.getRow(3);
            rowCallbackButtons = sheet.getRow(4);
            rowMarks = sheet.getRow(5);
            int cn = rowCallback.getLastCellNum();
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
            /*while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING) {
                        str = cell.getStringCellValue();
                        arrayList.add(str);
                    }
                }*/
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
}

