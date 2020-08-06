/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Nationality;

/**
 *
 * @author ThanhTung
 */
public class NationalityDAO extends AbstractHibernateDAO<Nationality>{
    public NationalityDAO(){
        setClazz(Nationality.class);
    }
}
