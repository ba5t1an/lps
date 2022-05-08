/*************************************************************************
 *
 * CONFIDENTIAL
 * __________________
 *
 *  [2016] Bastian Schoettle & Tim Schoettle
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Bastian Schoettle & Tim Schoettle and his suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Bastian Schoettle & Tim Schoettle
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Bastian Schoettle & Tim Schoettle.
 *
 */
package com.lps.common.xml.result;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * @author schobast
 */
public class SubShiftResult {

    private String subshiftName;
    private DateTime startTime;
    private DateTime endTime;
    private int subshiftID;

    /**
     *
     */
    private List<RequiredEmployee> requiredEmployees;

    /**
     * @return
     */
    @XmlElementWrapper(name = "requiredEmployees")
    @XmlElement(name = "requiredEmployee")
    public List<RequiredEmployee> getRequiredEmployees() {
        return requiredEmployees;
    }

    /**
     * @param requiredEmployees
     */
    public void setRequiredEmployees(List<RequiredEmployee> requiredEmployees) {
        this.requiredEmployees = requiredEmployees;
    }

    @XmlElement
    public int getSubshiftID() {
        return subshiftID;
    }

    public void setSubshiftID(int subshiftID) {
        this.subshiftID = subshiftID;
    }

    @XmlElement
    public String getSubshiftName() {
        return subshiftName;
    }

    public void setSubshiftName(String subshiftName) {
        this.subshiftName = subshiftName;
    }

    public void addRequiredEmployee(RequiredEmployee empl) {
        this.requiredEmployees.add(empl);
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }
}
