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
package com.lps.common.xml.task;

public class RequiredRole {

    private int roleID;
    private int count;
    private double requiredPresenceHours;
    private double requiredPresencePercentage;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public double getRequiredPresenceHours() {
        return requiredPresenceHours;
    }

    public void setRequiredPresenceHours(double requiredPresenceHours) {
        this.requiredPresenceHours = requiredPresenceHours;
    }

    public double getRequiredPresencePercentage() {
        return requiredPresencePercentage;
    }

    public void setRequiredPresencePercentage(double requiredPresencePercentage) {
        this.requiredPresencePercentage = requiredPresencePercentage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
