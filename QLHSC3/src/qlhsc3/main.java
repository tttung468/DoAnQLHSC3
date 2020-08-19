/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhsc3;

import DAO.FormTeacherAssignmentDAO;
import connection.HibernateUtil;
import java.util.List;
import pagination.PageRequest;
import pojos.FormTeacherAssignment;
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
        FormTeacherAssignmentDAO bus = new FormTeacherAssignmentDAO();
        FormTeacherAssignment filterModel;
        Integer page = 1;
	Integer perPage = 6;
	Sorter sorter = new Sorter("formTeacherAssignment.id", "desc");
        String searchKey;       
        
	searchKey = null;
	filterModel = null;
//        searchKey = null;
//        filterModel = bus.findOne(2);        
//        searchKey = "5";
//        filterModel = null;
        
        PageRequest<FormTeacherAssignment> pageRequest = new PageRequest<>(page, perPage, sorter, searchKey, filterModel);
        List<FormTeacherAssignment> list = bus.find(pageRequest);
        System.out.println("\nTest find :");
        for (FormTeacherAssignment pojo : list) {
            System.out.println(pojo.getId() + " - " + pojo.getCapacity());
        }
        
        System.out.println("\nTest count :");
        System.out.println(bus.count(pageRequest));
        
        
        //close sessionFactory
        HibernateUtil.close();
    }

    
}
