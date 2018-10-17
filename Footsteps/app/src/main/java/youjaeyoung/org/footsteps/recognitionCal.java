package youjaeyoung.org.footsteps;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongseoul on 2017. 12. 4..
 */

public class recognitionCal {
    private String inputStr;
    final private String[] weekStr = {"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};
    final private String[] findStr = {"년","월","일","요일","시","분"};
    private String[] splitResult ;
    private Calendar resultCal;
    private Map<String,String> RegCal;
    private boolean rtnR;
    SimpleDateFormat YFormat;
    SimpleDateFormat MFormat;
    SimpleDateFormat DFormat;
    SimpleDateFormat HFormat;
    SimpleDateFormat mFormat;
    SimpleDateFormat Out_YFormat;
    SimpleDateFormat Out_MFormat;
    SimpleDateFormat Out_DFormat;
    SimpleDateFormat Out_HFormat;
    SimpleDateFormat Out_mFormat;
    Date[] tempDate = new Date[5]; // 년 월 일 시 분
    public recognitionCal(String inputStr){
        this.rtnR=false;
        this.inputStr=inputStr;

        RegCal = new HashMap<String,String>();
        RegCal.put("Contents",inputStr);

        resultCal = Calendar.getInstance();

        YFormat = new SimpleDateFormat("yyyy년");
        MFormat = new SimpleDateFormat("M월");
        DFormat = new SimpleDateFormat("d일");
        HFormat = new SimpleDateFormat("h시");
        mFormat = new SimpleDateFormat("m분");

        Out_YFormat = new SimpleDateFormat("yyyy");
        Out_MFormat = new SimpleDateFormat("M");
        Out_DFormat = new SimpleDateFormat("d");
        Out_HFormat = new SimpleDateFormat("h");
        Out_mFormat = new SimpleDateFormat("m");
    }

    public void inputsplitResult(){
        splitResult=this.inputStr.split(" ");
        for(int i=0;i<splitResult.length;i++) {
            Log.d("xxxx",splitResult[i].toString());
            try {
                tempDate[0] = YFormat.parse(splitResult[i].toString());
                RegCal.put("Year",Out_YFormat.format(tempDate[0]));
                rtnR=true;
                Log.d("xxxx",tempDate[0].toString());
            }
            catch(Exception e){}
            try {
                if(tempDate[0]==null){
                    RegCal.put("Year","2018");
                }
                tempDate[1] = MFormat.parse(splitResult[i].toString());
                RegCal.put("Month",Out_MFormat.format(tempDate[1]));
                rtnR=true;
                Log.d("xxxx",tempDate[1].toString());
            }
            catch(Exception e){}
            try {
                if(tempDate[1]==null){
                    tempDate[1]=new Date(resultCal.MONTH);
                    RegCal.put("Month",String.valueOf(resultCal.MONTH));
                }
                tempDate[2] = DFormat.parse(splitResult[i].toString());
                RegCal.put("Day",Out_DFormat.format(tempDate[2]));
                rtnR=true;
                Log.d("xxxx",tempDate[2].toString());
            }
            catch(Exception e){}
            try {
                if(tempDate[2]==null){
                    tempDate[2]=new Date(resultCal.DAY_OF_MONTH);
                    RegCal.put("Day",String.valueOf(resultCal.DAY_OF_MONTH));
                }
                tempDate[3] = HFormat.parse(splitResult[i].toString());
                RegCal.put("HOUR",Out_DFormat.format(tempDate[3]));

                rtnR=true;
                Log.d("xxxx",tempDate[3].toString());
            }
            catch(Exception e){}
            try {
                if(tempDate[3]==null){
                    RegCal.put("HOUR",String.valueOf(0));
                }
                tempDate[4] = mFormat.parse(splitResult[i].toString());
                RegCal.put("MIN",Out_DFormat.format(tempDate[4]));
                rtnR=true;
                Log.d("xxxx",tempDate[4].toString());
            }
            catch(Exception e){}
            if(tempDate[4]==null){
                RegCal.put("MIN",String.valueOf(0));
            }
        }
    }

    public Map<String,String> returnResult(){
        return RegCal;
    }

    public boolean returnbool(){
        return this.rtnR;
    }

}
