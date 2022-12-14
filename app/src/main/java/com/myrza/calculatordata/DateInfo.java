package com.myrza.calculatordata;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateInfo {

    public static String dateInformation(String inputData,
                                         String January, String February, String March,
                                         String April, String May, String June,
                                         String July, String August, String September,
                                         String October, String November, String December,
                                         String Monday, String Tuesday, String Wednesday,
                                         String Thursday, String Friday, String Saturday,
                                         String Sunday,
                                         String eto, String denVGodu, String exception,
                                         String leapYear1, String leapYear2, String notLeapYear1,
                                         String notLeapYear2, String godu) {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            String data = inputData.replaceAll("[\\s|\\D]+", " ").trim();

            Calendar calendar = Calendar.getInstance();
            String[] toArr = data.split(" ");

            int day = Integer.parseInt(toArr[0]);
            int month = Integer.parseInt(toArr[1]);
            int year = Integer.parseInt(toArr[2]);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d M yyyy H m");

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DATE, day);

            String data1 = "01 01 " + year + " 00 00";
            String data2 = data + " 00 00";


            LocalDateTime start = LocalDateTime.parse(data1, dateTimeFormatter);
            LocalDateTime end = LocalDateTime.parse(data2, dateTimeFormatter);

            Duration duration = Duration.between(start, end);

            String[] getCurData = simpleDateFormat.format(calendar.getTime()).split(" ");

            int currDay = Integer.parseInt(getCurData[0]);
            int currMonth = Integer.parseInt(getCurData[1]);
            int currYear = Integer.parseInt(getCurData[2]);

            String res1;

            if (isLeapYear(currYear)) {
                res1 = currYear + " - " + leapYear1 + "\n" + leapYear2 + " " + currYear + " " + godu + ": 366";
            } else {
                res1 = currYear + " - " + notLeapYear1 + "\n" + notLeapYear2 + " " + currYear + " " + godu + ": 365";
            }


            String res2 = currDay + " " + returnNameOfMonthInStringFormat(currMonth, January, February,
                    March, April, May, June, July, August, September, October, November, December) + " " + currYear
                    + " - " + returnNameDayOfWeekInStringFormat(calendar.get(Calendar.DAY_OF_WEEK), Monday, Tuesday, Wednesday, Thursday,
                    Friday, Saturday, Sunday);

            String res3 = eto + " " + (duration.toDays() + 1) + " " + denVGodu + ".";

            stringBuilder.append(res1);
            stringBuilder.append("\n\n");
            stringBuilder.append(res2);
            stringBuilder.append("\n");
            stringBuilder.append(res3);
        } catch (Exception e) {
            return exception;
        }

        return stringBuilder.toString();
    }

    public static String returnNameOfMonthInStringFormat(int month, String January, String February, String March,
                                                         String April, String May, String June,
                                                         String July, String August, String September,
                                                         String October, String November, String December) {


        if (month == 1) {
            return January;
        } else if (month == 2) {
            return February;
        } else if (month == 3) {
            return March;
        } else if (month == 4) {
            return April;
        } else if (month == 5) {
            return May;
        } else if (month == 6) {
            return June;
        } else if (month == 7) {
            return July;
        } else if (month == 8) {
            return August;
        } else if (month == 9) {
            return September;
        } else if (month == 10) {
            return October;
        } else if (month == 11) {
            return November;
        } else if (month == 12) {
            return December;
        } else return " ";
    }

    ///?????????????????????? ?????? ?????????? 1, ????-2, ????-3 ??????..
    public static String returnNameDayOfWeekInStringFormat(int week,
                                                           String Monday, String Tuesday, String Wednesday,
                                                           String Thursday, String Friday, String Saturday,
                                                           String Sunday) {

        if (week == 1) {
            return Sunday;
        } else if (week == 2) {
            return Monday;
        } else if (week == 3) {
            return Tuesday;
        } else if (week == 4) {
            return Wednesday;
        } else if (week == 5) {
            return Thursday;
        } else if (week == 6) {
            return Friday;
        } else if (week == 7) {
            return Saturday;
        } else return " ";

    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }


}
