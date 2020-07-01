package com.example.lapse.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	  private DateUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	  //convert Date format to Calendar format
	  public static  Calendar dateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	  }

	  //Check if start date is <= end date
	  public static boolean startDateBeforeEndDate(Calendar start, Calendar end) {
		  if(start.getTimeInMillis() <= end.getTimeInMillis()) {
			  return true;
		  }
		  else return false;
	  };

	  //Remove weekends when days applied  <=14
	  public static float removeWeekends(Calendar start, Calendar end) {
		  float daysWithoutWeekends = 0;
		  do {
			  if (start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
					  start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {;
					  daysWithoutWeekends++; }
			  start.add(Calendar.DAY_OF_MONTH, 1);
		  } 
		  while (start.getTimeInMillis() <= end.getTimeInMillis());
		  return daysWithoutWeekends;

	  }

}
