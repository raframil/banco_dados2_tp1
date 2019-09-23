/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Department;
import model.Dept_emp;
import model.Employee;
import model.Salaries;
import model.Titles;
import utils.DAO;
import view.StoreEmployeeView;

/**
 *
 * @author tassi
 */
public class EmployeeController {

    Connection conexao = null;
    DAO varDao = new DAO();

    public int getLastEmp() throws ClassNotFoundException, SQLException {
        String SQL = "select max(emp_no) from employees;";
        int result = 0;        
        conexao = varDao.getConexao();        
        ResultSet res = this.varDao.executarQuery(conexao, SQL);
        while (res.next()) {
            result = res.getInt(1);
        }

        return result;
    }

    public void insereEmployee(Employee emp, Dept_emp dpemp, Salaries salarie, Titles title, Department dep) throws ClassNotFoundException, SQLException {
        String SQL1 = "insert into employees values(" + Integer.toString(emp.getEmp_no()) + ",'" + emp.getBirth_date().toString() + "','"
                + emp.getFirst_name() + "','" + emp.getLast_name() + "','" + emp.getGender() + "','" + emp.getHire_date().toString() + "');";

        String SQL2 = "insert into dept_emp values(" + Integer.toString(emp.getEmp_no()) + ",'" + dpemp.getDept_no() + "','"
                + dpemp.getFrom_date().toString() + "','" + dpemp.getTo_date().toString() + "');";

        String SQL3 = "insert into salaries values(" + Integer.toString(emp.getEmp_no()) + "," + Integer.toString(salarie.getSalary()) + ",'"
                + salarie.getFrom_date().toString() + "','" + salarie.getTo_date().toString() + "');";

        String SQL4 = "insert into titles values(" + Integer.toString(emp.getEmp_no()) + ",'" + title.getTitle() + "','"
                + title.getFrom_date().toString() + "','" + title.getTo_date().toString() + "');";
        String SQLs[] = {SQL1,SQL2,SQL3,SQL4};

            conexao = varDao.getConexao();
            varDao.executarBatch(conexao, SQLs);
    }
    
    public Employee buscaEmp(String EmpID) throws SQLException, ClassNotFoundException{
        Employee emp = new Employee();
        String SQL = "select * from employees where emp_no="+EmpID+";";
        conexao = varDao.getConexao();
        ResultSet res = varDao.executarQuery(conexao, SQL);
        while(res.next()){
            emp.setEmp_no(res.getInt(1));
            emp.setBirth_date(res.getDate(2));
            emp.setFirst_name(res.getString(3));
            emp.setLast_name(res.getString(4));
            emp.setGender(res.getString(5).charAt(0));
            emp.setHire_date(res.getDate(6));
        }
        return emp;
    }
}
