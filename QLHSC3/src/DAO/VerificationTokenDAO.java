/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.VerificationToken;

/**
 *
 * @author ThanhTung
 */
public class VerificationTokenDAO extends AbstractHibernateDAO<VerificationToken>{
    public VerificationTokenDAO(){
        setClazz(VerificationToken.class);
    }
}
