/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.SchoolYear;

/**
 *
 * @author ThanhTung
 */
public class SchoolYearDAO extends AbstractHibernateDAO<SchoolYear>{
    public SchoolYearDAO(){
        setClazz(SchoolYear.class);
    }
}
