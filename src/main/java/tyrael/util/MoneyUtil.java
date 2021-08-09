package tyrael.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 王超 on 2017/11/1.
 */

public class MoneyUtil {

    /**
     * 是金额，并大于0
     * @param s
     * @return
     */
    public static boolean isPositiveMoney(String s){
        try{
            float f = Float.parseFloat(s);
            if(f <= 0){
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return isMoney(s);
    }


    /**
     * 是否是金额
     * 000认为是金额
     * @return
     */
    public static boolean isMoney(String s){
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,2})?$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static void main(String args[]){
        System.out.println("isMoney(000):" + isMoney("000"));
        System.out.println("isMoney(0):" + isMoney("0"));
        System.out.println("isMoney(1):" + isMoney("1"));
        System.out.println("isMoney(1.1):" + isMoney("1.1"));
        System.out.println("isMoney(.01):" + isMoney(".01"));
    }
}
