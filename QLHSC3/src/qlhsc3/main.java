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
import org.hibernate.engine.spi.ExecutableList;
import org.hibernate.engine.spi.ExecutableList.Sorter;
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

                
        
        
        //close sessionFactory
        HibernateUtil.close();
    }

    
}
