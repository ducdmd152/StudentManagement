/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author MSI
 */
public class InputStudentValidation {

    
    public static boolean checkStudentIdFormat(String studentId) {
        return studentId.matches("^STU\\d{3}$");
    }
    
    public static boolean checkNameLength(String name) {
        name = name.trim();
        return name.length()>0 && name.length()<=20;
    }
    
    public static boolean checkGenderFormat(String input) {
        if(input.isBlank()) return false;
        
        input = input.trim().toUpperCase();
        return input.equals("TRUE") || input.equals("FALSE");
    }
    
    public static boolean checkDateFormat(String date) {
        String datePattern = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        return date.matches(datePattern);
        
    }
    public static boolean checkValidEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(regex);
   }
    
    public static boolean checkValidPhoneNumber(String phoneNumber) {
        String regex = "^\\d{10,12}$";
        return phoneNumber.matches(regex);
    }
    
//    private static boolean patternMatches(String data, String regex) {
//        return Pattern.compile(regex)
//          .matcher(data)
//          .matches();
//    }

    public static boolean checkDateOfBirth(String dateStr) {
        dateStr = dateStr.trim();
        
        if(!checkDateFormat(dateStr)) return false;
        
        StringTokenizer stk = new StringTokenizer(dateStr, "/");
        int day = Integer.parseInt(stk.nextToken());
        int month = Integer.parseInt(stk.nextToken());
        int year = Integer.parseInt(stk.nextToken());
        
        if(!DateValidation.checkDateValid(year, month, day)) return false;

        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        } catch (Exception ex) {
            return false;
        }
        
        if(date.after(new Date())) return false;
        
        return true;
    }
}
