/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.StudentOfClassDAO;
import pojos.StudentOfClass;

/**
 *
 * @author ThanhTung
 */
public class StudentOfClassDTO extends AbstractHibernateDTO<StudentOfClass>{

    public StudentOfClassDTO() {
        setAbstractHiberanateDAO(new StudentOfClassDAO());
    }
    
}
