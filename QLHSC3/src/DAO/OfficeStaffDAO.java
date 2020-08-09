/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.OfficeStaff;

/**
 *
 * @author ThanhTung
 */
public class OfficeStaffDAO extends AbstractHibernateDAO<OfficeStaff>{
    public OfficeStaffDAO(){
        setClazz(OfficeStaff.class);
    }
}
