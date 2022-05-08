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

public class Employee implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8348131328522874761L;

    private int emplID;
    private String emplName;
    private List<Integer> associatedRoles;
    private double availableWeeklyHours;
    private double overtime;
    private double costsPerHour;
    private boolean artificial;
    private List<DateTime> excludedDates;
    private List<Integer> excludedSubshifts;
    private int preferedSubshiftID;


    @XmlTransient
    private int periodDays;

    @XmlTransient
    private double priorityCoefficient;

    @XmlTransient
    private double actualDayHours;

    @XmlTransient
    private double remainingWorkload;

    @XmlTransient
    private double realCoefficient;

    /**
     *
     */
    public Employee() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Copy constructor
     *
     * @param source
     */
    public Employee(Employee source) {
        emplID = new Integer(source.emplID);
        emplName = new String(source.emplName);
        actualDayHours = new Double(source.actualDayHours);
        availableWeeklyHours = new Double(source.getAvailableWeeklyHours());
        costsPerHour = new Double(source.costsPerHour);
        priorityCoefficient = new Double(source.priorityCoefficient);
        excludedDates = source.excludedDates;
        excludedSubshifts = source.excludedSubshifts;
        overtime = new Double(source.overtime);
        associatedRoles = source.associatedRoles;
        artificial = new Boolean(source.artificial);
        periodDays = new Integer(source.periodDays);
        realCoefficient = new Double(source.realCoefficient);
        remainingWorkload = new Double(source.remainingWorkload);
        preferedSubshiftID = source.preferedSubshiftID;
    }

    public int getEmplID() {
        return emplID;
    }

    public void setEmplID(int emplID) {
        this.emplID = emplID;
    }

    public String getEmplName() {
        return emplName;
    }

    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }

    public double getAvailableWeeklyHours() {
        return availableWeeklyHours;
    }

    public void setAvailableWeeklyHours(double availableWeeklyHours) {
        this.availableWeeklyHours = availableWeeklyHours;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double getCostsPerHour() {
        return costsPerHour;
    }

    public void setCostsPerHour(double costsPerHour) {
        this.costsPerHour = costsPerHour;
    }

    @XmlElementWrapper(name = "excludedDates")
    @XmlElement(name = "excludedDate")
    public List<DateTime> getExcludedDates() {
        return excludedDates;
    }

    public void setExcludedDates(List<DateTime> excludedDates) {
        this.excludedDates = excludedDates;
    }

    @XmlElementWrapper(name = "associatedRoles")
    @XmlElement(name = "associatedRoleID")
    public List<Integer> getAssociatedRoles() {
        return associatedRoles;
    }

    public void setAssociatedRoles(List<Integer> associatedRoles) {
        this.associatedRoles = associatedRoles;
    }

    public boolean isArtificial() {
        return artificial;
    }

    public void setArtificial(boolean artificial) {
        this.artificial = artificial;
    }

    public List<Integer> getExcludedSubshifts() {
        return excludedSubshifts;
    }

    @XmlElementWrapper(name = "excludedSubshifts")
    @XmlElement(name = "excludedSubshiftID")
    public void setExcludedSubshifts(List<Integer> excludedSubshifts) {
        this.excludedSubshifts = excludedSubshifts;
    }

    public double getActualDayHours() {
        return actualDayHours;
    }

    public void setActualDayHours(double actualDayHours) {
        this.actualDayHours = actualDayHours;
    }

    public int getPreferedSubshiftID() {
        return preferedSubshiftID;
    }

    public void setPreferedSubshiftID(int preferedSubshiftID) {
        this.preferedSubshiftID = preferedSubshiftID;
    }

    public double getPriorityCoefficient() {
        return priorityCoefficient;
    }

    public void setPriorityCoefficient(double priorityCoefficient) {
        this.priorityCoefficient = priorityCoefficient;
    }

    public double getRemainingWorkload() {
        return remainingWorkload;
    }

    public void setRemainingWorkload(double remainingWorkload) {
        this.remainingWorkload = remainingWorkload;
    }

    public double getRealCoefficient() {
        return realCoefficient;
    }

    public void setRealCoefficient(double realCoefficient) {
        this.realCoefficient = realCoefficient;
    }

    public int getPeriodDays() {
        return periodDays;
    }

    public void setPeriodDays(int periodDays) {
        this.periodDays = periodDays;
    }
}
