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
package com.lps.common.xml.result;

import com.lps.common.xml.task.Employee;
import com.lps.common.xml.task.Subshift;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author schobast
 */
public class KnapsackResult {

    private Map<Subshift, List<Employee>> subshiftResults;

    private List<Employee> employees;

    private Map<DateTime, Map<Subshift, List<Employee>>> dateSubshiftResult;

    /**
     *
     */
    public KnapsackResult() {
        this.subshiftResults = new HashMap<>();
        this.dateSubshiftResult = new HashMap<>();
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee e) {
        this.employees.add(e);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Map<Subshift, List<Employee>> getSubshiftResults() {
        return subshiftResults;
    }

    public void setSubshiftResults(Map<Subshift, List<Employee>> subshiftResults) {
        this.subshiftResults = subshiftResults;
    }

    public void addEmployee(Subshift s, Employee e) {
        if (subshiftResults.get(s) == null) {
            subshiftResults.put(s, new ArrayList<Employee>());
        }
        subshiftResults.get(s).add(e);
    }

    public List<Employee> getAssignedEmployees(Subshift sub) {
        return subshiftResults.get(sub);
    }

    public List<Employee> getAssignedWorkload(DateTime date, Subshift sub) {
        return dateSubshiftResult.get(date).get(sub);
    }

    public void addAssignedWorkload(DateTime date, Subshift sub, Employee e) {
        System.out.println(date.toString() + " - " + sub.getSubshiftName() + " - " + e.getEmplName());
        if (dateSubshiftResult.get(date) == null) {
            List<Employee> list = new ArrayList<>();
            HashMap<Subshift, List<Employee>> map = new HashMap<>();
            map.put(sub, list);
            dateSubshiftResult.put(date, map);
        } else if (dateSubshiftResult.get(date).get(sub) == null) {
            List<Employee> list = new ArrayList<>();
            dateSubshiftResult.get(date).put(sub, list);
        }
        dateSubshiftResult.get(date).get(sub).add(e);
    }

}
