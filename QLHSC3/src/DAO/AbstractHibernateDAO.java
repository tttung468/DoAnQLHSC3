/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.ExecutableList.Sorter;
import pojos.Role;
import qlhsc3.Pageable;

/**
 *
 * @author ThanhTung
 * @param <T>
 */
public abstract class AbstractHibernateDAO< T extends Serializable> {

    private Class< T> clazz;

    public void setClazz(Class< T> clazzToSet) {
        clazz = clazzToSet;
    }

    /**
     * Lấy 1 dòng dữ liệu dựa vào id, trả về object T đã được mapping
     * 
     * @param id
     * @return 
     */
    public T findOne(long id) {
        T entity = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            entity = (T) session.get(clazz, id);     //get
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

    /**
     * Lấy tất cả các dòng trong table clazz
     * Trả về DS các objects được mapping
     * 
     * @return 
     */
    public List<T> findAll() {
        List<T> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            String hql = "from " + clazz.getName();
            Query query = session.createQuery(hql);

            tx = session.beginTransaction();
            list = query.list();    //get all
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return list;
   }

    /**
     * Thêm 1 dòng dữ liệu vào DB
     * Trả về id trên table, nếu thất bại trả về -1, và ngược lại
     * 
     * @param entity
     * @return 
     */
    public long insertOne(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long id = -1;
        
        try {
            tx = session.beginTransaction();
            id = (long) session.save(entity);    //save
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return id;
    }

    /**
     * Cập nhật lại dữ liệu của 1 dòng trong DB
     * 
     * @param entity 
     */
    public void updateOne(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(entity);    //update
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Xóa 1 dòng dữ liệu trong DB
     * 
     * @param entity 
     */
    public void deleteOne(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(entity);    //delete
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Xóa 1 dòng dữ liệu trong DB dựa vào id
     * @param id 
     */
    public void deleteById(long id) {
        final T entity = findOne(id);
        deleteOne(entity);
    }
    
    /**
     * Lấy các dòng dữ liệu trong DB dựa vào pageable
     * 
     * @param pageable
     * @return 
     */
    public List<T> find(Pageable<T> pageable){
        return null;
    }
    
    /**
     * 
     * @param pageable
     * @return 
     */
    public Integer count(Pageable pageable){
        return null;
    }
    
    
    
}
