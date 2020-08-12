/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.FormTeacherAssignment;

/**
 *
 * @author ThanhTung
 */
public class FormTeacherAssignmentDAO extends AbstractHibernateDAO<FormTeacherAssignment>{
    public FormTeacherAssignmentDAO(){
        setClazz(FormTeacherAssignment.class);
    }
}
