package com.lps.common.model;

public class NullObjective extends Objective {

    @Override
    public String toString() {
        return "None: - \n";
    }

    public String toCplexString() {
        return "";
    }
}
