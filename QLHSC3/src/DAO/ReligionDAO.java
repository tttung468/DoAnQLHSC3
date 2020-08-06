/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Religion;

/**
 *
 * @author ThanhTung
 */
public class ReligionDAO extends AbstractHibernateDAO<Religion>{
    public ReligionDAO(){
        setClazz(Religion.class);
    }
}
