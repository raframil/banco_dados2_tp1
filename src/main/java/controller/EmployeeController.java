package controller;

import java.util.Date;
import model.DeptEmp;
import model.Employees;
import org.hibernate.*;

public class EmployeeController {
    
    public void createEmployee(int empNo, Date birthDate, String firstName, String lastName, String gender, Date hireDate) {
        try {
            Employees employee = new Employees();

            employee.setEmpNo(empNo);
            employee.setBirthDate(birthDate);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setGender(gender);
            employee.setHireDate(hireDate);
            
            DeptEmp deptEmp = new DeptEmp();
            //deptEmp.setId(empNo);

            Session session = HibernateUtil.getSessionFactory().openSession();

            session.save(employee);

            Transaction tr = session.beginTransaction();

            tr.commit();
        } catch(HibernateException e) {
            e.printStackTrace();
        }
        HibernateUtil.getSessionFactory().close();
    }
}
