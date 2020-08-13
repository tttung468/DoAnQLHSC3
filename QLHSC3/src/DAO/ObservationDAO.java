/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Observation;

/**
 *
 * @author ThanhTung
 */
public class ObservationDAO extends AbstractHibernateDAO<Observation>{
    public ObservationDAO(){
        setClazz(Observation.class);
    }
}
