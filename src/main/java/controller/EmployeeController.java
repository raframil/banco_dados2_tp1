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
            Departments dept = new Departments();
            DeptEmpId deptEmp_id = new DeptEmpId();

            for (int i = 0; i < inputsDigitados.size(); i++) {
                for (int j = 0; j < inputsDigitados.get(i).size() - 1; j++) {
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
                    if (nomeInput.equals("salaryField")) {
                        sal.setSalary(Integer.parseInt(valorInput));
                    }
                    if (nomeInput.equals("titleSalaryToDateField")) {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(valorInput);
                        sal.setToDate(date);
                    }

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

            sal_id.setEmpNo(empNo);
            sal.setId(sal_id);

            deptEmp_id.setEmpNo(empNo);
            deptEmp.setId(deptEmp_id);

            titl_id.setEmpNo(empNo);
            titl.setId(titl_id);

            employee.setEmpNo(empNo);

            Session session = grl_emp.getSessao();

            session.save(employee);

            // Relações com FK
            titl.setEmployees(employee);
            sal.setEmployees(employee);
            deptEmp.setEmployees(employee);

            session.save(deptEmp);
            session.save(sal);
            session.save(titl);

            Transaction tr = session.beginTransaction();

            tr.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        grl_emp.fecharSessao();
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
            Employees employee = new Employees();

            Session session = grl_emp.getSessao();

            grl_emp.carregar(employee, empNo);

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
                }
            }
            employee.setEmpNo(empNo);
            //session.update(employee);
            grl_emp.atualizar(employee);
            
            Transaction tr = session.beginTransaction();
            tr.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        grl_emp.fecharSessao();
    }

    public void updateSalary(int empNo, int salary, Date fromDate, Date toDate) {
        GeneralDAO grl_emp = new GeneralDAO();
        try {
            Session session = grl_emp.getSessao();

            SalariesId sal_id = new SalariesId();
            Salaries sal = new Salaries();
            
            //grl_emp.carregar(sal, sal_id);
            sal_id.setEmpNo(empNo);
            sal_id.setFromDate(fromDate);

            sal.setSalary(salary);
            sal.setToDate(toDate);
            sal.setId(sal_id);
            
            //grl_emp.atualizar(sal);
            
            String hqlUpdate = "update Salaries set salary = :salary where emp_no = :empNo and from_date = :fromDate";
            int updatedEntities = session.createQuery(hqlUpdate)
                    .setInteger("salary", salary)
                    .setInteger("empNo", empNo)
                    .setParameter("fromDate", fromDate)
                    .executeUpdate();
            
            Transaction tx = session.beginTransaction();           
            
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        grl_emp.fecharSessao();
    }

    public ArrayList<Employees> readEmployee(int empNo) {
        GeneralDAO grl_emp = new GeneralDAO();
        try {
            //consultar
            Session sessao = grl_emp.getSessao();
            return grl_emp.listEmployee(empNo);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        grl_emp.fecharSessao();
        return null;
    }

    public ArrayList<Salaries> readSalaries(int empNo) {
        GeneralDAO grl_emp = new GeneralDAO();
        try {
            //consultar
            Session sessao = grl_emp.getSessao();
            return grl_emp.listSalariesFromEmployee(empNo);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        grl_emp.fecharSessao();
        return null;
    }

    public ArrayList<Employees> listAllEmployees() {
        GeneralDAO grl_emp = new GeneralDAO();
        Session sessao = grl_emp.getSessao();
        return (ArrayList<Employees>) sessao.createQuery("from Employees").list();
    }
}
