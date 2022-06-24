package TGMathLogic;

import TGLogic.TGOpenFile;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TGMathMultiplyStrategy implements TGMath {
    @Override
    public String parsingInfoArray(ArrayList<Integer> numbers, TGOpenFile tgOpenFile) {
        int sum = 0;
        String number = "";
        for (Integer testNumber : numbers) {
            sum = sum + testNumber * 2;
        }
        for(int i=0; i < tgOpenFile.getCriticalArray().size(); i++){
            if(sum > parseInt(tgOpenFile.getCriticalArray().get(i))){
                number = tgOpenFile.getNumberArray().get(i);
                break;
            }
        }
        return number;
    }
}
