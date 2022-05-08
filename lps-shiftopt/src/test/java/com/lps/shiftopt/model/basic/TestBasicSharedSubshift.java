package com.lps.shiftopt.model.basic;

import com.lps.shiftopt.model.Employee;
import com.lps.shiftopt.model.Shift;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestBasicSharedSubshift {

    private BasicSharedSubshift subshift;

    private Employee[] getEmployeeList() {
        Employee e0 = new Employee(0, "e", 40.f);
        Employee e1 = new Employee(1, "e", 40.f);
        Employee[] employees = new Employee[]{e0, e1};
        return employees;
    }

    @Before
    public void initialize() {
        DateTime st = new DateTime(2005, 3, 26, 8, 30);
        DateTime et = new DateTime(2005, 3, 26, 16, 30);
        DateTime dt = new DateTime(2005, 3, 26, 0, 0, 0, 0);
        Shift shift = new BasicShift(null, 0, "shift", dt);
        subshift = new BasicSharedSubshift(shift, 0, "subshift", st, et, 4.f, 10.5f, 4, 2);
        subshift.addAvailableEmployees(getEmployeeList());
        subshift.generate();
        System.out.println(subshift.generate().toString());
    }

    @Test
    public void testNumEmployees() {
        assertTrue((subshift.getEmployees().size() == 2));
        assertTrue((subshift.getBinaryVariables().size() == subshift.getEmployees().size() * 3));
    }

    @Test
    public void testExcludedSubshift() {
        Employee e2 = new Employee(2, "e", 40.f);
        List<Integer> excludedSubshifts = new ArrayList<Integer>();
        excludedSubshifts.add(new Integer(0));
        e2.setExcludedSubshifts(excludedSubshifts);
        assertTrue(e2.getExcludedSubshifts().size() == 1);
        subshift.addAvailableEmployee(e2);
        subshift.generate();
        assertTrue((subshift.getEmployees().size() == 2));
        assertTrue((subshift.getBinaryVariables().size() == subshift.getEmployees().size() * 3));
    }

    @Test
    public void testExcludedSubshift1() {
        Employee e2 = new Employee(2, "e", 40.f);
        List<Integer> excludedSubshifts = new ArrayList<Integer>();
        excludedSubshifts.add(new Integer(1));
        e2.setExcludedSubshifts(excludedSubshifts);
        assertTrue(e2.getExcludedSubshifts().size() == 1);
        subshift.addAvailableEmployee(e2);
        subshift.generate();
        assertTrue((subshift.getEmployees().size() == 3));
        assertTrue((subshift.getBinaryVariables().size() == subshift.getEmployees().size() * 3));
    }

    @Test
    public void testExcludedDate() {
        Employee e2 = new Employee(2, "e", 40.f);
        List<DateTime> excludedDates = new ArrayList<>();
        excludedDates.add(new DateTime(2005, 3, 26, 0, 0, 0, 0));
        e2.setExcludedDates(excludedDates);
        assertTrue(e2.getExcludedDates().size() == 1);
        subshift.addAvailableEmployee(e2);
        subshift.freeze();
        assertTrue((subshift.getEmployees().size() == 2));
        assertTrue((subshift.getBinaryVariables().size() == subshift.getEmployees().size() * 3));
    }

    @Test
    public void testExcludedDate1() {
        Employee e2 = new Employee(2, "e", 40.f);
        List<DateTime> excludedDates = new ArrayList<>();
        excludedDates.add(new DateTime(2005, 3, 27, 0, 0, 0, 0));
        e2.setExcludedDates(excludedDates);
        assertTrue(e2.getExcludedDates().size() == 1);
        subshift.addAvailableEmployee(e2);
        subshift.freeze();
        assertTrue((subshift.getEmployees().size() == 3));
        assertTrue((subshift.getBinaryVariables().size() == subshift.getEmployees().size() * 3));
    }

}
