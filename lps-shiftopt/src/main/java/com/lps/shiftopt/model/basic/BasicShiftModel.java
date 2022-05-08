package com.lps.shiftopt.model.basic;

import com.lps.common.model.Objective;
import com.lps.common.model.OptimizationProblem;
import com.lps.common.model.Variable;
import com.lps.shiftopt.model.Employee;
import com.lps.shiftopt.model.ShiftModel;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class BasicShiftModel extends ShiftModel {

    public BasicShiftModel(int id, String name, DateTime start, DateTime end) {
        super(id, name, start, end);
    }

    public String getSystemId() {
        return this.systemId;
    }

    public void freeze() {
        this.optimizationProblem = generate();
    }

    public void collectBinaries(OptimizationProblem main, OptimizationProblem problem) {
        for (OptimizationProblem subproblem : problem.getProblems()) {
            main.addBinaryVariables(subproblem.getBinaryVariables());
            if (subproblem.getProblems().size() > 0) {
                collectBinaries(main, subproblem);
            }
        }
    }

    public OptimizationProblem generate() {
        List<Variable> objectiveVars = new ArrayList<>();
        for (Employee e : this.employees) {
            Variable objectiveVar = this.createEmployeeVariable(e, e.getCost());
            objectiveVars.add(objectiveVar);
        }
        Objective objective = new Objective(objectiveVars, Objective.OptimizationObjective.MIN);
        optimizationProblem = new OptimizationProblem(this.getSystemId(), objective);
        optimizationProblem.addOptimizationProblems(createChildProblems());
        optimizationProblem.addConstraints(createEmployeeConstraints());
        optimizationProblem.addBinaryVariables(this.getBinaryVariables());
        for (OptimizationProblem subproblem : optimizationProblem.getProblems()) {
            collectBinaries(optimizationProblem, subproblem);
        }
        return optimizationProblem;
    }

    @Override
    public Variable createEmployeeVariable(Employee employee, float coefficient) {
        Variable var = new Variable(this.getSystemId() + employee.getSystemId(), coefficient);
        var.setNonNegative();
        this.variableMap.put(var.getName(), new BasicVariableContainer(BasicVariableContainer.VariableType.EMPLOYEE_PERIOD, null, null, employee));
        return var;
    }
}
