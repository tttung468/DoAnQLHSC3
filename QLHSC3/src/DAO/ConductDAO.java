/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Conduct;

/**
 *
 * @author ThanhTung
 */
public class ConductDAO extends AbstractHibernateDAO<Conduct>{
    public ConductDAO(){
        setClazz(Conduct.class);
    }
}
