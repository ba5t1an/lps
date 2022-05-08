/*************************************************************************
 *
 * CONFIDENTIAL
 * __________________
 *
 *  [2016] Bastian Schoettle & Tim Schoettle
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Bastian Schoettle & Tim Schoettle and his suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Bastian Schoettle & Tim Schoettle
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Bastian Schoettle & Tim Schoettle.
 *
 */
package com.lps.common.xml.task;

/**
 * @author schobast
 */
public class AttendenceStrategy {

    public static final String FIXED = "fixed";
    public static final String AVERAGE = "average";
    public static final String MEDIAN = "median";

    private String strategy;
    private double weeklyWorkdays;
    private double fixedHours;

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public double getWeeklyWorkdays() {
        return weeklyWorkdays;
    }

    public void setWeeklyWorkdays(double weeklyWorkdays) {
        this.weeklyWorkdays = weeklyWorkdays;
    }

    public double getFixedHours() {
        return fixedHours;
    }

    public void setFixedHours(double fixedHours) {
        this.fixedHours = fixedHours;
    }

}
