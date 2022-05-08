package com.lps.shiftopt.model.basic;

import com.lps.shiftopt.model.Employee;
import com.lps.shiftopt.model.Shift;
import com.lps.shiftopt.model.Subshift;

public class BasicVariableContainer {

    private Shift shift;
    private Subshift subshift;
    private Employee employee;
    private VariableType type;

    public BasicVariableContainer(VariableType type, Shift shift, Subshift subshift, Employee employee) {
        this.shift = shift;
        this.subshift = subshift;
        this.employee = employee;
        this.type = type;
    }

    public VariableType getType() {
        return this.type;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Subshift getSubshift() {
        return subshift;
    }

    public void setSubshift(Subshift subshift) {
        this.subshift = subshift;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public enum VariableType {
        EMPLOYEE_SUBSHIFT, EMPLOYEE_SUBSHIFT_SOLE, EMPLOYEE_SUBSHIFT_SHARED, EMPLOYEE_SHIFT, EMPLOYEE_PERIOD, EMPLOYEE_OBJECTIVE, TOTAL_WORKLOAD_SUBSHIFT
    }
}
