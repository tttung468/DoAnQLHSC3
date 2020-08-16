/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.TeacherAssignmentDAO;
import pojos.TeacherAssignment;

/**
 *
 * @author ThanhTung
 */
public class TeacherAssignmentBUS extends AbstractHibernateBUS<TeacherAssignment>{

    public TeacherAssignmentBUS() {
        setAbstractHiberanateDAO(new TeacherAssignmentDAO());
    }
    
}
