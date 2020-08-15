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
import pojos.OfficeStaff;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class OfficeStaffDAO extends AbstractHibernateDAO<OfficeStaff>{
    public OfficeStaffDAO(){
        setClazz(OfficeStaff.class);
    }
    
    @Override
    public String generateHQL(Pageable<OfficeStaff> pageable){
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        OfficeStaff officeStaff = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();
        
//        WHERE officeStaff.code=? AND officeStaff.name=? AND officeStaff.phone=? AND officeStaff.avatarpath=?
//              AND officeStaff.gender=? AND officeStaff.birth=? AND officeStaff.address=? AND officeStaff.salary=?
//              AND officeStaff.account.id=? AND officeStaff.isDeleted=?
        if (officeStaff != null || searchKey != null) {
            sql.append(" where");
        }
        if (officeStaff != null) {
            //officeStaff.code=? 
            if (officeStaff.getCode() != null) {
                sql.append(" officeStaff.code = :code");
            }
            if (officeStaff.getCode() != null
                    && officeStaff.getName() != null) {
                sql.append(" and");
            }
            
            //officeStaff.name=? 
            if (officeStaff.getName() != null) {
                sql.append(" officeStaff.name = :name");
            }
            if (officeStaff.getPhone() != null
                    && (officeStaff.getCode() != null
                    || officeStaff.getName() != null)) {
                sql.append(" and");
            }
            
            //officeStaff.phone=? 
            if (officeStaff.getPhone()!= null) {
                sql.append(" officeStaff.phone = :phone");
            }
            if (officeStaff.getAvatarpath()!= null
                    && (officeStaff.getCode() != null
                    || officeStaff.getName() != null
                    || officeStaff.getPhone() != null)) {
                sql.append(" and");
            }
            
            //officeStaff.avatarpath=?
            if (officeStaff.getAvatarpath()!= null) {
                sql.append(" officeStaff.avatarpath = :avatarpath");
            }
            if (officeStaff.getGender()!= null
                    && (officeStaff.getCode() != null
                    || officeStaff.getName() != null
                    || officeStaff.getPhone() != null
                    || officeStaff.getAvatarpath()!= null)) {
                sql.append(" and");
            }
            
            //officeStaff.gender=? 
            if (officeStaff.getGender()!= null) {
                sql.append(" officeStaff.gender = :gender");
            }
            if (officeStaff.getBirth()!= null
                    && (officeStaff.getCode() != null
                    || officeStaff.getName() != null
                    || officeStaff.getPhone() != null
                    || officeStaff.getAvatarpath()!= null
                    || officeStaff.getGender()!= null)) {
                sql.append(" and");
            }
            
            //officeStaff.birth=? 
            if (officeStaff.getBirth()!= null) {
                sql.append(" officeStaff.birth = :birth");
            }
            if (officeStaff.getAddress()!= null
                    && (officeStaff.getCode() != null
                    || officeStaff.getName() != null
                    || officeStaff.getPhone() != null
                    || officeStaff.getAvatarpath()!= null
                    || officeStaff.getGender()!= null
                    || officeStaff.getBirth()!= null)) {
                sql.append(" and");
            }
            
            //officeStaff.address=? 
            if (officeStaff.getAddress()!= null) {
                sql.append(" officeStaff.address = :address");
            }
            if (officeStaff.getSalary()!= null
                    && (officeStaff.getCode() != null
                    || officeStaff.getName() != null
                    || officeStaff.getPhone() != null
                    || officeStaff.getAvatarpath()!= null
                    || officeStaff.getGender()!= null
                    || officeStaff.getBirth()!= null
                    || officeStaff.getAddress()!= null)) {
                sql.append(" and");
            }
            
            //officeStaff.salary=?
            if (officeStaff.getSalary()!= null) {
                sql.append(" officeStaff.salary = :salary");
            }
            if (officeStaff.getAccount()!= null
                    && (officeStaff.getCode() != null
                    || officeStaff.getName() != null
                    || officeStaff.getPhone() != null
                    || officeStaff.getAvatarpath()!= null
                    || officeStaff.getGender()!= null
                    || officeStaff.getBirth()!= null
                    || officeStaff.getAddress()!= null
                    || officeStaff.getSalary()!= null)) {
                sql.append(" and");
            }
            
            //officeStaff.account.id=? 
            if (officeStaff.getAccount()!= null) {
                sql.append(" officeStaff.account.id = :accountID");
            }
            if (officeStaff.getIsDeleted()!= null
                    && (officeStaff.getCode() != null
                    || officeStaff.getName() != null
                    || officeStaff.getPhone() != null
                    || officeStaff.getAvatarpath()!= null
                    || officeStaff.getGender()!= null
                    || officeStaff.getBirth()!= null
                    || officeStaff.getAddress()!= null
                    || officeStaff.getSalary()!= null
                    || officeStaff.getAccount()!= null)) {
                sql.append(" and");
            }
            
            //officeStaff.isDeleted=?
            if (officeStaff.getIsDeleted()!= null) {
                sql.append(" officeStaff.isDeleted = :isDeleted");
            }
            if (searchKey != null
                    && (officeStaff.getCode() != null
                    || officeStaff.getName() != null
                    || officeStaff.getPhone() != null
                    || officeStaff.getAvatarpath()!= null
                    || officeStaff.getGender()!= null
                    || officeStaff.getBirth()!= null
                    || officeStaff.getAddress()!= null
                    || officeStaff.getSalary()!= null
                    || officeStaff.getAccount()!= null
                    || officeStaff.getIsDeleted()!= null)) {
                sql.append(" and");
            }
        }

//        AND (officeStaff.code LIKE ? OR officeStaff.name LIKE ? OR officeStaff.phone LIKE ? OR officeStaff.avatarpath LIKE ?
//              OR officeStaff.gender LIKE ? OR officeStaff.birth LIKE ? OR officeStaff.address LIKE ? OR officeStaff.salary LIKE ?
//              OR officeStaff.account.id LIKE ?)
        if (searchKey != null) {
            sql.append(" (officeStaff.code like :searchKey");
            sql.append(" or officeStaff.name like :searchKey");
            sql.append(" or officeStaff.phone like :searchKey");
            sql.append(" or officeStaff.avatarpath like :searchKey");
            sql.append(" or officeStaff.gender like :searchKey");
            sql.append(" or officeStaff.birth like :searchKey");
            sql.append(" or officeStaff.address like :searchKey");
            sql.append(" or officeStaff.salary like :searchKey");
            sql.append(" or officeStaff.account.id like :searchKey)");
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
    public String generateHQLForFind(Pageable<OfficeStaff> pageable){
//        SELECT * FROM OfficeStaff officeStaff
//        WHERE officeStaff.code=? AND officeStaff.name=? AND officeStaff.phone=? AND officeStaff.avatarpath=?
//              AND officeStaff.gender=? AND officeStaff.birth=? AND officeStaff.address=? AND officeStaff.salary=?
//              AND officeStaff.account.id=? AND officeStaff.isDeleted=?
//        AND (officeStaff.code LIKE ? OR officeStaff.name LIKE ? OR officeStaff.phone LIKE ? OR officeStaff.avatarpath LIKE ?
//              OR officeStaff.gender LIKE ? OR officeStaff.birth LIKE ? OR officeStaff.address LIKE ? OR officeStaff.salary LIKE ?
//              OR officeStaff.account.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM OfficeStaff officeStaff   
        String hql = "from OfficeStaff officeStaff";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public String generateHQLForCount(Pageable<OfficeStaff> pageable){
//        SELECT COUNT(*) FROM OfficeStaff officeStaff
//        WHERE officeStaff.code=? AND officeStaff.name=? AND officeStaff.phone=? AND officeStaff.avatarpath=?
//              AND officeStaff.gender=? AND officeStaff.birth=? AND officeStaff.address=? AND officeStaff.salary=?
//              AND officeStaff.account.id=? AND officeStaff.isDeleted=?
//        AND (officeStaff.code LIKE ? OR officeStaff.name LIKE ? OR officeStaff.phone LIKE ? OR officeStaff.avatarpath LIKE ?
//              OR officeStaff.gender LIKE ? OR officeStaff.birth LIKE ? OR officeStaff.address LIKE ? OR officeStaff.salary LIKE ?
//              OR officeStaff.account.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT COUNT(*) FROM OfficeStaff officeStaff   
        String hql = "select count(*) from OfficeStaff officeStaff";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<OfficeStaff> pageable, Session session, String hql){
        Query query = session.createQuery(hql);
        OfficeStaff officeStaff = pageable.getFilterModel();
        
        if(officeStaff != null){
            if(officeStaff.getCode() != null) {
                query.setParameter("code", officeStaff.getCode(), StringType.INSTANCE);
            }
            if(officeStaff.getName() != null){
                query.setParameter("name", officeStaff.getName(), StringNVarcharType.INSTANCE);
            }
            if(officeStaff.getPhone()!= null){
                query.setParameter("phone", officeStaff.getPhone(), StringType.INSTANCE);
            }
            if(officeStaff.getAvatarpath()!= null){
                query.setParameter("avatarpath", officeStaff.getAvatarpath(), StringNVarcharType.INSTANCE);
            }
            if(officeStaff.getGender()!= null){
                query.setParameter("gender", officeStaff.getGender(), StringNVarcharType.INSTANCE);
            }
            if(officeStaff.getBirth()!= null){
                query.setParameter("birth", officeStaff.getBirth());
            }
            if(officeStaff.getAddress()!= null){
                query.setParameter("address", officeStaff.getAddress(), StringNVarcharType.INSTANCE);
            }
            if(officeStaff.getSalary()!= null){
                query.setParameter("salary", officeStaff.getSalary());
            }          
            if(officeStaff.getAccount()!= null){
                query.setParameter("accountID", officeStaff.getAccount().getId());
            }
            if(officeStaff.getIsDeleted()!= null){
                query.setParameter("isDeleted", officeStaff.getIsDeleted());
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
