package es.uma.sportjump.sjs.web.controller.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

class ValidationUtils {
	
	
	static boolean validateDateFormat(final String date) {
		String[] formatStrings = { "dd/mm/yyyy" };
		boolean isInvalidFormat = false;
		Date dateObj;
		for (String formatString : formatStrings) {
			try {
				SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
				sdf.applyPattern(formatString);
				sdf.setLenient(false);
				dateObj = sdf.parse(date);
				if (date.equals(sdf.format(dateObj))) {
					isInvalidFormat = false;
					break;
				}
			} catch (ParseException e) {
				isInvalidFormat = true;
			}
		}
		return isInvalidFormat;
	}
	
	static String createDateString(String day, String month, String year, String delimiter) {
		String date = null;
		if (!StringUtils.isEmpty(day) && 
			!StringUtils.isEmpty(month)	&& 
			!StringUtils.isEmpty(year)) {
			date = day + delimiter + month + delimiter + year;
		}
		return date;
	}

}
