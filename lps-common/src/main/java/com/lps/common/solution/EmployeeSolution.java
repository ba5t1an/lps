package com.lps.common.solution;

public class EmployeeSolution {

    private int id;
    private String name;
    private float requieredWorkload;

    public EmployeeSolution(int id, String name, float requieredWorkload) {
        this.id = id;
        this.name = name;
        this.requieredWorkload = requieredWorkload;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public float getRequieredWorkload() {
        return requieredWorkload;
    }

    public void appendRequieredWorkload(float requieredWorkload) {
        this.requieredWorkload += requieredWorkload;
    }

    @Override
    public String toString() {
        return "[Employee = " + this.id + ", " + this.name + ", " + this.requieredWorkload + "]";
    }
}
