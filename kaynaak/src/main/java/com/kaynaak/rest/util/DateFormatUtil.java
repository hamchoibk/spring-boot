package com.kaynaak.rest.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.time.FastDateFormat;

public class DateFormatUtil {
	
	private static Map<String,FastDateFormat> formatterMap = new HashMap<String, FastDateFormat>();
	public static final String SHORT = "SHORT";

	public static final String MEDIUM = "MEDIUM";

	public static final String LONG = "LONG";

	public static final String FULL = "FULL";

	public static final String START_DAY = "00:00:00";

	public static final String END_DAY = "23:59:59";

	public static final String DATE_FORMAT_MMDDYYYY = "MM/dd/yyyy";

	public static final String DATE_TIME_FORMAT_MMDDYYYY_HHMMSS = "MM/dd/yyyy HH:mm:ss";
	
	public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";
	
	public static final String DATE_TIME_FORMAT_DDMMYYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	
	public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";

	public static final Locale usLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
	public static FastDateFormat getFormatter(String format) {
		return getFormatter(format, usLocale);
	}

	public static FastDateFormat getFormatter(String format, Locale locale) {
		if ((format == null) || (format.length() == 0))
			format = SHORT;
		String key = format + locale.toString();
		FastDateFormat formatter = (FastDateFormat) formatterMap.get(key);
		if (formatter == null) {
			int iFormat = -1;
			if (format.equalsIgnoreCase(FULL)) {
				iFormat = 0;
			} else if (format.equalsIgnoreCase(MEDIUM)) {
				iFormat = 2;
			} else if (format.equalsIgnoreCase(LONG)) {
				iFormat = 1;
			} else if (format.equalsIgnoreCase(SHORT))
				iFormat = 3;
			if (iFormat != -1) {
				formatter = FastDateFormat.getDateInstance(iFormat, locale);
			} else {
				formatter = FastDateFormat.getInstance(format, locale);
			}
			formatterMap.put(key, formatter);
		}
		return formatter;
	}

	public static FastDateFormat getFormatter(String format, Locale locale, TimeZone tz) {
		if ((format == null) || (format.length() == 0))
			format = SHORT;
		String key = format + locale.toString() + tz.getID();
		FastDateFormat formatter = (FastDateFormat) formatterMap.get(key);
		if (formatter == null) {
			int iFormat = -1;
			if (format.equalsIgnoreCase(FULL)) {
				iFormat = 0;
			} else if (format.equalsIgnoreCase(MEDIUM)) {
				iFormat = 2;
			} else if (format.equalsIgnoreCase(LONG)) {
				iFormat = 1;
			} else if (format.equalsIgnoreCase(SHORT))
				iFormat = 3;
			if (iFormat != -1) {
				formatter = FastDateFormat.getDateInstance(iFormat, tz, locale);
			} else {
				formatter = FastDateFormat.getInstance(format, tz, locale);
			}
			formatterMap.put(key, formatter);
		}
		return formatter;
	}
}
