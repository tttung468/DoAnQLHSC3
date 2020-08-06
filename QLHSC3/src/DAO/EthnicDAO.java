/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Ethnic;

/**
 *
 * @author ThanhTung
 */
public class EthnicDAO extends AbstractHibernateDAO<Ethnic>{
    public EthnicDAO(){
        setClazz(Ethnic.class);
    }
}
