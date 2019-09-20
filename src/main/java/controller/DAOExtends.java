package controller;

import java.util.Date;
import model.Employees;
import java.util.Iterator;

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
        
        Iterator i = sessao.createQuery("from Employees e JOIN DeptEmp d JOIN Salaries s JOIN Titles t WHERE hireDate < '"+dataContrato+"'").list().iterator();
        Employees e = null;
        
        if(i.hasNext()){
            e = (Employees) i.next();
        }
        
        return e;
    }
    
    public Employees funcionariosMediaSalarialDept(String numero_dept){
        
        //retorna todos os funcionários que foram contratados até uma data especificada
        
        Iterator i = sessao.createQuery("from Employees e JOIN DeptEmp d JOIN Salaries s WHERE d.deptNo = '"+numero_dept+"' "
                + "AND s.salary < (SELECT AVG(s.salary) FROM (Employees e JOIN DeptEmp d) JOIN Salaries s WHERE d.deptNo = '"+numero_dept+"' ").list().iterator();
        Employees e = null;
        
        if(i.hasNext()){
            e = (Employees) i.next();
        }
        
        return e;
    }
}