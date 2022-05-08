package com.lps.solve;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.lps.common.model.Constraint;
import com.lps.common.model.Objective;
import com.lps.common.model.OptimizationProblem;
import com.lps.common.model.Variable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver {

    private Map<String, MPVariable> solutionVariables;
    private MPSolver solver;
    private double infinity;

    public Solver(MPSolver solver) {
        this.solver = solver;
        this.solutionVariables = new HashMap<String, MPVariable>();
        this.infinity = MPSolver.infinity();
    }

    private MPVariable makeVariable(Variable var) {
        MPVariable solutionVariable = this.solutionVariables.get(var.getName());
        if (solutionVariable != null) {
            return solutionVariable;
        }
        if (var.isBinary()) {
            MPVariable variable = solver.makeIntVar(0., 1.0, var.getName());
            this.solutionVariables.put(var.getName(), variable);
            return variable;
        } else if (var.isInteger()) {
            MPVariable variable = solver.makeIntVar(var.getLowerBound(), var.getUpperBound(), var.getName());
            this.solutionVariables.put(var.getName(), variable);
            return variable;
        }
        MPVariable variable = solver.makeNumVar(var.getLowerBound(), var.getUpperBound(), var.getName());
        this.solutionVariables.put(var.getName(), variable);
        return variable;
    }

    private void makeConstraint(Constraint constraint) {
        MPConstraint constraint1;
        if (constraint.getRelation() == Constraint.Relation.LEQ) {
            constraint1 = solver.makeConstraint(-infinity, constraint.getRhs());
        } else if (constraint.getRelation() == Constraint.Relation.GEQ) {
            constraint1 = solver.makeConstraint(constraint.getRhs(), +infinity);
        } else {
            constraint1 = solver.makeConstraint(constraint.getRhs(), constraint.getRhs());
        }
        List<Variable> variables = constraint.getLhsVariables();
        for (Variable var : variables) {
            constraint1.setCoefficient(makeVariable(var), var.getCoefficient());
        }
    }

    private void makeObjective(Objective objective) {
        MPObjective objective1 = solver.objective();
        for (Variable var : objective.getVariables()) {
            MPVariable objectiveVariable = makeVariable(var);
            objective1.setCoefficient(objectiveVariable, var.getCoefficient());
        }
        if (objective.getObjective() == Objective.OptimizationObjective.MIN) {
            objective1.setMinimization();
        } else {
            objective1.setMaximization();
        }
    }

    private void makeConstraints(OptimizationProblem problem) {
        for (Constraint constraint : problem.getConstraints()) {
            makeConstraint(constraint);
        }
        for (OptimizationProblem localProblem : problem.getProblems()) {
            makeConstraints(localProblem);
        }
    }

    public void setProblem(OptimizationProblem problem) {
        makeConstraints(problem);
        makeObjective(problem.getObjective());
    }

    public void reset() {
        this.solver.reset();
    }

    public String enableExport() {
        return this.solver.exportModelAsLpFormat(true);
    }

    public void enableOutput() {
        this.solver.enableOutput();
    }

    public double getSolutionValue() {
        return solver.objective().value();
    }

    public double getSolutionBestBound() {
        return solver.objective().bestBound();
    }

    public MPSolver.ResultStatus solve() {
        return solver.solve();
    }

    public Map<String, Double> getSolution() {
        Map<String, Double> solutions = new HashMap<String, Double>();
        for (Map.Entry<String, MPVariable> entry : solutionVariables.entrySet()) {
            solutions.put(entry.getKey(), entry.getValue().solutionValue());
        }
        return solutions;
    }
}
