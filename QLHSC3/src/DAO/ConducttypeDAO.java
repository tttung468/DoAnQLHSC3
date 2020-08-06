/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Conducttype;

/**
 *
 * @author ThanhTung
 */
public class ConducttypeDAO extends AbstractHibernateDAO<Conducttype>{
    public ConducttypeDAO(){
        setClazz(Conducttype.class);
    }
}
