/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Score;

/**
 *
 * @author ThanhTung
 */
public class ScoreDAO extends AbstractHibernateDAO<Score>{
    public ScoreDAO(){
        setClazz(Score.class);
    }
}
