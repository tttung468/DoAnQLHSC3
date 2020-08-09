/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.TeacherAssignment;

/**
 *
 * @author ThanhTung
 */
public class TeacherAssignmentDAO extends AbstractHibernateDAO<TeacherAssignment>{
    public TeacherAssignmentDAO(){
        setClazz(TeacherAssignment.class);
    }
}
