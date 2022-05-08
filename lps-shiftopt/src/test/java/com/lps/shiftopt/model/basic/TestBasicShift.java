package com.lps.shiftopt.model.basic;

import com.lps.shiftopt.model.Employee;
import com.lps.shiftopt.model.Subshift;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class TestBasicShift {

    private BasicShift shift;

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
        shift = new BasicShift(null, 0, "shift", dt);
        Subshift subshift = new BasicSharedSubshift(shift, 0, "subshift", st, et, 4.f, 10.5f, 4, 2);
        shift.addSubshift(subshift);
        shift.addAvailableEmployees(getEmployeeList());
        //System.out.println(shift.generate().toString());
    }

    @Test
    public void testBasicShift() {
        DateTime st = new DateTime(2005, 3, 26, 16, 30);
        DateTime et = new DateTime(2005, 3, 26, 23, 30);
        Subshift subshift = new BasicSharedSubshift(shift, 1, "subshift", st, et, 4.f, 10.5f, 4, 2);
        shift.addSubshift(subshift);
        System.out.println(shift.generate().toString());
    }


}
