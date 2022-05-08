package com.lps.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Constraint {

    private String name;
    private List<Variable> variables;
    private float rhs;
    private Relation relation;

    public Constraint(String name, List<Variable> variables, Relation relation, float rhs) {
        this.name = name;
        this.variables = variables;
        this.relation = relation;
        this.rhs = rhs;
    }

    public Constraint(String name, Relation relation, float rhs) {
        this.name = name;
        this.variables = new ArrayList<>();
        this.relation = relation;
        this.rhs = rhs;
    }

    public Constraint(String name) {
        this.name = name;
        this.variables = new ArrayList<>();
    }

    public Constraint() {
        this.name = "default_constraint";
        this.variables = new ArrayList<>();
    }

    public void addLhsVariable(Variable variable) {
        this.variables.add(variable);
    }

    public List<Variable> getLhsVariables() {
        return Collections.unmodifiableList(variables);
    }

    public Relation getRelation() {
        return this.relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public float getRhs() {
        return rhs;
    }

    public void setRhs(float rhs) {
        this.rhs = rhs;
    }

    private String relationToString() {
        if (this.relation.equals(Relation.GEQ)) {
            return " >= ";
        } else if (this.relation.equals(Relation.LEQ)) {
            return " <= ";
        } else {
            return " = ";
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.name + " ");
        for (int i = 0; i < variables.size(); ++i) {
            builder.append(variables.get(i).toString());
            if (i < variables.size() - 1) {
                builder.append(" + ");
            }
        }
        builder.append(relationToString());
        builder.append(this.rhs);
        return builder.toString();
    }

    public String toCplexString() {
        StringBuilder builder = new StringBuilder(this.name + " ");
        for (int i = 0; i < variables.size(); ++i) {
            builder.append(variables.get(i).toCplexString());
            if (i < variables.size() - 1) {
                if (variables.get(i + 1).getCoefficient() >= 0.f) {
                    builder.append(" + ");
                } else {
                    builder.append(" ");
                }
            }
        }
        builder.append(relationToString());
        builder.append(this.rhs);
        return builder.toString();
    }

    public enum Relation {
        LEQ, GEQ, EQ
    }
}
