/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.TeacherDAO;
import pojos.Teacher;

/**
 *
 * @author ThanhTung
 */
public class TeacherDTO extends AbstractHibernateDTO<Teacher>{

    public TeacherDTO() {
        setAbstractHiberanateDAO(new TeacherDAO());
    }
    
}
