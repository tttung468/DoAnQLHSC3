/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Regulation;

/**
 *
 * @author ThanhTung
 */
public class RegulationDAO extends AbstractHibernateDAO<Regulation>{
    public RegulationDAO(){
        setClazz(Regulation.class);
    }
}
