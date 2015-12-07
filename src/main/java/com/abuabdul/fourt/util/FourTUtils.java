/*
 * Copyright 2013-2016 abuabdul.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.abuabdul.fourt.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	private static final DateFormat dd_MM_YYYY = new SimpleDateFormat("dd/MM/yyyy");

	public static String simpleDateStringWithDDMMYYYY(Date date) {
		if (date != null) {
			return dd_MM_YYYY.format(date);
		}
		return "";
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

}
