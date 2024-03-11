package com.example.englishschedule.model;

public class Date {
    public static boolean isLaterOther(String thisDate, String other){
        int[] arrThisDate = splitDate(thisDate);
        int[] arrOtherDate = splitDate(other);
        if(arrOtherDate== null || arrThisDate==null)
            return true;
        if(arrThisDate[2] > arrOtherDate[2])
            return true;
        else if (arrThisDate[2] == arrOtherDate[2] && arrThisDate[1] > arrOtherDate[1])
            return true;
        else if (arrThisDate[2] == arrOtherDate[2] && arrThisDate[1] == arrOtherDate[1] && arrThisDate[0]>arrOtherDate[0])
            return true;
        return false;
    }


    public static int[] splitDate(String date){
        int[] arrDate = new int[3];
        String[] arrStringDate = date.split("/");
        if (arrStringDate.length == 3){
            arrDate[0] = Integer.parseInt(arrStringDate[0]);
            arrDate[1] = Integer.parseInt(arrStringDate[1]);
            arrDate[2] = Integer.parseInt(arrStringDate[2]);
            return arrDate;
        }
        return null;
    }

}
