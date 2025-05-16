package com.kori1304.jpayouthdepartmentregister._common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
  public static LocalDate parse(String dateStr) {
    return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }


  public static DayOfWeek getDayOfWeek(LocalDate date) {
    return date.getDayOfWeek();
  }

  public static boolean isSunday(LocalDate date) {
    return getDayOfWeek(date) == DayOfWeek.SUNDAY;
  }

  public static String getDayOfWeekKorean(LocalDate date) {
    return switch (getDayOfWeek(date)) {
      case MONDAY -> "월요일";
      case TUESDAY -> "화요일";
      case WEDNESDAY -> "수요일";
      case THURSDAY -> "목요일";
      case FRIDAY -> "금요일";
      case SATURDAY -> "토요일";
      case SUNDAY -> "일요일";
    };
  }
}
