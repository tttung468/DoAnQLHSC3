/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import pagination.Pageable;
import pojos.TeacherAssignment;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class TeacherAssignmentDAO extends AbstractHibernateDAO<TeacherAssignment>{
    public TeacherAssignmentDAO(){
        setClazz(TeacherAssignment.class);
    }
    
    @Override
    public String generateHQL(Pageable<TeacherAssignment> pageable){
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        TeacherAssignment teacherAssignment = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();

//        WHERE teacherAssignment.subject.id=? AND teacherAssignment.semester.id=? 
//              AND teacherAssignment.schoolYear.id=? AND teacherAssignment.teacher.id=?
//              AND teacherAssignment.schoolClass.id=? AND teacherAssignment.idDeleted=?
        if (teacherAssignment != null || searchKey != null) {
            sql.append(" where");
        }
        if (teacherAssignment != null) {
            //teacherAssignment.subject.id=?
            if(teacherAssignment.getSubject() != null){
                sql.append(" teacherAssignment.subject.id = :subjectID");
            }
            if(teacherAssignment.getSemester() != null
                    && teacherAssignment.getSubject() != null){
                sql.append(" and");
            }
            
            //teacherAssignment.semester.id=? 
            if(teacherAssignment.getSemester()!= null){
                sql.append(" teacherAssignment.semester.id = :semesterID");
            }
            if(teacherAssignment.getSchoolYear()!= null
                    && (teacherAssignment.getSubject() != null
                    || teacherAssignment.getSemester() != null)){
                sql.append(" and");
            }
            
            //teacherAssignment.schoolYear.id=?
            if(teacherAssignment.getSchoolYear()!= null){
                sql.append(" teacherAssignment.schoolYear.id = :schoolYearID");
            }
            if(teacherAssignment.getTeacher()!= null
                    && (teacherAssignment.getSubject() != null
                    || teacherAssignment.getSemester() != null
                    || teacherAssignment.getSchoolYear()!= null)){
                sql.append(" and");
            }
            
            //teacherAssignment.teacher.id=?
            if(teacherAssignment.getTeacher()!= null){
                sql.append(" teacherAssignment.teacher.id = :teacherID");
            }
            if(teacherAssignment.getSchoolClass()!= null
                    && (teacherAssignment.getSubject() != null
                    || teacherAssignment.getSemester() != null
                    || teacherAssignment.getSchoolYear()!= null
                    || teacherAssignment.getTeacher()!= null)){
                sql.append(" and");
            }
            
            //teacherAssignment.schoolClass.id=? 
            if(teacherAssignment.getSchoolClass()!= null){
                sql.append(" teacherAssignment.schoolClass.id = :schoolClassID");
            }
            if(teacherAssignment.getIsDeleted()!= null
                    && (teacherAssignment.getSubject() != null
                    || teacherAssignment.getSemester() != null
                    || teacherAssignment.getSchoolYear()!= null
                    || teacherAssignment.getTeacher()!= null
                    || teacherAssignment.getSchoolClass()!= null)){
                sql.append(" and");
            }
            
            //teacherAssignment.idDeleted=?
            if(teacherAssignment.getIsDeleted()!= null){
                sql.append(" teacherAssignment.idDeleted = :idDeleted");
            }
            if(searchKey != null
                    && (teacherAssignment.getSubject() != null
                    || teacherAssignment.getSemester() != null
                    || teacherAssignment.getSchoolYear()!= null
                    || teacherAssignment.getTeacher()!= null
                    || teacherAssignment.getSchoolClass()!= null
                    || teacherAssignment.getIsDeleted()!= null)){
                sql.append(" and");
            }
        }

//        AND (teacherAssignment.subject.id LIKE ? OR teacherAssignment.semester.id LIKE ? 
//              OR teacherAssignment.schoolYear.id LIKE ? OR teacherAssignment.teacher.id LIKE ?
//              OR teacherAssignment.schoolClass.id LIKE ?)
        if (searchKey != null) {
            sql.append(" (teacherAssignment.subject.id like :searchKey");
            sql.append(" or teacherAssignment.semester.id like :searchKey");
            sql.append(" or teacherAssignment.schoolYear.id like :searchKey");
            sql.append(" or teacherAssignment.teacher.id like :searchKey");
            sql.append(" or teacherAssignment.schoolClass.id like :searchKey)");
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
    public String generateHQLForFind(Pageable<TeacherAssignment> pageable){
//        SELECT * FROM TeacherAssignment teacherAssignment 
//        WHERE teacherAssignment.subject.id=? AND teacherAssignment.semester.id=? 
//              AND teacherAssignment.schoolYear.id=? AND teacherAssignment.teacher.id=?
//              AND teacherAssignment.schoolClass.id=? AND teacherAssignment.idDeleted=?
//        AND (teacherAssignment.subject.id LIKE ? OR teacherAssignment.semester.id LIKE ? 
//              OR teacherAssignment.schoolYear.id LIKE ? OR teacherAssignment.teacher.id LIKE ?
//              OR teacherAssignment.schoolClass.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM TeacherAssignment teacherAssignment    
        String hql = "from TeacherAssignment teacherAssignment";
        
        //Các dòng còn lại
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public String generateHQLForCount(Pageable<TeacherAssignment> pageable){
//        SELECT COUNT(*) FROM TeacherAssignment teacherAssignment 
//        WHERE teacherAssignment.subject.id=? AND teacherAssignment.semester.id=? 
//              AND teacherAssignment.schoolYear.id=? AND teacherAssignment.teacher.id=?
//              AND teacherAssignment.schoolClass.id=? AND teacherAssignment.idDeleted=?
//        AND (teacherAssignment.subject.id LIKE ? OR teacherAssignment.semester.id LIKE ? 
//              OR teacherAssignment.schoolYear.id LIKE ? OR teacherAssignment.teacher.id LIKE ?
//              OR teacherAssignment.schoolClass.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT COUNT(*) FROM TeacherAssignment teacherAssignment    
        String hql = "select count(*) from TeacherAssignment teacherAssignment";
        
        //Các dòng còn lại
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<TeacherAssignment> pageable, Session session, String hql){
        Query query = session.createQuery(hql);
        TeacherAssignment teacherAssignment = pageable.getFilterModel();
        
        if(teacherAssignment != null){
            if(teacherAssignment.getSubject() != null){
                query.setParameter("subjectID", teacherAssignment.getSubject().getId());
            }
            if(teacherAssignment.getSemester()!= null){
                query.setParameter("semesterID", teacherAssignment.getSemester().getId());
            }
            if(teacherAssignment.getSchoolYear()!= null){
                query.setParameter("schoolYearID", teacherAssignment.getSchoolYear().getId());
            }
            if(teacherAssignment.getTeacher()!= null){
                query.setParameter("teacherID", teacherAssignment.getTeacher().getId());
            }
            if(teacherAssignment.getSchoolClass()!= null){
                query.setParameter("schoolClassID", teacherAssignment.getSchoolClass().getId());
            }
            if(teacherAssignment.getIsDeleted()!= null){
                query.setParameter("isDeleted", teacherAssignment.getIsDeleted());
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
