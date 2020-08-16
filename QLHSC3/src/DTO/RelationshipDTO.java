/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.RelationshipDAO;
import pojos.Relationship;

/**
 *
 * @author ThanhTung
 */
public class RelationshipDTO extends AbstractHibernateDTO<Relationship>{

    public RelationshipDTO() {
        setAbstractHiberanateDAO(new RelationshipDAO());
    }
    
}
