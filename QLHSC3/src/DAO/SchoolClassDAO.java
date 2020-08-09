/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.SchoolClass;

/**
 *
 * @author ThanhTung
 */
public class SchoolClassDAO extends AbstractHibernateDAO<SchoolClass>{
    public SchoolClassDAO(){
        setClazz(SchoolClass.class);
    }
}
