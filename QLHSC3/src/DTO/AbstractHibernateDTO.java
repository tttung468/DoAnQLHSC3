/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.AbstractHibernateDAO;
import DAO.AccountDAO;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import pagination.Pageable;
import pojos.Account;
import qlhsc3.Generic;

/**
 *
 * @author ThanhTung
 * @param <T>
 */
public abstract class AbstractHibernateDTO<T extends Generic> {
    
    protected AbstractHibernateDAO<T> dao;

    protected void setAbstractHiberanateDAO(AbstractHibernateDAO<T> dao1){
        dao = dao1;
    }
    
    public T findOne(long id) {
        return dao.findOne(id);
    }
    
    public List<T> findAll() {
        return dao.findAll();
    }
    
    public T insertOne(T entity, Account creator){
        Long id;
        
        entity.setIsDeleted(false);
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity.setCreatedBy(creator != null ? creator.getUsername() : "UNKNOWN");

        id = dao.insertOne(entity);
        entity.setId(id);
        
        return entity;
    }
    
    public T updateOne(T entity, Account editor){
        T odlEntity = dao.findOne(entity.getId());
        
        if (odlEntity != null) {
            entity.setCreatedDate(odlEntity.getCreatedDate());
            entity.setCreatedBy(odlEntity.getCreatedBy());
            entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            entity.setModifiedBy(editor != null ? editor.getUsername() : "UNKNOWN");
        }
        
        dao.updateOne(entity);
        return entity;
    }
    
    public void deleteOne(T entity){
        dao.deleteOne(entity);
    }
    
    public void deleteById(long id){
        dao.deleteById(id);
    }
    
    public void delete(Long[] ids){
        for (Long id : ids) {
            deleteById(id);
        }
    }
    
    public List<T> find(Pageable<T> pageable){
        return dao.find(pageable);
    }
    
    public Long count(Pageable<T> pageable){
        return dao.count(pageable);
    }
    
    public Map<String, String> validate(T entity) {
        Map<String, String> map = new Hashtable<String, String>();

        if (dao.findByCode(entity.getCode()) != null) {
            map.put("messageCode", "code_existed");
            map.put("alert", "danger");
        } else {
            map.put("messageCode", "insert_success");
            map.put("alert", "success");
        }

        return map;
    }

}
