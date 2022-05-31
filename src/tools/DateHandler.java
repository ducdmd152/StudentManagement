/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class DateHandler {
    public static String toPatternFormat(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
    
    public static Date createDateFromPattern(String date, String regexPattern) throws Exception {
        if(!date.matches(regexPattern))
            throw new Exception("The string does not match the pattern!");
        
        return new SimpleDateFormat(regexPattern).parse(date);
    }
}
