package com.lps.shiftopt.model;

import com.lps.common.model.OptimizationProblem;
import com.lps.shiftopt.model.basic.BasicVariableContainer;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShiftModel extends AbstractResourceManager {

    protected OptimizationProblem optimizationProblem;

    public ShiftModel(int id, String name, DateTime start, DateTime end) {
        super(new HashMap<String, BasicVariableContainer>(), id, name);
        this.systemId = String.format("v%03d", id);
    }

    public ShiftModel(Map<String, BasicVariableContainer> variableMap, int id, String name, DateTime start, DateTime end) {
        super(variableMap, id, name);
        this.systemId = String.format("v%03d", id);
    }

    public void addAvailableEmployees(Employee... employees) {
        for (Employee employee : employees) {
            addEmployee(employee);
        }
    }

    public void addAvailableEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            addEmployee(employee);
        }
    }

    public void addShift(Shift shift) {
        this.children.add(shift);
    }

    public void addShifts(List<Shift> shifts) {
        this.children.addAll(shifts);
    }

    public void addShifts(Shift... shifts) {
        for (Shift shift : shifts) {
            this.children.add(shift);
        }
    }

    public void addAvailableEmployee(Employee employee) {
        addEmployee(employee);
    }
}
