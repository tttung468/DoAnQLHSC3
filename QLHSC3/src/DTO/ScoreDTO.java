/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.ScoreDAO;
import pojos.Score;

/**
 *
 * @author ThanhTung
 */
public class ScoreDTO extends AbstractHibernateDTO<Score>{

    public ScoreDTO() {
        setAbstractHiberanateDAO(new ScoreDAO());
    }
    
}
