package TGParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

import static org.apache.commons.lang.StringUtils.split;

public class Parser {
    ArrayList<String> callDataArray = new ArrayList<>();
    ArrayList<String> questionArray = new ArrayList<>();
    ArrayList<String> diseaseNameArray = new ArrayList<>();
    ArrayList<String> buttonsNameArray = new ArrayList<>();
    ArrayList<String> callBackButtonsArray = new ArrayList<>();
    ArrayList<String> marksArray = new ArrayList<>();
    public void readFromExcel(String path) {
        File file = new File(path);
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
        Cell cell;

        Iterator<Row> rowIterator = sheet.iterator();
            rowCallback = sheet.getRow(0);
            rowQuestion = sheet.getRow(1);
            rowDiseaseName =sheet.getRow(2);
            rowButtonsName = sheet.getRow(3);
            rowCallbackButtons = sheet.getRow(4);
            rowMarks = sheet.getRow(5);
            int cn = rowCallback.getLastCellNum();
            for (int i=1; i < cn;i++){
                cell = rowCallback.getCell(i);
                if (cell.getCellType() == CellType.STRING) {
                    callDataArray.add(cell.getStringCellValue());
                }
            }
            for (int i=1; i < cn;i++){
            cell = rowQuestion.getCell(i);
            if (cell.getCellType() == CellType.STRING) {
                questionArray.add(cell.getStringCellValue());
            }
        }
        for (int i=1; i < cn;i++){
            cell = rowDiseaseName.getCell(i);
            if (cell.getCellType() == CellType.STRING) {
                diseaseNameArray.add(cell.getStringCellValue());
            }
        }
        for (int i=1; i < cn;i++){
            cell = rowButtonsName.getCell(i);
            if (cell.getCellType() == CellType.STRING) {
                buttonsNameArray.add(cell.getStringCellValue());
            }
        }
        for (int i=1; i < cn;i++){
            cell = rowCallbackButtons.getCell(i);
            if (cell.getCellType() == CellType.STRING) {
                callBackButtonsArray.add(cell.getStringCellValue());
            }
        }
        for (int i=1; i < cn;i++){
            cell = rowMarks.getCell(i);
            if (cell.getCellType() == CellType.STRING) {
                marksArray.add(cell.getStringCellValue());
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

