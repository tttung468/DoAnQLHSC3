/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Relatives;

/**
 *
 * @author ThanhTung
 */
public class RelativesDAO extends AbstractHibernateDAO<Relatives>{
    public RelativesDAO(){
        setClazz(Relatives.class);
    }
}
