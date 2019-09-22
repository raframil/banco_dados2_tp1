package controller;

import java.util.Date;
import model.Employees;
import java.util.Iterator;
import controller.GeneralDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author greca
 */

public class DAOExtends extends GeneralDAO{
    
    public List<Employees> funcionariosDataDeContrato(Date dataContrato){
               
        //retorna todos os funcionários que foram contratados até uma data especificada
        GeneralDAO grl_dao = new GeneralDAO();
        List<Employees> emp = new ArrayList();
        Session sessao = grl_dao.getSessao();
                        
        emp = sessao.createQuery(
            "select e " +
            "from Employees e " +
            "where e.hireDate < :dataContrato ")
        .setParameter( "dataContrato", dataContrato).list();
        
//        for (Employees emp1 : emp) {
//            System.out.println(emp1.getEmpNo());
//        }
        
        return emp;
    }
    
    public List<Employees> funcionariosMediaSalarialDept(String numero_dept){
        
        //retorna todos os funcionários que foram contratados até uma data especificada
        GeneralDAO grl_dao = new GeneralDAO();
        List<Employees> emp = new ArrayList();
        Session sessao = grl_dao.getSessao();
                
        emp = sessao.createQuery(
            "select e " +
            "from Employees e , DeptEmp d , Salaries s " +
            "where e.empNo = d.id.empNo and e.empNo = s.id.empNo and d.id.deptNo = :numero_dept and s.salary < " +
            "(select avg(s.salary) from Salaries s)")
            .setParameter("numero_dept", numero_dept).list();
        
//        for (Employees emp1 : emp) {
//            System.out.println(emp1.getEmpNo());
//            System.out.println(emp1.getFirstName());
//        }
        
        return emp;
    }
}