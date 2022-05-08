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
package com.lps.common.xml.task;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

public class Period implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5405801826850971174L;
    private int periodID;

    private DateTime startDate;

    private DateTime endDate;

    private List<DateTime> excludedDates;

    @XmlTransient
    private int periodDays;

    @XmlTransient
    private int coveredShifts;

    @XmlTransient
    private double coveredHours;

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public int getPeriodID() {
        return periodID;
    }

    public void setPeriodID(int periodID) {
        this.periodID = periodID;
    }

    @XmlElementWrapper(name = "excludedDates")
    @XmlElement(name = "excludedDate")
    public List<DateTime> getExcludedDates() {
        return excludedDates;
    }

    public void setExcludedDates(List<DateTime> excludedDates) {
        this.excludedDates = excludedDates;
    }

    public boolean isExcluded(DateTime date) {
        if (excludedDates == null) {
            return false;
        }
        return excludedDates.contains(date);
    }

    public boolean isInPeriod(DateTime date) {
        if ((date.isAfter(startDate) && date.isBefore(endDate)) || (date.isEqual(startDate) || date.isEqual(endDate))) {
            return !excludedDates.contains(date);
        }
        return false;
    }

    public boolean isBeforeEnd(DateTime date) {
        return date.isBefore(endDate) || date.isEqual(endDate);
    }

    public boolean isEnd(DateTime date) {
        return date.equals(endDate);
    }

    /**
     * @return
     */
    public int getPeriodDays() {
        if (periodDays == 0) {
            DateTime currentDate = this.startDate;
            while (isBeforeEnd(currentDate) || isEnd(currentDate)) {
                if (excludedDates == null) {
                    periodDays++;
                } else if (!excludedDates.contains(currentDate)) {
                    periodDays++;
                }
                currentDate = currentDate.plusDays(1);
            }
        }
        return periodDays;
    }

    public int getCoveredShifts() {
        return coveredShifts;
    }

    public void increaseCoveredShifts(int count) {
        this.coveredShifts += count;
    }

    public double getCoveredHours() {
        return coveredHours;
    }

    public void increaseCoveredHours(double hours) {
        this.coveredHours += hours;
    }
}
