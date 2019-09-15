package model;

//package controller;
//
//import java.io.Serializable;
//import org.hibernate.*;
//import Hibernate.HibernateUtil;
//import java.util.ArrayList;
//
///**
// *
// * @author greca
// */
//public class GeneralDAO {
//
//    Session sessao;
//
//    public GeneralDAO() {
//        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
//        
//    }
//    
//    public Session getSessao() {
//        return sessao;
//    }
//
//    public void setSessao(Session sessao) {
//        this.sessao = sessao;
//    }
//
//
//    public void salvar(Object entidade) {
//        sessao.save(entidade);
//    }
//
//    public Object carregar(Object entidade, Serializable id) {
//        sessao.load(entidade, id);
//        return entidade;
//    }
//
//    public void apagar(Object entidade) {
//        sessao.delete(entidade);
//    }
//
//    public void atualizar(Object entidade) {
//        sessao.update(entidade);
//    }
//
//    public void fecharSessao() {
//        sessao.close();
//    }
//
//    public ArrayList<Object> listaTodos(String tabela) {
//        return (ArrayList<Object>) sessao.createQuery("from "+tabela).list();
//    }
//}