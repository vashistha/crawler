package com.vk.crawler.core.util;
/*
 * Copyright (C) 2015 by Vashistha kumar
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtils {
  
  /**
   * 
   * @param day - Starts from 1, cannot be an invalid date of given month.
   * @param month - 1 for January,, 
   * @param year - in yyyy format
   * @return date.
   */
  public static Date getDate(int day, int month, int year) {
    if(day == 0 || month == 0 || year == 0) {
      return null;
    }
    GregorianCalendar cal = new GregorianCalendar();
    cal.set(Calendar.DAY_OF_MONTH, day);
    cal.set(Calendar.MONTH, month-1);
    cal.set(Calendar.YEAR, year);
    return cal.getTime();
  }

  public static Date getCurrentDate() {
    return Calendar.getInstance().getTime();
  }
  
  public static Date getDateOffsetByDay(Date in, int days) {
    if(in == null) {
      return in;
    }
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(in);
    cal.add(Calendar.DAY_OF_MONTH, - days);
    return cal.getTime();
  }
  public static Date getDateOffsetByMonth(Date in, int months) {
    if(in == null) {
      return in;
    }
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(in);
    cal.add(Calendar.MONTH, - months);
    return cal.getTime();
  }
  
  public static Date getDateOffsetByYear(Date in, int years) {
    if(in == null) {
      return in;
    }
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(in);
    cal.add(Calendar.YEAR, - years);
    return cal.getTime();
  }
  
  public static String getFormattedDate(String format, Date source) {
    if(source == null || StringUtils.isEmpty(format)) {
      return null;
    }
    return new SimpleDateFormat(format).format(source);
  }
  
  public static Date getCurrentMonthStartDate() {
    GregorianCalendar cal = new GregorianCalendar();
    cal.set(Calendar.DAY_OF_MONTH, 1);
    return cal.getTime();
  }

  public static Date getMonthStartDate(Date date) {
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(date);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    return cal.getTime();
  }

  public static Date getMonthEndDate(Date date) {
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(date);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.add(Calendar.MONTH, 1);
    cal.add(Calendar.DAY_OF_MONTH, -1);
    return cal.getTime();
  }

  public static Date getCurrentMonthEndDate() {
    GregorianCalendar cal = new GregorianCalendar();
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.add(Calendar.MONTH, 1);
    cal.add(Calendar.DAY_OF_MONTH, -1);
    return cal.getTime();
  }
  
  public static void clearTime(final Calendar cal) {
    cal.clear(Calendar.HOUR_OF_DAY);
    cal.clear(Calendar.MINUTE);
    cal.clear(Calendar.SECOND);
    cal.clear(Calendar.AM_PM);
    cal.clear(Calendar.HOUR);
    cal.clear(Calendar.MILLISECOND);
  }

  public static boolean isWeekEnd(Date in) {
    if(in == null) {
      return false;
    }
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(in);
    return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
  }
  
  public static boolean isBetWeenDates(Date in, Date from, Date to) {
    if(in != null) {
      return in.after(from) && in.before(to) || isEqualDate(in,from) || isEqualDate(in, to);
    }
    return false;
  }
  
  public static boolean isEqualDate(Date date1, Date date2) {
    if(date1 == null || date2 == null) {
      return false;
    }
    GregorianCalendar cal1 = new GregorianCalendar();
    cal1.setTime(date1);
    GregorianCalendar cal2 = new GregorianCalendar();
    cal2.setTime(date2);
    
    return cal1.get(Calendar.YEAR) == cal1.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
        && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE);
    
  }

  public static Date TheEndDate() {
    return getDate(01, 01, 2020);
  }
}
