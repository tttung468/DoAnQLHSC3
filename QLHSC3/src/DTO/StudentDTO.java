/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.StudentDAO;
import pojos.Student;

/**
 *
 * @author ThanhTung
 */
public class StudentDTO extends AbstractHibernateDTO<Student>{

    public StudentDTO() {
        setAbstractHiberanateDAO(new StudentDAO());
    }
    
}
