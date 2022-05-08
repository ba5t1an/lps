package com.lps.common.solution;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class ShiftModelSolution {

    private List<ShiftSolution> shiftSolutions;

    public ShiftModelSolution() {
        this.shiftSolutions = new ArrayList<>();
    }

    public ShiftSolution getShiftSolutionByIdAndDate(int id, DateTime date) {
        for (ShiftSolution shift : this.shiftSolutions) {
            if (shift.getId() == id) {
                if (shift.getDate().withTimeAtStartOfDay().equals(date.withTimeAtStartOfDay())) {
                    return shift;
                }
            }
        }
        return null;
    }

    public void addShiftSolution(ShiftSolution solution) {
        this.shiftSolutions.add(solution);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        System.out.println(shiftSolutions.size());
        for (ShiftSolution sSolution : this.shiftSolutions) {
            builder.append(sSolution.toString());
        }
        return builder.toString();
    }
}
