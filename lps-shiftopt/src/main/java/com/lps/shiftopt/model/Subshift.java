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
package com.lps.shiftopt.model;

import com.lps.common.model.OptimizationProblem;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Subshift extends AbstractResourceManager {

    protected OptimizationProblem optimizationProblem;
    protected DateTime startTime;
    protected DateTime endTime;
    protected AbstractResourceManager parent;
    protected String totalWorkloadVariable;
    protected float totalWorkload;
    protected float minHours;
    protected float maxHours;

    public Subshift(AbstractResourceManager parent, int id, String name, DateTime startTime, DateTime endTime, float minHours, float maxHours) {
        super(id, name);
        this.parent = parent;
        if (parent == null) {
            this.variableMap = new HashMap<>();
        } else {
            this.variableMap = parent.getVariableMap();
        }
        this.systemId = String.format("%s%03d", this.parent.getSystemId(), id);
        this.minHours = minHours;
        this.maxHours = maxHours;
        this.employees = new ArrayList<>();
        setTime(startTime, endTime);
    }

    private void setTime(DateTime start, DateTime end) {
        Shift shift = (Shift) parent;
        DateTime parentDate = shift.getDate();
        if (end.isBefore(start)) {
            parentDate = shift.getDate().plusDays(1);
        }
        new DateTime();
        this.startTime = new DateTime(shift.getDate().getYear(), shift.getDate().getMonthOfYear(), shift.getDate().getDayOfMonth(), start.getHourOfDay(), start.getMinuteOfHour());
        this.endTime = new DateTime(parentDate.getYear(), parentDate.getMonthOfYear(), parentDate.getDayOfMonth(), end.getHourOfDay(), end.getMinuteOfHour());
        Period p = new Period(start, end);
        this.totalWorkload = (float) (p.getHours() + (p.getMinutes() / 60.));
    }

    @Override
    public String getSystemId() {
        return systemId;
    }

    public void addEmployee(Employee... employees) {
        for (Employee employee : employees) {
            this.employees.add(employee);
        }
    }

    public DateTime getStart() {
        return this.startTime;
    }


    public DateTime getEnd() {
        return this.endTime;
    }

    public void addAvailableEmployees(Employee... employees) {
        Shift shift = (Shift) parent;
        for (Employee employee : employees) {
            if (employee.isAvailableAtDate(shift.getDate()) && employee.isAvailableAtSubshift(id)) {
                addEmployee(employee);
            }
        }
    }

    public void addAvailableEmployees(List<Employee> employees) {
        Shift shift = (Shift) parent;
        for (Employee employee : employees) {
            if (employee.isAvailableAtDate(shift.getDate()) && employee.isAvailableAtSubshift(id)) {
                addEmployee(employee);
            }
        }
    }

    public void addAvailableEmployee(Employee employee) {
        Shift shift = (Shift) parent;
        if (employee.isAvailableAtDate(shift.getDate()) && employee.isAvailableAtSubshift(id)) {
            addEmployee(employee);
        }
    }

    public String getTotalWorkloadVariable() {
        return this.totalWorkloadVariable;
    }

    @Override
    public void freeze() {
        this.optimizationProblem = generate();
    }
}
