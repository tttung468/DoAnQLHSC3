/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Schoolclass;

/**
 *
 * @author ThanhTung
 */
public class SchoolclassDAO extends AbstractHibernateDAO<Schoolclass>{
    public SchoolclassDAO(){
        setClazz(Schoolclass.class);
    }
}
