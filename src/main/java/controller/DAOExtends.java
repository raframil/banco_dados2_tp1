package controller;

import java.util.Date;
import model.Employees;
import java.util.Iterator;
import controller.GeneralDAO;
import org.hibernate.Session;

/**
 *
 * @author greca
 */

public class DAOExtends extends GeneralDAO{
    
//    public Livro getLivroByIsbn(String isbn) {
//        Iterator i = sessao.createQuery("from Livro where isbn='"+isbn+"'").list().iterator();
//        Livro l = null;
//        if(i.hasNext()){
//            l = (Livro) i.next();
//        }
//        return l;
//    }
    
    public Employees funcionariosDataDeContrato(Date dataContrato){
        
        //retorna todos os funcionários que foram contratados até uma data especificada
        GeneralDAO grl_dao = new GeneralDAO();
        
        Session sessao = grl_dao.getSessao();
        
        Iterator i = sessao.createQuery(
            "select ph " +
            "from Employees e " +
            "join e.DeptEmp d " +
            "join d.Salaries s " +
            "join s.Titles t " +
            "where e.hireDate < :dataContrato ")
        .setParameter( "dataContrato", dataContrato).list().iterator();
                
        Employees e = null;
        
        if(i.hasNext()){
            e = (Employees) i.next();
        }
        
        return e;
    }
    
    public Employees funcionariosMediaSalarialDept(String numero_dept){
        
        //retorna todos os funcionários que foram contratados até uma data especificada
        GeneralDAO grl_dao = new GeneralDAO();
        Session sessao = grl_dao.getSessao();
//        Iterator i = sessao.createQuery("from Employees e join DeptEmp d join d.Salaries s where d.deptNo = :numero_dept "
//                + "and s.salary < (SELECT AVG(s.salary) from Salaries s where d.deptNo = :numero_dept")
                
        Iterator i = sessao.createQuery(
            "select ph " +
            "from Employees e " +
            "join e.DeptEmp d " +
            "join d.Salaries s " +
            "where d.deptNo = :numero_dept and s.salary < (SELECT AVG(s.salary) from Salaries s where d.deptNo = :numero_dept)")
        .setParameter( "numero_dept", numero_dept)
        .setParameter( "numero_dept", numero_dept).list().iterator();
        
        Employees e = null;
        
        if(i.hasNext()){
            e = (Employees) i.next();
        }
        
        return e;
    }
}