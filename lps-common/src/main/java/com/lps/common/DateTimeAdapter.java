/*************************************************************************
 *
 * CONFIDENTIAL
 * __________________
 *
 *  [2016] Bastian Schoettle & Tim Schoettle
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Bastian Schoettle & Tim Schoettle and their suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Bastian Schoettle & Tim Schoettle
 * and their suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Bastian Schoettle & Tim Schoettle.
 *
 */
package com.lps.common;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.regex.Pattern;

public class DateTimeAdapter extends XmlAdapter<String, DateTime> {

    private static final String DATE_PATTERN = "\\d{2}-\\d{2}-\\d{4}";
    private static final String TIME_PATTERN = "\\d{2}:\\d{2}:\\d{2}";

    private Pattern datePattern;
    private Pattern timePattern;

    public DateTimeAdapter() {
        datePattern = Pattern.compile(DATE_PATTERN);
        timePattern = Pattern.compile(TIME_PATTERN);
    }

    private DateTime getMatch(String v) {
        DateTime dateTime = new DateTime();
        if (v == null) {
            return dateTime;
        } else if (datePattern.matcher(v).matches()) {
            dateTime = DateTimeFormat.forPattern("dd-MM-yyyy").parseDateTime(v);
        } else if (timePattern.matcher(v).matches()) {
            dateTime = DateTimeFormat.forPattern("HH:mm:ss").parseDateTime(v);
        }
        return dateTime;
    }

    @Override
    public String marshal(DateTime v) {
        return v.toString();
    }

    @Override
    public DateTime unmarshal(String v) {
        return getMatch(v);
    }

}
