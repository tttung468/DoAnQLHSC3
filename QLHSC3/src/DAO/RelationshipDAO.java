/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Relationship;

/**
 *
 * @author ThanhTung
 */
public class RelationshipDAO extends AbstractHibernateDAO<Relationship>{
    public RelationshipDAO(){
        setClazz(Relationship.class);
    }
}
