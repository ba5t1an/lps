package com.lps.shiftopt.model;

import com.lps.common.model.Constraint;
import com.lps.common.model.OptimizationProblem;
import com.lps.common.model.Variable;
import com.lps.shiftopt.model.basic.BasicVariableContainer;

import java.util.*;

public abstract class AbstractResourceManager implements VariableProducer, ProblemGenerator {

    protected List<Employee> employees;
    protected Map<Employee, List<Variable>> localEmployeeVariables;
    protected Map<Employee, Variable> globalEmployeeVariables;
    protected Map<String, BasicVariableContainer> variableMap;
    protected List<AbstractResourceManager> children;
    protected int id;
    protected String name;
    protected String systemId;
    private List<Variable> binaryVariables;
    private int constraintCounter;

    public AbstractResourceManager(int id, String name) {
        this.employees = new ArrayList<>();
        this.binaryVariables = new ArrayList<>();
        this.localEmployeeVariables = new HashMap<>();
        this.globalEmployeeVariables = new HashMap<>();
        this.constraintCounter = 0;
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
    }

    public AbstractResourceManager(Map<String, BasicVariableContainer> variableMap, int id, String name) {
        this.employees = new ArrayList<>();
        this.binaryVariables = new ArrayList<>();
        this.localEmployeeVariables = new HashMap<>();
        this.globalEmployeeVariables = new HashMap<>();
        this.constraintCounter = 0;
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
        this.variableMap = variableMap;
    }

    public int getId() {
        return this.id;
    }

    public Employee getEmployeeById(int id) {
        for (Employee e : this.employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public void reset() {
        this.employees.clear();
        this.binaryVariables.clear();
        this.localEmployeeVariables.clear();
        this.globalEmployeeVariables.clear();
    }

    public Map<String, BasicVariableContainer> getVariableMap() {
        return this.variableMap;
    }

    public String getConstraintPrefix() {
        return "c" + this.systemId + (++this.constraintCounter) + ": ";
    }


    public List<OptimizationProblem> createChildProblems() {
        if (this.employees != null) {
            List<OptimizationProblem> subproblems = new ArrayList<>();
            for (AbstractResourceManager child : this.children) {
                binaryVariables.addAll(child.getBinaryVariables());
                child.addAvailableEmployees(this.employees);
                subproblems.add(child.generate());
            }
            return subproblems;
        }
        return null;
    }

    public List<Constraint> createEmployeeConstraints() {
        List<Constraint> constraints = new ArrayList<>();
        for (Employee employee : this.employees) {
            Constraint constraint = new Constraint(this.getConstraintPrefix());
            for (AbstractResourceManager child : this.children) {

                Variable variable = child.getGlobalEmployeeVariable(employee);
                if (variable != null) {
                    constraint.addLhsVariable(variable);
                }
            }
            Variable variable = createEmployeeVariable(employee, -1.f);
            constraint.addLhsVariable(variable);
            constraint.setRelation(Constraint.Relation.EQ);
            constraint.setRhs(0.f);
            constraints.add(constraint);
        }
        return constraints;
    }


    public void addChildren(AbstractResourceManager... children) {
        for (AbstractResourceManager resourceManager : this.children) {
            this.children.add(resourceManager);
        }
    }

    public void addChild(AbstractResourceManager child) {
        this.children.add(child);
    }

    public List<AbstractResourceManager> getChild() {
        return Collections.unmodifiableList(this.children);
    }

    public List<Variable> getBinaryVariables() {
        return this.binaryVariables;
    }

    public void addLocalEmployeeVariable(Employee e, Variable variable) {
        List<Variable> variables = localEmployeeVariables.get(e);
        if (variables == null) {
            variables = new ArrayList<>();
            localEmployeeVariables.put(e, variables);
        }
        if (!variables.contains(variable)) {
            variables.add(variable);
        }
    }

    public void addGlobalEmployeeVariable(Employee e, Variable variable) {
        Variable var = this.globalEmployeeVariables.get(e);
        if (var == null) {
            this.globalEmployeeVariables.put(e, variable);
        }
    }

    public Variable getGlobalEmployeeVariable(Employee e) {
        return globalEmployeeVariables.get(e);
    }

    public void addBinaryVariable(Variable variable) {
        if (!this.binaryVariables.contains(variable)) {
            this.binaryVariables.add(variable);
        }
    }

    public void addEmployee(Employee... employees) {
        for (Employee employee : employees) {
            this.employees.add(employee);
        }
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public abstract void addAvailableEmployees(Employee... employees);

    public abstract void addAvailableEmployees(List<Employee> employees);

    public abstract void addAvailableEmployee(Employee employee);

    public abstract Variable createEmployeeVariable(Employee employee, float coefficient);


}
