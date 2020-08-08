/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Semester;

/**
 *
 * @author ThanhTung
 */
public class SemesterDAO extends AbstractHibernateDAO<Semester>{
    public SemesterDAO(){
        setClazz(Semester.class);
    }
}
