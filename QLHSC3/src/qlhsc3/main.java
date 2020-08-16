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
import DTO.AccountDTO;
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
//        SubjectDAO dao = new SubjectDAO();
//        Subject filterModel;
//        Integer page = 1;
//	Integer perPage = 6;
//	Sorter sorter = new Sorter("subject.id", "desc");
//        String searchKey;       
//        
//	searchKey = null;
//	filterModel = null;
////        searchKey = null;
////        filterModel = dao.findOne(2);        
////        searchKey = "english";
////        filterModel = null;
//        
//        PageRequest<Subject> pageRequest = new PageRequest<>(page, perPage, sorter, searchKey, filterModel);
//        List<Subject> list = dao.find(pageRequest);
//        System.out.println("\nTest find :");
//        for (Subject pojo : list) {
//            System.out.println(pojo.getId() + " - " + pojo.getCode());
//        }
//        
//        System.out.println("\nTest count :");
//        System.out.println(dao.count(pageRequest));
        
//        AccountDTO dto = new AccountDTO();
//        for (Account a : dto.findAll()) {
//            System.out.println(a.getId() + "  " + a.getUsername());
//        }
        HRStaff entity = (new HRStaffDAO()).findByCode("hr-001");
        if(entity != null)
            System.out.println(entity.getId() + " -- " + entity.getName());
        
        //close sessionFactory
        HibernateUtil.close();
    }

    
}
