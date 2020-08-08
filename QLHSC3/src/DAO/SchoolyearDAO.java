/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Schoolyear;

/**
 *
 * @author ThanhTung
 */
public class SchoolyearDAO extends AbstractHibernateDAO<Schoolyear>{
    public SchoolyearDAO(){
        setClazz(Schoolyear.class);
    }
}
