/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Teacher;

/**
 *
 * @author ThanhTung
 */
public class TeacherDAO extends AbstractHibernateDAO<Teacher>{
    public TeacherDAO(){
        setClazz(Teacher.class);
    }
}
