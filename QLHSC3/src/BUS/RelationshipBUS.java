/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.RelationshipDAO;
import pojos.Relationship;

/**
 *
 * @author ThanhTung
 */
public class RelationshipBUS extends AbstractHibernateBUS<Relationship>{

    public RelationshipBUS() {
        setAbstractHiberanateDAO(new RelationshipDAO());
    }
    
}
