/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Headmaster;

/**
 *
 * @author ThanhTung
 */
public class HeadmasterDAO extends AbstractHibernateDAO<Headmaster>{
    public HeadmasterDAO(){
        setClazz(Headmaster.class);
    }
}
