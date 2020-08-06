/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Hrstaff;

/**
 *
 * @author ThanhTung
 */
public class HrstaffDAO extends AbstractHibernateDAO<Hrstaff>{
    public HrstaffDAO(){
        setClazz(Hrstaff.class);
    }
}
