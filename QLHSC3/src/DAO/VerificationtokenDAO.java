/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Verificationtoken;

/**
 *
 * @author ThanhTung
 */
public class VerificationtokenDAO extends AbstractHibernateDAO<Verificationtoken>{
    public VerificationtokenDAO(){
        setClazz(Verificationtoken.class);
    }
}
