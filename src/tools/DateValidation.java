package tools;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class DateValidation {
    public static boolean isNumber(String num) {
        int n = 0;
        for(int i = 0; i < num.length(); i++) {
            if(num.charAt(i) > 57 || num.charAt(i) < 48) {
                return false;
            }
        }
        return true;
    } 
    static  boolean checkYearValid(int year) {
       //get current year, month, day
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);

       if (year > currentYear || year < 1900) {
           return false;
       }
       return true;
   }

    static  boolean checkMonthValid(int month) {
       if (month > 12 || month < 1) {
           return false;
       }
       return true;
   }

     static boolean IsNamNhuan(int year) {
       if (year % 400 == 0)
           return true;
       else if (year % 4 == 0 && year % 100 != 0)
           return true;
       else
           return false;
   }
     
     public static boolean checkDateValid(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        
        return DateValidation.checkDateValid(year,month,day);
     }
     
//     public static boolean checkDateValid(String date) {
//        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
//        if(!date.matches(regex)) return false;
//        
//        StringTokenizer st = new StringTokenizer(date, "/");
//        int day = Integer.parseInt(st.nextToken());
//        int month = Integer.parseInt(st.nextToken());
//        int year = Integer.parseInt(st.nextToken());
//        
//        return DateValidation.checkDateValid(year,month,day);
//     }
     
     static boolean checkDateValid(int year, int month, int day) {
         
       if(!checkMonthValid(month) || !checkYearValid(year)) return false;
       
       if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
           // day 31
           if (day > 31 || day <= 0) {
               return false;
           }
       } else if (month == 4 || month == 6 || month == 9 || month == 11) {
           //day 30
           if (day > 30 || day <= 0) {
               return false;
           }
       } else if (month == 2) {

           if (IsNamNhuan(year)) {
               if (day > 29 || day <= 0) {
                   return false;
               }
           } else {
               if (day > 28 | day <= 0) {
                   return false;
               }
           }
       }
       return true;
   }
}
