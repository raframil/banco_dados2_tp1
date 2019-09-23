/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author tassi
 */
public class Dept_emp {
    private int emp_no;
    private String dept_no;
    private Date from_date;
    private Date to_date;

    public Dept_emp() {
    }

    public Dept_emp(int emp_no, String dept_no, Date from_date, Date to_date) {
        this.emp_no = emp_no;
        this.dept_no = dept_no;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }
}
