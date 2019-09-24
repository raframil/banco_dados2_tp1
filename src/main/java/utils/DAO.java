/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tassi
 */
public class DAO {

    Connection conexao = null;
    static final String URL = "jdbc:mysql://localhost:3306/employees?useTimezone=true&serverTimezone=UTC";
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConexao() throws SQLException, ClassNotFoundException {
        Class.forName(this.DRIVER);
        conexao = DriverManager.getConnection(this.URL, "bd2", "123456");
        return conexao;
    }

    public ResultSet executarQuery(Connection conexao, String SQL) throws SQLException {
        PreparedStatement pstm = conexao.prepareStatement(SQL);
        return pstm.executeQuery();
    }
    
    public void executarUpdate(Connection conexao, String SQL) throws SQLException {
        PreparedStatement pstm = conexao.prepareStatement(SQL);
        pstm.executeUpdate();
    }
    
    public void executarDelete(Connection conexao, String SQL) throws SQLException{
        PreparedStatement pstm = conexao.prepareStatement(SQL);
        pstm.execute(SQL);
    }
    
    public void executarBatch(Connection conexao, String[] SQLs) throws SQLException{
        Statement stm = conexao.createStatement();
        for (String s: SQLs){
            stm.addBatch(s);
        }
        stm.executeBatch();
        fechaStm(stm);
    }
    
    public void fechaStm(Statement Fstm) throws SQLException{
        Fstm.close();
    }
}

