package com.lps.common.solution;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubshiftSolution {

    private int id;
    private DateTime start;
    private DateTime end;
    private List<EmployeeSolution> employeeSolutions;

    public SubshiftSolution(int id, DateTime start, DateTime end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.employeeSolutions = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public DateTime getStart() {
        return this.start;
    }

    public DateTime getEnd() {
        return this.end;
    }

    public void put(int id, String name, float time) {
        this.employeeSolutions.add(new EmployeeSolution(id, name, time));
    }

    public void putOrAppend(int id, String name, float time) {
        for (EmployeeSolution eSolution : this.employeeSolutions) {
            if (eSolution.getId() == id) {
                eSolution.appendRequieredWorkload(time);
                return;
            }
        }
        this.employeeSolutions.add(new EmployeeSolution(id, name, time));
    }

    public List<EmployeeSolution> getEmployeeSolutions() {
        return Collections.unmodifiableList(this.employeeSolutions);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[Subshift = " + this.getId() + ", " + this.getStart().toString() + ", " + this.getEnd().toString() + " -> ");
        for (EmployeeSolution eSolution : employeeSolutions) {
            builder.append(eSolution.toString() + ", ");
        }
        builder.append("]");
        return builder.toString();
    }
}
