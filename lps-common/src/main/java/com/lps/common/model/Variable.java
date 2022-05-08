package com.lps.common.model;

public class Variable {

    private float coefficient;
    private String name;
    private int hash;
    private float lowerBound;
    private float upperBound;
    private boolean isInteger;

    public Variable(String name, float coefficient) {
        this.name = name;
        this.coefficient = coefficient;
        this.lowerBound = -Float.MAX_VALUE;
        this.upperBound = +Float.MAX_VALUE;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public String getName() {
        return name;
    }

    public void setBinary() {
        this.lowerBound = 0;
        this.upperBound = 1;
        this.isInteger = true;
    }

    public void setNonNegative() {
        this.lowerBound = 0;
    }

    public void setInteger() {
        this.isInteger = true;
    }

    public boolean isInteger() {
        return isInteger;
    }

    public boolean isBinary() {
        return isInteger && this.lowerBound == 0.f && this.upperBound == 1.f;
    }

    public float getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(float lowerBound) {
        this.lowerBound = lowerBound;
    }

    public float getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(float upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public String toString() {
        return coefficient + " * " + name + "[" + lowerBound + "," + upperBound + "](integer=" + isInteger + ")";
    }

    public String toCplexString() {
        return coefficient + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Variable)) {
            return false;
        }
        Variable var = (Variable) o;
        return var.name.equals(name);
    }

    @Override
    public int hashCode() {
        int h = hash;
        if ((h == 0) && (name.length() > 0)) {
            char val[] = name.toCharArray();
            for (int i = 0; i < name.length(); i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
}
