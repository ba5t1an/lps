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

public class EmployeeResult {

    /*
     * <employeeResult> <emplID></emplID> <emplName></emplName>
     * <totalWorkHours></totalWorkHours> <totalOvertime></totalOvertime>
     * </employeeResult>
     */
    private int emplID;
    /**
     *
     */
    private String emplName;
    /**
     *
     */
    private double totalWorkload;
    /**
     *
     */
    private double totalOvertime;

    public int getEmplID() {
        return emplID;
    }

    public void setEmplID(int emplID) {
        this.emplID = emplID;
    }

    public double getTotalWorkload() {
        return totalWorkload;
    }

    public void setTotalWorkload(double totalWorkload) {
        this.totalWorkload = totalWorkload;
    }

    public double getTotalOvertime() {
        return totalOvertime;
    }

    public void setTotalOvertime(double totalOvertime) {
        this.totalOvertime = totalOvertime;
    }

    public String getEmplName() {
        return emplName;
    }

    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }
}
