/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.FormTeacherAssignmentDAO;
import pojos.FormTeacherAssignment;

/**
 *
 * @author ThanhTung
 */
public class FormTeacherAssignmentBUS extends AbstractHibernateBUS<FormTeacherAssignment>{

    public FormTeacherAssignmentBUS() {
        setAbstractHiberanateDAO(new FormTeacherAssignmentDAO());
    }
    
}
