package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.Employee;
import java.util.Iterator;
import utils.DAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author greca
 */

public class DAOExtends extends DAO{
    
    public List<Employee> funcionariosDataDeContrato(Date dataContrato) throws SQLException, ClassNotFoundException{
               
        //retorna todos os funcionários que foram contratados até uma data especificada
        String SQL = "select * from employees where hire_date < '"+dataContrato.toString()+"';";
        this.conexao = this.getConexao();
        List<Employee> emp = new ArrayList();
        ResultSet res = this.executarQuery(conexao, SQL);
            int emp_no;
            java.sql.Date birth_date;
            String first_name;
            String last_name;
            char gender;
            java.sql.Date hire_date;
            while (res.next()){
                emp_no = res.getInt(1);
                birth_date = res.getDate(2);
                first_name = res.getString(3);
                last_name = res.getString(4);
                gender = res.getString(5).charAt(0);
                hire_date = res.getDate(6);
                emp.add(new Employee(emp_no, birth_date, first_name, last_name, gender, hire_date));
            }
        
        return emp;
    }
    
    public List<Employee> funcionariosMediaSalarialDept(String numero_dept) throws SQLException, ClassNotFoundException{
        //retorna todos os funcionários que foram contratados até uma data especificada
        String SQL = "select e.emp_no,e.birth_date,e.first_name,e.last_name,e.gender,e.hire_date from Employees e , Dept_Emp d , Salaries s \n" +
        "WHERE e.emp_no = d.emp_no AND e.emp_no = s.emp_no AND d.dept_no = '"+numero_dept+"' AND s.salary < (SELECT avg(s.salary) FROM Salaries s)";
        this.conexao = this.getConexao();
        List<Employee> emp = new ArrayList();
        ResultSet res = this.executarQuery(conexao, SQL);
            int emp_no;
            java.sql.Date birth_date;
            String first_name;
            String last_name;
            char gender;
            java.sql.Date hire_date;
            while (res.next()){
                emp_no = res.getInt(1);
                birth_date = res.getDate(2);
                first_name = res.getString(3);
                last_name = res.getString(4);
                gender = res.getString(5).charAt(0);
                hire_date = res.getDate(6);
                emp.add(new Employee(emp_no, birth_date, first_name, last_name, gender, hire_date));
            }
        
        return emp;
    }
}