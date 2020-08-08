/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Teacherassignment;

/**
 *
 * @author ThanhTung
 */
public class TeacherassignmentDAO extends AbstractHibernateDAO<Teacherassignment>{
    public TeacherassignmentDAO(){
        setClazz(Teacherassignment.class);
    }
}
