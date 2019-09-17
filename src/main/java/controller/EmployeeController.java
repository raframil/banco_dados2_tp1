package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import model.*;
import java.text.SimpleDateFormat; 
import org.hibernate.*;

public class EmployeeController {
    
    public void createEmployee(int empNo, Date birthDate, String firstName, String lastName, String gender, Date hireDate, String title, Date from_date, Date to_date, int salary, String dept_no) {
        
        GeneralDAO grl_emp = new GeneralDAO();
        
        try{
            
            //inserir
            
            Employees employee = new Employees();
            Titles titl = new Titles();
            TitlesId titl_id = new TitlesId();
            Salaries sal = new Salaries();
            SalariesId sal_id = new SalariesId();
            DeptEmp deptEmp = new DeptEmp();
            Departments dept = new Departments();
            DeptEmpId deptEmp_id = new DeptEmpId();
            DeptManager deptMan = new DeptManager();
            DeptManagerId deptMan_id = new DeptManagerId();
                        
            Session sessao = grl_emp.getSessao();
                       
            titl.setEmployees(employee);
            titl_id.setEmpNo(empNo);
            titl_id.setFromDate(from_date);
            titl_id.setTitle(title);
            titl.setId(titl_id);
            titl.setToDate(to_date);
            
            sal.setEmployees(employee);
            sal.setId(sal_id);
            sal.setSalary(salary);
            sal.setToDate(to_date);
            sal_id.setEmpNo(empNo);
            sal_id.setFromDate(from_date);
            
            deptEmp.setDepartments(dept);
            deptEmp.setEmployees(employee);
            deptEmp.setFromDate(from_date);
            deptEmp.setId(deptEmp_id);
            deptEmp.setToDate(to_date);
            deptEmp_id.setEmpNo(empNo);
            deptEmp_id.setDeptNo(dept_no);
            deptMan.setDepartments(dept);
            deptMan.setEmployees(employee);
            deptMan.setFromDate(from_date);
            deptMan.setId(deptMan_id);
            deptMan.setToDate(to_date);
            deptMan_id.setDeptNo(dept_no);
            deptMan_id.setEmpNo(empNo);
            
            employee.setEmpNo(empNo);
            employee.setBirthDate(birthDate);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setGender(gender);
            employee.setHireDate(hireDate);
            
            Set<Salaries> sal_set = new HashSet<>();
            sal_set.add(sal);
            
            Set<Titles> titl_set = new HashSet<>();
            titl_set.add(titl);
            
            Set<DeptEmp> deptemp_set = new HashSet<>();
            deptemp_set.add(deptEmp);
            
            Set<DeptManager> deptman_set = new HashSet<>();
            deptman_set.add(deptMan);
            
            employee.setDeptEmps(deptemp_set);
            employee.setDeptManagers(deptman_set);
            employee.setSalarieses(sal_set);
            employee.setTitleses(titl_set);
            
            Transaction tr = sessao.beginTransaction();

            tr.commit();
            
        }catch(HibernateException e) {
            e.printStackTrace();
        }
        
        grl_emp.fecharSessao();
    }
    
    public void deleteEmployee(int empNo){
        
        GeneralDAO grl_emp = new GeneralDAO();
        
        try{
            
            //deletar
            
            Employees employee = new Employees();
          
            Session sessao = grl_emp.getSessao();

            grl_emp.carregar(employee, empNo);
            grl_emp.apagar(employee);

            Transaction tr = sessao.beginTransaction();

            tr.commit();
            
        }catch(HibernateException e) {
            e.printStackTrace();
        }
        
        grl_emp.fecharSessao();
    }
    
    public void updateEmployee(int empNo, ArrayList<ArrayList<String>> inputsDigitados) throws ParseException {
        
        GeneralDAO grl_emp = new GeneralDAO();
        
        try{
            
            //atualizar
            
            Employees employee = new Employees();
            Titles titl = new Titles();
            TitlesId titl_id = new TitlesId();
            Salaries sal = new Salaries();
            SalariesId sal_id = new SalariesId();
            DeptEmp deptEmp = new DeptEmp();
            Departments dept = new Departments();
            DeptEmpId deptEmp_id = new DeptEmpId();
            DeptManager deptMan = new DeptManager();
            DeptManagerId deptMan_id = new DeptManagerId();
          
            Session sessao = grl_emp.getSessao();
            
            grl_emp.carregar(employee, empNo);
            
            for (int i = 0; i < inputsDigitados.size(); i++) { 
                for (int j = 0; j < inputsDigitados.get(i).size()-1; j++) { 
                    //System.out.print(inputsDigitados.get(i).get(j) + " ");
                    
                    String nomeInput = inputsDigitados.get(i).get(j).toString();
                    String valorInput = inputsDigitados.get(i).get(j+1).toString();
                    
                    if(nomeInput.equals("birth_date")){
                        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(valorInput); 
                        employee.setBirthDate(date1);
                    }

                    if(nomeInput.equals("first_name")){
                        employee.setFirstName(valorInput);
                    }

                    if(nomeInput.equals("last_name")){
                        employee.setLastName(valorInput);
                    }
                } 
                //System.out.println(); 
            } 
            
            employee.setDeptEmps(null);
            employee.setDeptManagers(null);
            employee.setEmpNo(empNo);
            employee.setGender(null);
            employee.setHireDate(null);
            employee.setSalarieses(null);
            employee.setTitleses(null);
            
        }catch(HibernateException e) {
            e.printStackTrace();
        }
        
        grl_emp.fecharSessao();
    }
    
    public void readEmployee(int empNo) {
        
        GeneralDAO grl_emp = new GeneralDAO();
        
        try{
            
            //consultar
            
            Employees employee = new Employees();
          
            Session sessao = grl_emp.getSessao();

            grl_emp.listaEmployee(empNo);

            Transaction tr = sessao.beginTransaction();

            tr.commit();
            
        }catch(HibernateException e) {
            e.printStackTrace();
        }
        
        grl_emp.fecharSessao();
    }
}
