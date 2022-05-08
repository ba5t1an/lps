package com.lps.shiftopt.model;

import com.lps.common.model.OptimizationProblem;

public interface ProblemGenerator {

    void freeze();

    OptimizationProblem generate();

}
