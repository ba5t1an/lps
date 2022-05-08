/************************************************************************

 CONFIDENTIAL
 __________________

 [2016] Bastian Schoettle & Tim Schoettle
 All Rights Reserved.

 NOTICE:  All information contained herein is, and remains
 the property of Bastian Schoettle & Tim Schoettle and his suppliers,
 if any.  The intellectual and technical concepts contained
 herein are proprietary to Bastian Schoettle & Tim Schoettle
 and its suppliers and may be covered by U.S. and Foreign Patents,
 patents in process, and are protected by trade secret or copyright law.
 Dissemination of this information or reproduction of this material
 is strictly forbidden unless prior written permission is obtained
 from Bastian Schoettle & Tim Schoettle.

 */
package com.lps.common.xml.result;

/**
 * @author schobast
 */
public class MetaResult {

    /**
     *
     */
    private double totalHours;

    /**
     *
     */
    private double totalCosts;

    /**
     *
     */
    public MetaResult() {
    }

    /**
     * @param totalHours
     * @param totalCosts
     */
    public MetaResult(double totalHours, double totalCosts) {
        super();
        this.totalHours = totalHours;
        this.totalCosts = totalCosts;
    }

    /**
     * @return
     */
    public double getTotalHours() {
        return totalHours;
    }

    /**
     * @param totalHours
     */
    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    /**
     * @return
     */
    public double getTotalCosts() {
        return totalCosts;
    }

    /**
     * @param totalCosts
     */
    public void setTotalCosts(double totalCosts) {
        this.totalCosts = totalCosts;
    }

}
