package com.lps.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Objective {

    private List<Variable> variables;
    private OptimizationObjective objective;

    public Objective() {
        this.variables = new ArrayList<>();
        this.objective = OptimizationObjective.MIN;
    }

    public Objective(List<Variable> variables, OptimizationObjective objective) {
        this.variables = variables;
        this.objective = objective;
    }

    public List<Variable> getVariables() {
        return Collections.unmodifiableList(variables);
    }

    public OptimizationObjective getObjective() {
        return this.objective;
    }

    private String goalToString() {
        if (objective == OptimizationObjective.MIN) {
            return "Minimize\n";
        }
        return "Maximize\n";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(goalToString());
        int cnt = 0;
        for (Variable var : variables) {
            builder.append(var.toString());
            if (cnt != variables.size() - 1) {
                builder.append(" + ");
            }
            ++cnt;
        }
        builder.append("\n");
        return builder.toString();
    }

    public String toCplexString() {
        StringBuilder builder = new StringBuilder(goalToString() + "Obj:");
        int cnt = 0;
        for (Variable var : variables) {
            builder.append(var.toCplexString());
            if (cnt != variables.size() - 1) {
                builder.append(" + ");
            }
            ++cnt;
        }
        builder.append("\nSubject To\n");
        return builder.toString();
    }

    public enum OptimizationObjective {
        MIN, MAX,
    }

}
