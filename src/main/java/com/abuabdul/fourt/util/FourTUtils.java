package com.abuabdul.fourt.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.abuabdul.fourt.exception.FourTException;

/**
 * Utility class which has all the helper methods used across the application.
 * 
 * @author abuabdul
 *
 */
public class FourTUtils {

	private static final DateFormat dd_MM_YYYY = new SimpleDateFormat("DD-MM-YYYY");

	public static String simpleDateStringWithDDMMYYYY(Date date) {
		if (date != null) {
			return getForwardSlashInDateFormat(dd_MM_YYYY.format(date));
		}
		return null;
	}

	public static Date simpleDateWithDDMMYYYY(String dateStr) throws FourTException {
		try {
			if (dateStr != null) {
				return dd_MM_YYYY.parse(dateStr);
			}
		} catch (ParseException par) {
			new FourTException(par.getMessage(), par);
		}
		return null;
	}

	public static Date getUTCDateTime() {
		return new DateTime(DateTimeZone.UTC).toDate();
	}

	public static String getHyphensInDateFormat(String dateStr) {
		return StringUtils.replaceChars(dateStr, "/", "-");
	}

	public static String getForwardSlashInDateFormat(String dateStr) {
		return StringUtils.replaceChars(dateStr, "-", "/");
	}
}
