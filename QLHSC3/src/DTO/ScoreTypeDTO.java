/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.ScoreTypeDAO;
import pojos.ScoreType;

/**
 *
 * @author ThanhTung
 */
public class ScoreTypeDTO extends AbstractHibernateDTO<ScoreType>{

    public ScoreTypeDTO() {
        setAbstractHiberanateDAO(new ScoreTypeDAO());
    }
    
}
