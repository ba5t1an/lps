package com.lps.shiftopt.model.basic;

import com.lps.common.model.NullObjective;
import com.lps.common.model.OptimizationProblem;
import com.lps.common.model.Variable;
import com.lps.shiftopt.model.AbstractResourceManager;
import com.lps.shiftopt.model.Employee;
import com.lps.shiftopt.model.Shift;
import org.joda.time.DateTime;

public class BasicShift extends Shift {

    public BasicShift(AbstractResourceManager parent, int id, String name, DateTime date) {
        super(parent, id, name, date);
    }

    @Override
    public OptimizationProblem generate() {
        optimizationProblem = new OptimizationProblem(this.getSystemId(), new NullObjective());
        optimizationProblem.addOptimizationProblems(createChildProblems());
        optimizationProblem.addConstraints(createEmployeeConstraints());
        optimizationProblem.addBinaryVariables(this.getBinaryVariables());
        return optimizationProblem;
    }

    @Override
    public String toString() {
        return this.generate().toString();
    }

    @Override
    public Variable createEmployeeVariable(Employee employee, float coefficient) {
        Variable var = new Variable(this.getSystemId() + employee.getSystemId(), coefficient);
        var.setNonNegative();
        Variable globalVariable = new Variable(this.getSystemId() + employee.getSystemId(), 1.f);
        globalVariable.setNonNegative();
        this.variableMap.put(globalVariable.getName(), new BasicVariableContainer(BasicVariableContainer.VariableType.EMPLOYEE_SHIFT, this, null, employee));
        addGlobalEmployeeVariable(employee, globalVariable);
        return var;
    }
}
