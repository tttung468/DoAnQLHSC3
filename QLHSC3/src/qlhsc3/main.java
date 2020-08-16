/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhsc3;


import DAO.ConductTypeDAO;
import DAO.EthnicDAO;
import DAO.HRStaffDAO;
import DAO.OfficeStaffDAO;
import DAO.RoleDAO;
import DAO.SchoolClassDAO;
import DAO.SchoolYearDAO;
import DAO.ScoreDAO;
import DAO.StudentDAO;
import DAO.SubjectDAO;
import DAO.TeacherAssignmentDAO;
import DAO.TeacherDAO;
import BUS.AccountBUS;
import BUS.StudentOfClassBUS;
import BUS.SubjectBUS;
import connection.HibernateUtil;
import java.util.List;
import pagination.PageRequest;
import pojos.Account;
import pojos.ConductType;
import pojos.Ethnic;
import pojos.HRStaff;
import pojos.OfficeStaff;
import pojos.Role;
import pojos.SchoolClass;
import pojos.SchoolYear;
import pojos.Score;
import pojos.Student;
import pojos.StudentOfClass;
import pojos.Subject;
import pojos.Teacher;
import pojos.TeacherAssignment;
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
        StudentOfClassBUS bus = new StudentOfClassBUS();
        StudentOfClass filterModel;
        Integer page = 1;
	Integer perPage = 6;
	Sorter sorter = new Sorter("studentOfClass.id", "desc");
        String searchKey;       
        
	searchKey = null;
	filterModel = null;
//        searchKey = null;
//        filterModel = bus.findOne(3);        
//        searchKey = "7";
//        filterModel = null;
        
        PageRequest<StudentOfClass> pageRequest = new PageRequest<>(page, perPage, sorter, searchKey, filterModel);
        List<StudentOfClass> list = bus.find(pageRequest);
        System.out.println("\nTest find :");
        for (StudentOfClass pojo : list) {
            System.out.println(pojo.getId() + " - " + pojo.getStudent().getCode());
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
