/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Department;
import utils.DAO;
/**
 *
 * @author tassi
 */
public class DepartmentController {
    
    Connection conexao = null;
    DAO varDao = new DAO();
    

    
    public ArrayList<Department> listAllDepartments() throws ClassNotFoundException, SQLException{
        String SQL = "select * from departments;";
        ArrayList <Department> departmentsList = new ArrayList();
            conexao = varDao.getConexao();
            ResultSet res = this.varDao.executarQuery(conexao, SQL);
            String nome;
            String id;
            while (res.next()){
                id = res.getString(1);
                nome = res.getString(2);
                departmentsList.add(new Department(id, nome));
            }
        
        return departmentsList;
    }
}
