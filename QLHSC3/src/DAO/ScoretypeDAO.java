/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Scoretype;

/**
 *
 * @author ThanhTung
 */
public class ScoretypeDAO extends AbstractHibernateDAO<Scoretype>{
    public ScoretypeDAO(){
        setClazz(Scoretype.class);
    }
}
