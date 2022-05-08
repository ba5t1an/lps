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
package com.lps.common.xml.result;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * @author schobast
 */
public class ShiftResult {

    /*
     * <shiftID></shiftID> <shiftDate></shiftDate> <collaborations>
     * <employeeID></employeeID> <employeeID></employeeID> </collaborations>
     */
    /**
     *
     */
    private String shiftID;
    /**
     *
     */
    private DateTime shiftDate;

    private List<SubShiftResult> subshiftResults;

    /**
     * @return
     */

    @XmlElementWrapper(name = "subshiftResults")
    @XmlElement(name = "subshiftResult")
    public List<SubShiftResult> getSubshiftResults() {
        return subshiftResults;
    }

    /**
     * @param subshiftResults
     */
    public void setSubshiftResults(List<SubShiftResult> subshiftResults) {
        this.subshiftResults = subshiftResults;
    }

    /**
     * @return
     */
    @XmlElement
    public String getShiftID() {
        return shiftID;
    }

    /**
     * @param shiftID
     */
    public void setShiftID(String shiftID) {
        this.shiftID = shiftID;
    }

    /**
     * @return
     */
    @XmlElement
    public DateTime getShiftDate() {
        return shiftDate;
    }

    /**
     * @param shiftDate
     */
    public void setShiftDate(DateTime shiftDate) {
        this.shiftDate = shiftDate;
    }

    public void addSubshiftResult(SubShiftResult res) {
        this.subshiftResults.add(res);
    }
}
