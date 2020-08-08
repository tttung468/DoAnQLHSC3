/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Student;

/**
 *
 * @author ThanhTung
 */
public class StudentDAO extends AbstractHibernateDAO<Student>{
    public StudentDAO(){
        setClazz(Student.class);
    }
}
