package com.lps.shiftopt.model.basic;

import com.lps.common.model.Constraint;
import com.lps.common.model.OptimizationProblem;
import com.lps.common.model.Variable;
import com.lps.shiftopt.model.AbstractResourceManager;
import com.lps.shiftopt.model.Employee;
import com.lps.shiftopt.model.Shift;
import com.lps.shiftopt.model.Subshift;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicSubshift extends Subshift {

    public BasicSubshift(AbstractResourceManager parent, int id, String name, DateTime startTime, DateTime endTime, float minHours, float maxHours) {
        super(parent, id, name, startTime, endTime, minHours, maxHours);
    }

    protected Variable createTotalWorkloadVariable(float coefficient) {
        return new Variable(this.parent.getSystemId() + this.getSystemId() + "w", coefficient);
    }

    public Variable createEmployeeVariable(final Employee e, float coefficient) {
        Variable var = new Variable(this.getSystemId() + e.getSystemId(), coefficient);
        var.setNonNegative();
        this.variableMap.put(var.getName(), new BasicVariableContainer(BasicVariableContainer.VariableType.EMPLOYEE_SUBSHIFT, (Shift) this.parent, this, e));
        addGlobalEmployeeVariable(e, var);
        return var;
    }

    public Variable createBinaryEmployeeVariable(Employee e, float coefficient) {
        Variable binaryVar = new Variable(this.getSystemId() + e.getBinaryVariable(), coefficient);
        binaryVar.setBinary();
        this.addBinaryVariable(binaryVar);
        return binaryVar;
    }

    /*
     * Constraint to calculate the total workload
     */
    public Constraint createTotalWorkloadConstraint() {
        Constraint constraint = new Constraint(this.getConstraintPrefix());
        for (Employee employee : employees) {
            constraint.addLhsVariable(createEmployeeVariable(employee, 1.f));
        }
        constraint.addLhsVariable(createTotalWorkloadVariable(-1.f));
        constraint.setRelation(Constraint.Relation.EQ);
        constraint.setRhs(0.f);
        return constraint;
    }

    public List<Constraint> createMinMaxHoursConstraints() {
        List<Constraint> constraintList = new ArrayList<>();
        for (Employee employee : employees) {
            constraintList.add(new Constraint(this.getConstraintPrefix(), Arrays.asList(this.createEmployeeVariable(employee, 1.f), this.createBinaryEmployeeVariable(employee, -1000.f)), Constraint.Relation.LEQ, 0.f));
            constraintList.add(new Constraint(this.getConstraintPrefix(), Arrays.asList(this.createEmployeeVariable(employee, 1.f), this.createBinaryEmployeeVariable(employee, -minHours)), Constraint.Relation.GEQ, 0.f));
            constraintList.add(new Constraint(this.getConstraintPrefix(), Arrays.asList(this.createEmployeeVariable(employee, 1.f)), Constraint.Relation.LEQ, maxHours));
        }
        return constraintList;
    }

    public Constraint createRequiredWorkloadConstraint() {
        Constraint workload = new Constraint(this.getConstraintPrefix());
        for (Employee employee : employees) {
            workload.addLhsVariable(createEmployeeVariable(employee, 1.f));
        }
        workload.setRelation(Constraint.Relation.GEQ);
        workload.setRhs(this.totalWorkload);
        return workload;
    }

    @Override
    public OptimizationProblem generate() {
        if (optimizationProblem != null) {
            reset();
        }
        return null;
    }

}
