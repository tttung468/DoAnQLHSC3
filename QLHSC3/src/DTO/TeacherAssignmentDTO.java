/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.TeacherAssignmentDAO;
import pojos.TeacherAssignment;

/**
 *
 * @author ThanhTung
 */
public class TeacherAssignmentDTO extends AbstractHibernateDTO<TeacherAssignment>{

    public TeacherAssignmentDTO() {
        setAbstractHiberanateDAO(new TeacherAssignmentDAO());
    }
    
}
