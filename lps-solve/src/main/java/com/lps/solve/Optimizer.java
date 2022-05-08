package com.lps.solve;

import com.google.ortools.linearsolver.MPSolver;
import com.lps.common.model.OptimizationProblem;

import java.util.Map;


public class Optimizer {

    private static final String SHARED_LIB_NAME = "jniortools";
    private static final String PROGRAM_NAME = "shiftopt";

    static {
        System.loadLibrary(SHARED_LIB_NAME);
    }

    private SolutionStatus solutionStatus;
    private MPSolver solver;
    private Solver generator;

    public Optimizer() {
        this.solutionStatus = SolutionStatus.NOT_SOLVED;
        this.solver = createSolver("CBC_MIXED_INTEGER_PROGRAMMING");
        this.generator = new Solver(solver);
    }

    private static MPSolver createSolver(String solverType) {
        return new MPSolver(PROGRAM_NAME,
                MPSolver.OptimizationProblemType.valueOf(solverType));
    }

    public SolutionStatus getSolutionStatus() {
        return solutionStatus;
    }

    public String getLpModel() {
        return generator.enableExport();
    }

    public void solve(OptimizationProblem problem) {
        this.generator.setProblem(problem);
        this.generator.enableOutput();
        MPSolver.ResultStatus status = this.generator.solve();
        if (status == MPSolver.ResultStatus.INFEASIBLE) {
            solutionStatus = SolutionStatus.INFEASIBLE;
        } else if (status == MPSolver.ResultStatus.OPTIMAL) {
            solutionStatus = SolutionStatus.OPTIMAL;
        } else {
            solutionStatus = SolutionStatus.FEASIBLE;
        }
    }

    public double getSolutionValue() {
        return this.generator.getSolutionValue();
    }

    public Map<String, Double> getVariableSolutionValues(boolean optimalOnly) {
        if (optimalOnly && (solutionStatus == SolutionStatus.NOT_SOLVED || solutionStatus == SolutionStatus.INFEASIBLE)) {
            throw new RuntimeException("Not solved or infeasible");
        }
        return this.generator.getSolution();
    }

    public enum SolutionStatus {
        INFEASIBLE, OPTIMAL, FEASIBLE, NOT_SOLVED
    }
}
