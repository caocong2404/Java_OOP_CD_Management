package util;

import java.util.Calendar;

public class MyToyValid {

    public static boolean checkYearValid(int year) {
        int currenYear = Calendar.getInstance().get(Calendar.YEAR);

        if (year > currenYear || year < 1980) {
            return false;
        }
        return true;
    }

    public static boolean checkMonthValid(int month) {
        if (month < 1 || month < 12) {
            return false;
        }
        return true;
    }

    public static boolean isNamNhuan(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 4 == 0 && year % 100 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDayValid(int day, int month, int year) {
        if (month == 1 || month == 3 || month == 5 || month == 7 
                || month == 8 || month == 10 || month == 12) {
            if (day < 1 || day > 31) 
                return false;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day < 1 || day > 30)
                return false;
        } else if (month == 2) {
            if (isNamNhuan(year) == true){
                if (day < 1 || day > 29)
                    return false;
                            
            } else {
                if (day < 1 || day > 28)
                    return false;
            }
    }
        return true;
    }
    
    public static boolean checkPhoneValid(String phoneNumber){
        for (int i = 0; i < phoneNumber.length(); i++) 
            if(!Character.isDigit(phoneNumber.charAt(i)))
                return false;
            
        return true;
    }
    
    public static boolean checkEmailValid(String emailAddress, String pattern){
        //^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$
        if (!emailAddress.matches(pattern))
            return false;
        return true;
    }
}
