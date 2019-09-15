package controller;

import java.util.ArrayList;
import java.util.List;
import model.Departments;
import org.hibernate.Query;
import org.hibernate.Session;

public class DeptController {
    
    public List<Departments> listAllDepartments() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select deptName from Departments");
        
        List departmentsList = query.list();
        return departmentsList;
    }
}