/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Subject;

/**
 *
 * @author ThanhTung
 */
public class SubjectDAO extends AbstractHibernateDAO<Subject>{
    public SubjectDAO(){
        setClazz(Subject.class);
    }
}
