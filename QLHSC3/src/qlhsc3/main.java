/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhsc3;

import BUS.ConductBUS;
import connection.HibernateUtil;
import java.util.List;
import pagination.PageRequest;
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
        ConductBUS bus = new ConductBUS();
        Conduct filterModel;
        Integer page = 1;
	Integer perPage = 6;
	Sorter sorter = new Sorter("conduct.id", "desc");
        String searchKey;       
        
	searchKey = null;
	filterModel = null;
//        searchKey = null;
//        filterModel = bus.findOne(3);        
//        searchKey = "8";
//        filterModel = null;
        
        PageRequest<Conduct> pageRequest = new PageRequest<>(page, perPage, sorter, searchKey, filterModel);
        List<Conduct> list = bus.find(pageRequest);
        System.out.println("\nTest find :");
        for (Conduct pojo : list) {
            System.out.println(pojo.getId() + " - " + pojo.getConductType().getCode());
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
