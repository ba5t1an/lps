package com.lps.shiftopt.model.basic;

import com.lps.common.model.Constraint;
import com.lps.common.model.NullObjective;
import com.lps.common.model.OptimizationProblem;
import com.lps.common.model.Variable;
import com.lps.shiftopt.model.AbstractResourceManager;
import com.lps.shiftopt.model.Employee;
import com.lps.shiftopt.model.Shift;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicSharedSubshift extends BasicSubshift {

    private float sharedHours;
    private float soleHours;
    private int sharedCount;

    public BasicSharedSubshift(AbstractResourceManager parent, int id, String name, DateTime startTime, DateTime endTime, float minHours, float maxHours, float sharedHours, int count) {
        super(parent, id, name, startTime, endTime, minHours, maxHours);
        this.sharedHours = sharedHours;
        this.sharedCount = count;
        this.soleHours = this.totalWorkload - sharedHours;
    }

    public Variable createSharedEmployeeVariable(Employee e, int k, float coefficient) {
        Variable variable = new Variable(this.createEmployeeVariable(e, 1.f).getName() + "_" + k, coefficient);
        variable.setNonNegative();
        this.variableMap.put(variable.getName(), new BasicVariableContainer(BasicVariableContainer.VariableType.EMPLOYEE_SUBSHIFT_SHARED, (Shift) this.parent, this, e));
        addLocalEmployeeVariable(e, variable);
        return variable;
    }

    public Variable createBinarySharedEmployeeVariable(Employee e, int k, float coefficient) {
        Variable variable = new Variable(this.createBinaryEmployeeVariable(e, 1.f).getName() + "_" + k, coefficient);
        variable.setBinary();
        this.addBinaryVariable(variable);
        return variable;
    }

    public List<Constraint> createBinarySharedEmployeeConstraints() {
        List<Constraint> constraintList = new ArrayList<>();
        for (Employee employee : this.employees) {
            List<Variable> variables = new ArrayList<>();
            for (int i = 0; i < sharedCount; ++i) {
                Variable var = createBinarySharedEmployeeVariable(employee, i, 1.f);
                variables.add(var);
            }
            Constraint constraint = new Constraint(this.getConstraintPrefix(), variables, Constraint.Relation.LEQ, 1.f);
            constraintList.add(constraint);
        }
        return constraintList;
    }

    public Variable createSoleEmployeeVariable(Employee e, float coefficient) {
        Variable variable = new Variable(this.createEmployeeVariable(e, 1.f).getName() + "_s", coefficient);
        variable.setNonNegative();
        this.variableMap.put(variable.getName(), new BasicVariableContainer(BasicVariableContainer.VariableType.EMPLOYEE_SUBSHIFT_SOLE, (Shift) this.parent, this, e));
        this.addLocalEmployeeVariable(e, variable);
        return variable;
    }

    private Constraint createSoleHoursConstraints() {
        Constraint constraint = new Constraint(this.getConstraintPrefix());
        for (Employee employee : employees) {
            Variable variable = this.createSoleEmployeeVariable(employee, 1.f);
            constraint.addLhsVariable(variable);
        }
        constraint.setRelation(Constraint.Relation.GEQ);
        constraint.setRhs(this.soleHours);
        return constraint;
    }

    private Constraint createSharedHoursConstraints() {
        Constraint constraint = new Constraint(this.getConstraintPrefix());
        for (int i = 0; i < sharedCount; ++i) {
            for (Employee employee : employees) {
                Variable var = this.createSharedEmployeeVariable(employee, i, 1.f);
                constraint.addLhsVariable(var);
            }
        }
        constraint.setRelation(Constraint.Relation.GEQ);
        constraint.setRhs(sharedHours);
        return constraint;
    }

    private List<Constraint> createSharedBinaryActivationConstraints() {
        List<Constraint> constraints = new ArrayList<>();
        for (int i = 0; i < sharedCount; ++i) {
            for (Employee employee : employees) {
                Variable empVar = this.createSharedEmployeeVariable(employee, i, 1.f);
                Variable binaryVar = this.createBinarySharedEmployeeVariable(employee, i, 100.f);
                binaryVar.setBinary();
                Constraint constraint = new Constraint(this.getConstraintPrefix(), Arrays.asList(empVar, binaryVar), Constraint.Relation.LEQ, 1.f);
                constraints.add(constraint);
            }
        }
        return constraints;
    }

    private List<Constraint> createLocalEmployeeConstraints() {
        List<Constraint> constraintList = new ArrayList<>();
        for (Employee employee : this.employees) {
            List<Variable> variables = this.localEmployeeVariables.get(employee);
            Constraint constraint = new Constraint(getConstraintPrefix());
            for (Variable variable : variables) {
                constraint.addLhsVariable(variable);
            }
            constraint.addLhsVariable(createEmployeeVariable(employee, -1.f));
            constraint.setRelation(Constraint.Relation.EQ);
            constraint.setRhs(0.f);
            constraintList.add(constraint);
        }
        return constraintList;
    }

    @Override
    public OptimizationProblem generate() {
        optimizationProblem = new OptimizationProblem(this.getSystemId(), new NullObjective());
        optimizationProblem.addConstraint(createTotalWorkloadConstraint());
        optimizationProblem.addConstraint(createRequiredWorkloadConstraint());
        optimizationProblem.addConstraint(createSoleHoursConstraints());
        optimizationProblem.addConstraint(createSharedHoursConstraints());
        optimizationProblem.addConstraints(createSharedBinaryActivationConstraints());
        optimizationProblem.addConstraints(createBinarySharedEmployeeConstraints());
        optimizationProblem.addConstraints(createMinMaxHoursConstraints());
        optimizationProblem.addConstraints(createLocalEmployeeConstraints());
        optimizationProblem.addBinaryVariables(this.getBinaryVariables());
        return optimizationProblem;
    }

    @Override
    public String toString() {
        return generate().toString();
    }

}
