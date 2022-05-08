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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "solverResult")
public class SolverResult {


    private MetaResult metaResult;
    /**
     *
     */
    private List<EmployeeResult> employeeResults;

    /**
     *
     */
    private List<ShiftResult> shiftResults;

    /**
     * @return
     */

    @XmlElementWrapper(name = "employeeResults")
    @XmlElement(name = "employeeResult")
    public List<EmployeeResult> getEmployeeResults() {
        return employeeResults;
    }

    /**
     * @param employeeResults
     */
    public void setEmployeeResults(List<EmployeeResult> employeeResults) {
        this.employeeResults = employeeResults;
    }

    /**
     * @return
     */
    @XmlElement
    public List<ShiftResult> getShiftResults() {
        return shiftResults;
    }

    /**
     * @param shiftResults
     */
    public void setShiftResults(List<ShiftResult> shiftResults) {
        this.shiftResults = shiftResults;
    }

    @XmlElement
    public MetaResult getMetaResult() {
        return metaResult;
    }

    public void setMetaResult(MetaResult metaResult) {
        this.metaResult = metaResult;
    }
}
