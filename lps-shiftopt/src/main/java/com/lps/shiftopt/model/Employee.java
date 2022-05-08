package com.lps.shiftopt.model;

import org.joda.time.DateTime;

import java.util.List;

public class Employee implements VariableProducer {

    private VariableProducer parent;
    private int id;
    private String name;
    private List<Integer> excludedSubshifts;
    private List<DateTime> excludedDates;
    private List<DateTime> excludedTimes;
    private String systemId;
    private String binaryVariable;
    private float coefficient;
    private float maxWeeklyWorkload;
    private float cost;

    public Employee(VariableProducer parent, int id, String name) {
        this.parent = parent;
        this.id = id;
        this.name = name;
        this.systemId = String.format("%s%03d", this.parent.getSystemId(), id);
        this.binaryVariable = this.systemId + "b";
        this.setCoefficient(1.f);
    }

    public Employee(int id, String name, float maxWeeklyWorkload) {
        this.id = id;
        this.name = name;
        this.systemId = String.format("%03d", id);
        this.binaryVariable = this.systemId + "b";
        this.setCoefficient(1.f);
        this.maxWeeklyWorkload = maxWeeklyWorkload;
        this.setCost(this.getCoefficient());
    }

    public Employee(int id, String name, List<Integer> excludedSubshifts, List<DateTime> excludedDates, List<DateTime> excludedTimes, float maxWeeklyWorkload) {
        this.id = id;
        this.name = name;
        this.systemId = String.format("%03d", id);
        this.binaryVariable = this.systemId + "b";
        this.excludedDates = excludedDates;
        this.excludedTimes = excludedTimes;
        this.excludedSubshifts = excludedSubshifts;
        this.setCoefficient(1.f);
        this.setCost(this.getCoefficient());
    }

    public float getCost() {
        return this.cost;
    }


    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setMaxWeeklyWorkload(float max) {
        this.maxWeeklyWorkload = max;
    }

    public float setMaxWeeklyWorkload() {
        return this.maxWeeklyWorkload;
    }

    public String getBinaryVariable() {
        return this.binaryVariable;
    }

    public boolean isAvailableAtDate(DateTime date) {
        if (excludedDates == null) {
            return true;
        }
        for (DateTime dateTime : excludedDates) {
            if (dateTime.getYear() == date.getYear()) {
                if (dateTime.getMonthOfYear() == date.getMonthOfYear()) {
                    if (dateTime.getDayOfMonth() == date.getDayOfMonth()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isAvailableAtSubshift(int subshift) {
        if (excludedSubshifts == null) {
            return true;
        }
        for (Integer id : excludedSubshifts) {
            if (id == subshift) {
                return false;
            }
        }
        return true;
    }

    public String getSystemId() {
        return systemId;
    }

    public List<Integer> getExcludedSubshifts() {
        return excludedSubshifts;
    }

    public void setExcludedSubshifts(List<Integer> excludedSubshifts) {
        this.excludedSubshifts = excludedSubshifts;
    }

    public List<DateTime> getExcludedDates() {
        return excludedDates;
    }

    public void setExcludedDates(List<DateTime> excludedDates) {
        this.excludedDates = excludedDates;
    }

    public List<DateTime> getExcludedTimes() {
        return excludedTimes;
    }

    public void setExcludedTimes(List<DateTime> excludedTimes) {
        this.excludedTimes = excludedTimes;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public boolean equals(Object o) {
        if (o != this) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee e = (Employee) o;
        return e.id == this.id;
    }

    public int hashCode() {
        return this.id;
    }

}
