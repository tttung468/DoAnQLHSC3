/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhsc3;

import DAO.AccountDAO;
import connection.HibernateUtil;
import java.util.List;
import pojos.Account;
import pojos.Role;

/**
 *
 * @author ThanhTung
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Account> list;
        AccountDAO accountDAO = new AccountDAO();
        Account account1;
        
        account1 = accountDAO.findOne(6);
        account1.setUsername("teacher3");
        account1.setId((long) 0);
        long id = accountDAO.insertOne(account1);
        System.out.println("id = " + id );
//        System.out.println(account1.getId() + " - " + account1.getUsername());
//        System.out.println(account1.getCreateddate());
//        System.out.println(account1.getModifieddate());
        System.out.println("");
        
        list = accountDAO.findAll();
        for (Account account : list) {
            System.out.println(account.getId() + " - " + account.getUsername());
        }
        
        
        
        
        //close sessionFactory
        HibernateUtil.close();
    }

}
