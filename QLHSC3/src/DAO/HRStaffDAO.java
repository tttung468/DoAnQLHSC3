/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringNVarcharType;
import org.hibernate.type.StringType;
import pagination.Pageable;
import pojos.HRStaff;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class HRStaffDAO extends AbstractHibernateDAO<HRStaff>{
    public HRStaffDAO(){
        setClazz(HRStaff.class);
    }
    
    @Override
    public String generateHQL(Pageable<HRStaff> pageable){
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        HRStaff hrStaff = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();
        
//        WHERE hrStaff.code=? AND hrStaff.name=? AND hrStaff.phone=? AND hrStaff.avatarpath=?
//              AND hrStaff.gender=? AND hrStaff.birth=? AND hrStaff.address=? AND hrStaff.salary=?
//              AND hrStaff.account.id=? AND hrStaff.isDeleted=?
        if (hrStaff != null || searchKey != null) {
            sql.append(" where");
        }
        if (hrStaff != null) {
            //hrStaff.code=? 
            if (hrStaff.getCode() != null) {
                sql.append(" hrStaff.code = :code");
            }
            if (hrStaff.getCode() != null
                    && hrStaff.getName() != null) {
                sql.append(" and");
            }
            
            //hrStaff.name=? 
            if (hrStaff.getName() != null) {
                sql.append(" hrStaff.name = :name");
            }
            if (hrStaff.getPhone() != null
                    && (hrStaff.getCode() != null
                    || hrStaff.getName() != null)) {
                sql.append(" and");
            }
            
            //hrStaff.phone=? 
            if (hrStaff.getPhone()!= null) {
                sql.append(" hrStaff.phone = :phone");
            }
            if (hrStaff.getAvatarpath()!= null
                    && (hrStaff.getCode() != null
                    || hrStaff.getName() != null
                    || hrStaff.getPhone() != null)) {
                sql.append(" and");
            }
            
            //hrStaff.avatarpath=?
            if (hrStaff.getAvatarpath()!= null) {
                sql.append(" hrStaff.avatarpath = :avatarpath");
            }
            if (hrStaff.getGender()!= null
                    && (hrStaff.getCode() != null
                    || hrStaff.getName() != null
                    || hrStaff.getPhone() != null
                    || hrStaff.getAvatarpath()!= null)) {
                sql.append(" and");
            }
            
            //hrStaff.gender=? 
            if (hrStaff.getGender()!= null) {
                sql.append(" hrStaff.gender = :gender");
            }
            if (hrStaff.getBirth()!= null
                    && (hrStaff.getCode() != null
                    || hrStaff.getName() != null
                    || hrStaff.getPhone() != null
                    || hrStaff.getAvatarpath()!= null
                    || hrStaff.getGender()!= null)) {
                sql.append(" and");
            }
            
            //hrStaff.birth=? 
            if (hrStaff.getBirth()!= null) {
                sql.append(" hrStaff.birth = :birth");
            }
            if (hrStaff.getAddress()!= null
                    && (hrStaff.getCode() != null
                    || hrStaff.getName() != null
                    || hrStaff.getPhone() != null
                    || hrStaff.getAvatarpath()!= null
                    || hrStaff.getGender()!= null
                    || hrStaff.getBirth()!= null)) {
                sql.append(" and");
            }
            
            //hrStaff.address=? 
            if (hrStaff.getAddress()!= null) {
                sql.append(" hrStaff.address = :address");
            }
            if (hrStaff.getSalary()!= null
                    && (hrStaff.getCode() != null
                    || hrStaff.getName() != null
                    || hrStaff.getPhone() != null
                    || hrStaff.getAvatarpath()!= null
                    || hrStaff.getGender()!= null
                    || hrStaff.getBirth()!= null
                    || hrStaff.getAddress()!= null)) {
                sql.append(" and");
            }
            
            //hrStaff.salary=?
            if (hrStaff.getSalary()!= null) {
                sql.append(" hrStaff.salary = :salary");
            }
            if (hrStaff.getAccount()!= null
                    && (hrStaff.getCode() != null
                    || hrStaff.getName() != null
                    || hrStaff.getPhone() != null
                    || hrStaff.getAvatarpath()!= null
                    || hrStaff.getGender()!= null
                    || hrStaff.getBirth()!= null
                    || hrStaff.getAddress()!= null
                    || hrStaff.getSalary()!= null)) {
                sql.append(" and");
            }
            
            //hrStaff.account.id=? 
            if (hrStaff.getAccount()!= null) {
                sql.append(" hrStaff.account.id = :accountID");
            }
            if (hrStaff.getIsDeleted()!= null
                    && (hrStaff.getCode() != null
                    || hrStaff.getName() != null
                    || hrStaff.getPhone() != null
                    || hrStaff.getAvatarpath()!= null
                    || hrStaff.getGender()!= null
                    || hrStaff.getBirth()!= null
                    || hrStaff.getAddress()!= null
                    || hrStaff.getSalary()!= null
                    || hrStaff.getAccount()!= null)) {
                sql.append(" and");
            }
            
            //hrStaff.isDeleted=?
            if (hrStaff.getIsDeleted()!= null) {
                sql.append(" hrStaff.isDeleted = :isDeleted");
            }
            if (searchKey != null
                    && (hrStaff.getCode() != null
                    || hrStaff.getName() != null
                    || hrStaff.getPhone() != null
                    || hrStaff.getAvatarpath()!= null
                    || hrStaff.getGender()!= null
                    || hrStaff.getBirth()!= null
                    || hrStaff.getAddress()!= null
                    || hrStaff.getSalary()!= null
                    || hrStaff.getAccount()!= null
                    || hrStaff.getIsDeleted()!= null)) {
                sql.append(" and");
            }
        }

//        AND (hrStaff.code LIKE ? OR hrStaff.name LIKE ? OR hrStaff.phone LIKE ? OR hrStaff.avatarpath LIKE ?
//              OR hrStaff.gender LIKE ? OR hrStaff.birth LIKE ? OR hrStaff.address LIKE ? OR hrStaff.salary LIKE ?
//              OR hrStaff.account.id LIKE ?)   
        if (searchKey != null) {
            sql.append(" (hrStaff.code like :searchKey");
            sql.append(" or hrStaff.name like :searchKey");
            sql.append(" or hrStaff.phone like :searchKey");
            sql.append(" or hrStaff.avatarpath like :searchKey");
            sql.append(" or hrStaff.gender like :searchKey");
            sql.append(" or hrStaff.birth like :searchKey");
            sql.append(" or hrStaff.address like :searchKey");
            sql.append(" or hrStaff.salary like :searchKey");
            sql.append(" or hrStaff.account.id like :searchKey)");
        }
        
//        ORDER BY ? ?        
        if (sorter != null
                && sorter.getSortBy() != null
                && sorter.getSortName() != null) {
            sql.append(" order by " + sorter.getSortBy() + " " + sorter.getSortName());
        }
        
        return sql.toString();        
    }
    
    @Override
    public String generateHQLForFind(Pageable<HRStaff> pageable){
//        SELECT * FROM HRStaff hrStaff
//        WHERE hrStaff.code=? AND hrStaff.name=? AND hrStaff.phone=? AND hrStaff.avatarpath=?
//              AND hrStaff.gender=? AND hrStaff.birth=? AND hrStaff.address=? AND hrStaff.salary=?
//              AND hrStaff.account.id=? AND hrStaff.isDeleted=?
//        AND (hrStaff.code LIKE ? OR hrStaff.name LIKE ? OR hrStaff.phone LIKE ? OR hrStaff.avatarpath LIKE ?
//              OR hrStaff.gender LIKE ? OR hrStaff.birth LIKE ? OR hrStaff.address LIKE ? OR hrStaff.salary LIKE ?
//              OR hrStaff.account.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM HRStaff hrStaff
        String hql = "from HRStaff hrStaff";
        
        //Các dòng còn lại
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public String generateHQLForCount(Pageable<HRStaff> pageable){
//        SELECT COUNT(*) FROM HRStaff hrStaff
//        WHERE hrStaff.code=? AND hrStaff.name=? AND hrStaff.phone=? AND hrStaff.avatarpath=?
//              AND hrStaff.gender=? AND hrStaff.birth=? AND hrStaff.address=? AND hrStaff.salary=?
//              AND hrStaff.account.id=? AND hrStaff.isDeleted=?
//        AND (hrStaff.code LIKE ? OR hrStaff.name LIKE ? OR hrStaff.phone LIKE ? OR hrStaff.avatarpath LIKE ?
//              OR hrStaff.gender LIKE ? OR hrStaff.birth LIKE ? OR hrStaff.address LIKE ? OR hrStaff.salary LIKE ?
//              OR hrStaff.account.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT COUNT(*) FROM HRStaff hrStaff
        String hql = "select count(*) from HRStaff hrStaff";
        
        //Các dòng còn lại
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<HRStaff> pageable, Session session, String hql){
        Query query = session.createQuery(hql);
        HRStaff hrStaff = pageable.getFilterModel();
        
        if(hrStaff != null){
            if(hrStaff.getCode() != null) {
                query.setParameter("code", hrStaff.getCode(), StringType.INSTANCE);
            }
            if(hrStaff.getName() != null){
                query.setParameter("name", hrStaff.getName(), StringNVarcharType.INSTANCE);
            }
            if(hrStaff.getPhone()!= null){
                query.setParameter("phone", hrStaff.getPhone(), StringType.INSTANCE);
            }
            if(hrStaff.getAvatarpath()!= null){
                query.setParameter("avatarpath", hrStaff.getAvatarpath(), StringNVarcharType.INSTANCE);
            }
            if(hrStaff.getGender()!= null){
                query.setParameter("gender", hrStaff.getGender(), StringNVarcharType.INSTANCE);
            }
            if(hrStaff.getBirth()!= null){
                query.setParameter("birth", hrStaff.getBirth());
            }
            if(hrStaff.getAddress()!= null){
                query.setParameter("address", hrStaff.getAddress(), StringNVarcharType.INSTANCE);
            }
            if(hrStaff.getSalary()!= null){
                query.setParameter("salary", hrStaff.getSalary());
            }          
            if(hrStaff.getAccount()!= null){
                query.setParameter("accountID", hrStaff.getAccount().getId());
            }
            if(hrStaff.getIsDeleted()!= null){
                query.setParameter("isDeleted", hrStaff.getIsDeleted());
            }
        }
        if (pageable.getSearchKey() != null) {
            query.setString("searchKey", "%"+pageable.getSearchKey()+"%");
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getLimit());
        }  
        
        return query;
    }
}
