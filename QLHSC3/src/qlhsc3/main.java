/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhsc3;

import DAO.AccountDAO;
import DAO.ConducttypeDAO;
import DAO.EthnicDAO;
import DAO.StudentDAO;
import connection.HibernateUtil;
import java.util.List;
import pojos.Account;
import pojos.Conducttype;
import pojos.Ethnic;
import pojos.Role;
import pojos.Student;

/**
 *
 * @author ThanhTung
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Student> list;
        StudentDAO DAO = new StudentDAO();
           
        list = DAO.findAll();
        for (Student t : list) {
            System.out.println(t.getId() + " - " + t.getName());
        }
        
        
        
        
        //close sessionFactory
        HibernateUtil.close();
    }

}
