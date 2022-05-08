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

public class Shift implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7817696053574538667L;
    private String shiftType;
    private int shiftID;
    private String shiftName;
    private List<DateTime> includedDates;
    private List<Subshift> subshifts;

    @XmlTransient
    private double coveringHours;

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    @XmlElementWrapper(name = "includedDates")
    @XmlElement(name = "includedDate")
    public List<DateTime> getIncludedDates() {
        return includedDates;
    }

    public void setIncludedDates(List<DateTime> includedDates) {
        this.includedDates = includedDates;
    }

    public boolean isIncluded(DateTime date) {
        return includedDates.contains(date);
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public List<Subshift> getSubshifts() {
        return subshifts;
    }

    @XmlElementWrapper(name = "subshifts")
    @XmlElement(name = "subshift")
    public void setSubshifts(List<Subshift> subshifts) {
        this.subshifts = subshifts;
    }

    public double getCoveringHours() {
        if (coveringHours == 0) {
            setCoveringHours();
        }
        return coveringHours;
    }

    public void setCoveringHours() {
        for (Subshift sub : subshifts) {
            this.coveringHours += sub.getDuration();
        }
    }

}
