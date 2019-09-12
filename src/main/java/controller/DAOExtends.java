//package controller;
//
//import Hibernate.Livro;
//import java.util.Iterator;
//
///**
// *
// * @author greca
// */
//public class LivroDAO extends GeneralDAO{
//    public Livro getLivroByIsbn(String isbn) {
//        Iterator i = sessao.createQuery("from Livro where isbn='"+isbn+"'").list().iterator();
//        Livro l = null;
//        if(i.hasNext()){
//            l = (Livro) i.next();
//        }
//        return l;
//    }
//}