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

// Create Employee
public class EmployeeController {

    public void createEmployee(int empNo, ArrayList<ArrayList<String>> inputsDigitados) throws ParseException {
        GeneralDAO grl_emp = new GeneralDAO();
        try {
            Employees employee = new Employees();
            Titles titl = new Titles();
            TitlesId titl_id = new TitlesId();
            Salaries sal = new Salaries();
            SalariesId sal_id = new SalariesId();
            DeptEmp deptEmp = new DeptEmp();
            //Departments dept = new Departments();
            DeptEmpId deptEmp_id = new DeptEmpId();

            for (int i = 0; i < inputsDigitados.size(); i++) {
                for (int j = 0; j < inputsDigitados.get(i).size() - 1; j++) {
                    //System.out.print(inputsDigitados.get(i).get(j) + " ");   
                    String nomeInput = inputsDigitados.get(i).get(j).toString();
                    String valorInput = inputsDigitados.get(i).get(j + 1).toString();

                    // Dados Referentes a tabela de Employees
                    if (nomeInput.equals("birth_date")) {
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        employee.setBirthDate(date1);
                    }

                    if (nomeInput.equals("first_name")) {
                        employee.setFirstName(valorInput);
                    }

                    if (nomeInput.equals("last_name")) {
                        employee.setLastName(valorInput);
                    }

                    if (nomeInput.equals("sex")) {
                        employee.setGender(valorInput);
                    }

                    if (nomeInput.equals("hire_date")) {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        employee.setHireDate(date);
                    }
                    // Fim Dados Referentes a tabela de Employees

                    // Dados Referentes a tabela de Titles
                    if (nomeInput.equals("title")) {
                        titl_id.setTitle(valorInput);
                    }

                    titl_id.setEmpNo(empNo);

                    if (nomeInput.equals("titleSalaryFromDateField")) {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        titl_id.setFromDate(date);
                    }
                    if (nomeInput.equals("titleSalaryToDateField")) {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        titl.setToDate(date);
                    }
                    // Fim tabela titles

                    // Começo tabela salaries 
                    sal.setId(sal_id);
                    if (nomeInput.equals("salaryField")) {
                        sal.setSalary(Integer.parseInt(valorInput));
                    }
                    if (nomeInput.equals("titleSalaryToDateField")) {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        sal.setToDate(date);
                    }
                    sal_id.setEmpNo(empNo);
                    if (nomeInput.equals("titleSalaryFromDateField")) {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        sal_id.setFromDate(date);
                    }
                    // Fim tabela salaries

                    // Tabela Departamento-Empregado
                    
                    if (nomeInput.equals("departmentFromDateField")) {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        deptEmp.setFromDate(date);
                    }
                    if (nomeInput.equals("departmentToDateField")) {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        deptEmp.setToDate(date);
                    }
                    if (nomeInput.equals("dept_no")) {
                        deptEmp_id.setDeptNo(valorInput);
                    }
                    // Fim Tabela Departamento-Empregado
                }
            }
            
            
            
            sal.setId(sal_id);

            deptEmp_id.setEmpNo(empNo); 
            deptEmp.setId(deptEmp_id);

            employee.setEmpNo(empNo);

            /*Set<Salaries> sal_set = new HashSet<>();
            sal_set.add(sal);

            Set<Titles> titl_set = new HashSet<>();
            titl_set.add(titl);

            Set<DeptEmp> deptemp_set = new HashSet<>();
            deptemp_set.add(deptEmp);*/

            Session session = grl_emp.getSessao();

            session.save(employee);
            
            // Relações com FK
            titl.setEmployees(employee);
            sal.setEmployees(employee);
            deptEmp.setEmployees(employee);
            
            session.save(deptEmp);
            //session.save(sal);
            //session.save(titl);
            
            

            Transaction tr = session.beginTransaction();

            tr.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        grl_emp.fecharSessao();
    }
    
    public void saveDeptEmp(DeptEmp deptEmp) {
        
    }

    public void deleteEmployee(int empNo) {
        GeneralDAO grl_emp = new GeneralDAO();
        try {
            //deletar
            Employees employee = new Employees();

            Session sessao = grl_emp.getSessao();

            grl_emp.carregar(employee, empNo);
            grl_emp.apagar(employee);

            Transaction tr = sessao.beginTransaction();

            tr.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        grl_emp.fecharSessao();
    }

    public void updateEmployee(int empNo, ArrayList<ArrayList<String>> inputsDigitados) throws ParseException {

        GeneralDAO grl_emp = new GeneralDAO();

        try {

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
                for (int j = 0; j < inputsDigitados.get(i).size() - 1; j++) {
                    //System.out.print(inputsDigitados.get(i).get(j) + " ");

                    String nomeInput = inputsDigitados.get(i).get(j).toString();
                    String valorInput = inputsDigitados.get(i).get(j + 1).toString();

                    if (nomeInput.equals("birth_date")) {
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        employee.setBirthDate(date1);
                    }

                    if (nomeInput.equals("first_name")) {
                        employee.setFirstName(valorInput);
                    }

                    if (nomeInput.equals("last_name")) {
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

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        grl_emp.fecharSessao();
    }

    public void readEmployee(int empNo) {

        GeneralDAO grl_emp = new GeneralDAO();

        try {

            //consultar
            Employees employee = new Employees();

            Session sessao = grl_emp.getSessao();

            grl_emp.listEmployee(empNo);

            Transaction tr = sessao.beginTransaction();

            tr.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }

        grl_emp.fecharSessao();
    }

    public ArrayList<Employees> listAllEmployees() {
        GeneralDAO grl_emp = new GeneralDAO();
        Session sessao = grl_emp.getSessao();
        return (ArrayList<Employees>) sessao.createQuery("from Employees").list();
    }
}
