/*************************************************************************
 *
 * CONFIDENTIAL
 * __________________
 *
 *  [2016] Bastian Schoettle & Tim Schoettle
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Bastian Schoettle & Tim Schoettle and their suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Bastian Schoettle & Tim Schoettle
 * and their suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Bastian Schoettle & Tim Schoettle.
 *
 */
package com.lps.common.xml.result;

public class RequiredEmployee {


    /*
     * <requiredEmployee> <emplName></emplName> <emplID></emplID>
     * <presenceHours></presenceHourshttps://marketplace.eclipse.org/content/eclipse-color-theme> <shiftOvertime></shiftOvertime>
     * </requiredEmployee>
     *
     *
     */
    /**
     *
     */
    private String emplName;
    /**
     *
     */
    private int emplID;
    /**
     *
     */
    private double singleHours;

    /**
     *
     */
    private double totalHours;
    /**
     *
     */
    private double multiHours;
    /**
     *
     */
    private double breakTime;

    /**
     * @return
     */
    public String getEmplName() {
        return emplName;
    }

    /**
     * @param emplName
     */
    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }

    /**
     * @return
     */
    public int getEmplID() {
        return emplID;
    }

    /**
     * @param emplID
     */
    public void setEmplID(int emplID) {
        this.emplID = emplID;
    }

    public double getSingleHours() {
        return singleHours;
    }

    public void setSingleHours(double singleHours) {
        this.singleHours = singleHours;
    }

    public double getMultiHours() {
        return multiHours;
    }

    public void setMultiHours(double multiHours) {
        this.multiHours = multiHours;
    }

    public void addMultiHours(double multiHours) {
        this.multiHours += multiHours;
        this.totalHours += multiHours;
    }

    public void addSingleHours(double singleHours) {
        this.singleHours += singleHours;
        this.totalHours += singleHours;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public double getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(double breakTime) {
        this.breakTime = breakTime;
    }

}
