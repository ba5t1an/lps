package com.lps.common.solution;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class ShiftSolution {

    private int id;
    private DateTime date;
    private List<SubshiftSolution> subshiftSolutionList;

    public ShiftSolution(int id, DateTime date) {
        this.id = id;
        this.date = date;
        this.subshiftSolutionList = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public DateTime getDate() {
        return this.date;
    }

    public void addSubshift(SubshiftSolution sub) {
        this.subshiftSolutionList.add(sub);
    }

    public SubshiftSolution getSubshiftSolutionByIdAndTimes(int id, DateTime start, DateTime end) {
        for (SubshiftSolution subshift : this.subshiftSolutionList) {
            if (subshift.getId() == id) {
                if (subshift.getStart().withTimeAtStartOfDay().equals(start.withTimeAtStartOfDay()) && subshift.getEnd().withTimeAtStartOfDay().equals(end.withTimeAtStartOfDay())) {
                    return subshift;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{Shift = " + this.id + ", " + this.getDate() + " -> ");
        for (SubshiftSolution sSolution : this.subshiftSolutionList) {
            builder.append(sSolution.toString() + ", ");
        }
        builder.append("}\n");
        return builder.toString();
    }
}
