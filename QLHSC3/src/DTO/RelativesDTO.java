/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.RelativesDAO;
import pojos.Relatives;

/**
 *
 * @author ThanhTung
 */
public class RelativesDTO extends AbstractHibernateDTO<Relatives>{

    public RelativesDTO() {
        setAbstractHiberanateDAO(new RelativesDAO());
    }
    
}
