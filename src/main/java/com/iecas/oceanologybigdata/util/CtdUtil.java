package com.iecas.oceanologybigdata.util;

import java.math.BigDecimal;

public class CtdUtil {

    /**

     * 将科学计数法的字符串传入

     * @param str

     * @return 返回double类型

     */

    public static double getDoubleNumber(String str){

        double number = 0;

        BigDecimal bd = new BigDecimal(str);

        number =  Double.parseDouble(bd.toPlainString());



        return number;

}
    public static double strToDouble(String str){
        return Double.parseDouble(str);
    }

}
