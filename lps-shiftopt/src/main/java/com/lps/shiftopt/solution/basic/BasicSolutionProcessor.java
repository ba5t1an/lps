package com.lps.shiftopt.solution.basic;

import com.lps.common.solution.ShiftModelSolution;
import com.lps.common.solution.ShiftSolution;
import com.lps.common.solution.SubshiftSolution;
import com.lps.shiftopt.model.Shift;
import com.lps.shiftopt.model.ShiftModel;
import com.lps.shiftopt.model.Subshift;
import com.lps.shiftopt.model.basic.BasicVariableContainer;

import java.util.Map;

public class BasicSolutionProcessor {

    private Map<String, Double> variableSolutions;
    private ShiftModel model;
    private ShiftModelSolution modelSolution;

    public BasicSolutionProcessor(Map<String, Double> solution, ShiftModel model) {
        this.variableSolutions = solution;
        this.model = model;
        this.modelSolution = new ShiftModelSolution();
    }

    public void createOrAppendsSolution(BasicVariableContainer container, Double workload) {
        if (container.getType() == BasicVariableContainer.VariableType.EMPLOYEE_SUBSHIFT || container.getType() == BasicVariableContainer.VariableType.EMPLOYEE_SUBSHIFT_SHARED || container.getType() == BasicVariableContainer.VariableType.EMPLOYEE_SUBSHIFT_SOLE) {
            Shift shift = container.getShift();
            Subshift subshift = container.getSubshift();
            ShiftSolution shiftSolution = modelSolution.getShiftSolutionByIdAndDate(shift.getId(), shift.getDate());
            if (shiftSolution == null) {
                shiftSolution = new ShiftSolution(shift.getId(), shift.getDate());
                SubshiftSolution subSolution = new SubshiftSolution(subshift.getId(), subshift.getEnd(), subshift.getStart());
                shiftSolution.addSubshift(subSolution);
                modelSolution.addShiftSolution(shiftSolution);
            } else {
                SubshiftSolution subSolution = shiftSolution.getSubshiftSolutionByIdAndTimes(subshift.getId(), subshift.getEnd(), subshift.getStart());
                if (subSolution == null) {
                    subSolution = new SubshiftSolution(subshift.getId(), subshift.getEnd(), subshift.getStart());
                    subSolution.put(container.getEmployee().getId(), container.getEmployee().getName(), workload.floatValue());
                    shiftSolution.addSubshift(subSolution);
                } else {
                    subSolution.putOrAppend(container.getEmployee().getId(), container.getEmployee().getName(), workload.floatValue());
                }
            }
        }
    }

    public void processSolution() {
        Map<String, BasicVariableContainer> variableContainerMap = this.model.getVariableMap();
        for (Map.Entry<String, Double> entry : variableSolutions.entrySet()) {
            BasicVariableContainer container = variableContainerMap.get(entry.getKey());
            if (container != null) {
                createOrAppendsSolution(container, entry.getValue());
            }
        }
    }

    public ShiftModelSolution getSolution() {
        return this.modelSolution;
    }
}
