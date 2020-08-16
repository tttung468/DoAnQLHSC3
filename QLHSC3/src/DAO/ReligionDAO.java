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
import pojos.Religion;

/**
 *
 * @author ThanhTung
 */
public class ReligionDAO extends AbstractHibernateDAO<Religion>{
    public ReligionDAO(){
        setClazz(Religion.class);
    }
    
    @Override
    public Religion findByCode(String code) {
        Religion entity = null;
        List<Religion> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            String hql = "from Religion religion";
            hql += " where religion.code = :code"; 
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
