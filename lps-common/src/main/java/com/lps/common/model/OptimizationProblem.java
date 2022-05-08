package com.lps.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimizationProblem {

    private Objective objective;
    private List<Constraint> constraints;
    private List<Variable> binaryVariables;
    private List<OptimizationProblem> problems;
    private String name;

    public OptimizationProblem(String name, Objective objective, List<Constraint> constraints) {
        this.objective = objective;
        this.name = name;
        this.constraints = constraints;
        this.problems = new ArrayList<>();
    }

    public OptimizationProblem(String name, Objective objective) {
        this.objective = objective;
        this.name = name;
        this.constraints = new ArrayList<>();
        this.problems = new ArrayList<>();
        this.binaryVariables = new ArrayList<>();
    }

    public void addConstraint(Constraint constraint) {
        this.constraints.add(constraint);
    }

    public void addBinaryVariable(Variable var) {
        this.binaryVariables.add(var);
    }

    public void addBinaryVariables(List<Variable> vars) {
        this.binaryVariables.addAll(vars);
    }

    public void addOptimizationProblem(OptimizationProblem problem) {
        this.problems.add(problem);
    }

    public void addOptimizationProblems(List<OptimizationProblem> problems) {
        this.problems.addAll(problems);
    }

    public void addConstraints(List<Constraint> constraints) {
        this.constraints.addAll(constraints);
    }

    public List<OptimizationProblem> getProblems() {
        return Collections.unmodifiableList(this.problems);
    }

    public List<Constraint> getConstraints() {
        return Collections.unmodifiableList(this.constraints);
    }

    public Objective getObjective() {
        return this.objective;
    }

    @Override
    public String toString() {
        if (problems.size() == 0) {
            StringBuilder builder = new StringBuilder(name + ":\n");
            for (Constraint constraint : constraints) {
                builder.append(constraint.toString() + "\n");
            }
            return builder.toString();
        }
        StringBuilder builder = new StringBuilder(name + ":\n");
        builder.append(this.objective.toString());
        for (Constraint constraint : constraints) {
            builder.append(constraint.toString() + "\n");
        }
        for (OptimizationProblem problem : this.problems) {
            builder.append(problem.toString());
        }
        return builder.toString();
    }

    public List<Variable> getBinaryVariables() {
        return this.binaryVariables;
    }

    public String toCplexLPString() {
        if (problems.size() == 0) {
            StringBuilder builder = new StringBuilder();
            for (Constraint constraint : constraints) {
                builder.append(constraint.toCplexString() + "\n");
            }
            return builder.toString();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(this.objective.toCplexString());
        for (Constraint constraint : constraints) {
            builder.append(constraint.toCplexString() + "\n");
        }
        for (OptimizationProblem problem : this.problems) {
            builder.append(problem.toCplexLPString());
        }

        if (this.binaryVariables.size() > 0) {
            for (Variable var : this.binaryVariables) {
                builder.append(var.getName() + " <= 1\n");
            }
            builder.append("Binaries\n");
            for (Variable var : this.binaryVariables) {
                builder.append(var.getName() + "\n");
            }
        }
        return builder.toString();
    }
}
