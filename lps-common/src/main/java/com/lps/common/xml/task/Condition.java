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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

public class Condition implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4092435378899699616L;
    private int conditionID;
    private double minDailyHours;
    private double maxDailyHours;
    private double multipleRequiredHours;
    private double multipleRequiredPercentage;
    private int multipleRequiredCount;
    private List<RequiredRole> requiredRoles;
    @XmlTransient
    private double preferedDailyHours;

    public int getConditionID() {
        return conditionID;
    }

    public void setConditionID(int conditionID) {
        this.conditionID = conditionID;
    }

    public double getMinDailyHours() {
        return minDailyHours;
    }

    public void setMinDailyHours(double minDailyHours) {
        this.minDailyHours = minDailyHours;
    }

    public double getMaxDailyHours() {
        return maxDailyHours;
    }

    public void setMaxDailyHours(double maxDailyHours) {
        this.maxDailyHours = maxDailyHours;
    }

    public double getMultipleRequiredHours() {
        return multipleRequiredHours;
    }

    public void setMultipleRequiredHours(double multipleRequiredHours) {
        this.multipleRequiredHours = multipleRequiredHours;
    }

    public int getMultipleRequiredCount() {
        return multipleRequiredCount;
    }

    public void setMultipleRequiredCount(int multipleRequiredCount) {
        this.multipleRequiredCount = multipleRequiredCount;
    }

    public double getMultipleRequiredPercentage() {
        return multipleRequiredPercentage;
    }

    public void setMultipleRequiredPercentage(double multipleRequiredPercentage) {
        this.multipleRequiredPercentage = multipleRequiredPercentage;
    }

    @XmlElementWrapper(name = "requiredRoles")
    @XmlElement(name = "requiredRole")
    public List<RequiredRole> getRequiredRoles() {
        return requiredRoles;
    }

    public void setRequiredRoles(List<RequiredRole> requiredRoles) {
        this.requiredRoles = requiredRoles;
    }

    public double getPreferedDailyHours() {
        return preferedDailyHours;
    }

    public void setPreferedDailyHours(double preferedDailyHours) {
        this.preferedDailyHours = preferedDailyHours;
    }

}
