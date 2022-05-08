package com.lps.shiftopt.model;


import com.lps.common.model.OptimizationProblem;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Shift extends AbstractResourceManager {

    protected OptimizationProblem optimizationProblem;
    protected DateTime date;
    protected AbstractResourceManager parent;

    public Shift(AbstractResourceManager parent, final int id, final String name, DateTime date) {
        super(id, name);
        if (parent == null) {
            this.variableMap = new HashMap<>();
        } else {
            this.variableMap = parent.getVariableMap();
        }
        this.parent = parent;
        this.date = date;
        if (parent != null) {
            this.systemId = String.format("%s%d%d%d%03d", this.parent.getSystemId(), date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), id);
        } else {
            this.systemId = String.format("v%d%d%d%03d", date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), id);
        }
        this.employees = new ArrayList<>();
    }

    public DateTime getDate() {
        return this.date;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public void addAvailableEmployees(Employee... employees) {
        for (Employee employee : employees) {
            if (employee.isAvailableAtDate(this.getDate())) {
                addEmployee(employee);
            }
        }
    }

    public void addAvailableEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.isAvailableAtDate(this.getDate())) {
                addEmployee(employee);
            }
        }
    }

    public void addAvailableEmployee(Employee employee) {
        if (employee.isAvailableAtDate(this.getDate())) {
            addEmployee(employee);
        }
    }


    public void addSubshift(Subshift subshift) {
        this.children.add(subshift);
    }

    public void addSubshifts(List<Subshift> subshifts) {
        this.children.addAll(subshifts);
    }

    public void addSubshifts(Subshift... subshifts) {
        for (Subshift subshift : subshifts) {
            this.children.add(subshift);
        }
    }


    @Override
    public void freeze() {
        this.optimizationProblem = generate();
    }

    @Override
    public String toString() {
        return generate().toString();
    }
}
