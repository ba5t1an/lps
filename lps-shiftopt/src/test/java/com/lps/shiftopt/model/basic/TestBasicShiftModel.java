package com.lps.shiftopt.model.basic;

import com.lps.common.model.OptimizationProblem;
import com.lps.common.solution.ShiftModelSolution;
import com.lps.shiftopt.model.Employee;
import com.lps.shiftopt.model.Shift;
import com.lps.shiftopt.model.Subshift;
import com.lps.shiftopt.solution.basic.BasicSolutionProcessor;
import com.lps.solve.Optimizer;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class TestBasicShiftModel {

    private BasicShiftModel model;

    private Employee[] getEmployeeList() {
        Employee e0 = new Employee(0, "e", 40.f);
        Employee e1 = new Employee(1, "e", 40.f);
        Employee[] employees = new Employee[]{e0, e1};
        return employees;
    }

    @Before
    public void initialize() {
        DateTime st = new DateTime(2005, 3, 26, 8, 30);
        DateTime et = new DateTime(2005, 3, 26, 16, 30);
        DateTime dt = new DateTime(2005, 3, 26, 0, 0, 0, 0);
        model = new BasicShiftModel(0, "model", st, et);
        Shift shift = new BasicShift(model, 0, "shift", dt);
        Subshift subshift = new BasicSharedSubshift(shift, 0, "subshift", st, et, 4.f, 10.5f, 4, 2);
        shift.addSubshift(subshift);
        DateTime st1 = new DateTime(2005, 3, 26, 16, 30);
        DateTime et1 = new DateTime(2005, 3, 26, 23, 30);
        Subshift subshift1 = new BasicSharedSubshift(shift, 1, "subshift", st1, et1, 4.f, 10.5f, 4, 2);
        shift.addSubshift(subshift1);
        model.addAvailableEmployees(getEmployeeList());
        model.addShift(shift);
        //System.out.println(shift.generate().toString());
    }

    @Test
    public void testBasicShiftModel() {
        Optimizer solver = new Optimizer();
        OptimizationProblem problem = model.generate();
        solver.solve(problem);
        Map<String, Double> solution = solver.getVariableSolutionValues(false);
        if (solution != null) {
            BasicSolutionProcessor solutionProcessor = new BasicSolutionProcessor(solution, model);
            solutionProcessor.processSolution();
            ShiftModelSolution modelSolution = solutionProcessor.getSolution();
            System.out.println(modelSolution.toString());
        }
    }

}
