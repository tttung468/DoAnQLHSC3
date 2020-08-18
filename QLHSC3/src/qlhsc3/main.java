/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhsc3;

import BUS.ConductBUS;
import DAO.AccountDAO;
import connection.HibernateUtil;
import java.util.List;
import pagination.PageRequest;
import pojos.Account;
import pojos.Conduct;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AccountDAO bus = new AccountDAO();
        Account filterModel;
        Integer page = 1;
	Integer perPage = 6;
	Sorter sorter = new Sorter("account.id", "desc");
        String searchKey;       
        
//	searchKey = null;
//	filterModel = null;
//        searchKey = null;
//        filterModel = bus.findOne(3);        
        searchKey = "teacher1@gmail.com";
        filterModel = null;
        
        PageRequest<Account> pageRequest = new PageRequest<>(page, perPage, sorter, searchKey, filterModel);
        List<Account> list = bus.find(pageRequest);
        System.out.println("\nTest find :");
        for (Account pojo : list) {
            System.out.println(pojo.getId() + " - " + pojo.getUsername());
        }
        
        System.out.println("\nTest count :");
        System.out.println(bus.count(pageRequest));
        
//        AccountBUS bus = new AccountBUS();
//        for (Account a : bus.findAll()) {
//            System.out.println(a.getId() + "  " + a.getUsername());
//        }
        
        
        //close sessionFactory
        HibernateUtil.close();
    }

    
}
