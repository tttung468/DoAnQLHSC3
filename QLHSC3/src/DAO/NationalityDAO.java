/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;
import pojos.Nationality;

/**
 *
 * @author ThanhTung
 */
public class NationalityDAO extends AbstractHibernateDAO<Nationality>{
    public NationalityDAO(){
        setClazz(Nationality.class);
    }
    
    @Override
    public Nationality findByCode(String code) {
        Nationality entity = null;
        List<Nationality> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            String hql = "from Nationality nationality";
            hql += " where nationality.code = :code"; 
            Query query = session.createQuery(hql);

            query.setParameter("code", code, StringType.INSTANCE);
            tx = session.beginTransaction();
            
            list = query.list();    //get all
            if(!list.isEmpty()){
                entity = list.get(0);   //get first entity
            }
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return entity;
    }
}
