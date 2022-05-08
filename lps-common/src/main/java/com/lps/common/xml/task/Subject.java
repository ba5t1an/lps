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

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Subject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -954518554246350367L;

    private int disassemblyParts;

    private String subjectType;

    private int hourMinimization;

    private int costMinimization;

    private int defaultShiftID;

    private int defaultConditionID;

    private AttendenceStrategy attencenceStrategy;

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    @XmlElement
    public int getHourMinimization() {
        return hourMinimization;
    }

    public void setHourMinimization(int hourMinimization) {
        this.hourMinimization = hourMinimization;
    }

    @XmlElement
    public int getCostMinimization() {
        return costMinimization;
    }

    public void setCostMinimization(int costMinimization) {
        this.costMinimization = costMinimization;
    }

    public int getDefaultShiftID() {
        return defaultShiftID;
    }

    public void setDefaultShiftID(int defaultShiftID) {
        this.defaultShiftID = defaultShiftID;
    }

    public int getDefaultConditionID() {
        return defaultConditionID;
    }

    public void setDefaultConditionID(int defaultConditionID) {
        this.defaultConditionID = defaultConditionID;
    }

    public int getDisassemblyParts() {
        return disassemblyParts;
    }

    public void setDisassemblyParts(int disassemblyParts) {
        this.disassemblyParts = disassemblyParts;
    }

    public AttendenceStrategy getAttencenceStrategy() {
        return attencenceStrategy;
    }

    public void setAttencenceStrategy(AttendenceStrategy attencenceStrategy) {
        this.attencenceStrategy = attencenceStrategy;
    }
}
