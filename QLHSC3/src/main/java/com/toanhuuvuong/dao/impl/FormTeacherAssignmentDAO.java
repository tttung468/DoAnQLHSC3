package com.toanhuuvuong.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.toanhuuvuong.model.FormTeacherAssignment;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.sort.Sorter;

public class FormTeacherAssignmentDAO extends AbstractHibernateDAO<FormTeacherAssignment> {
	public FormTeacherAssignmentDAO() {
		setClazz(FormTeacherAssignment.class);
	}

	@Override
	public String generateHQL(Pageable<FormTeacherAssignment> pageable) {
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		FormTeacherAssignment formTeacherAssignment = pageable.getFilterModel();
		StringBuilder sql = new StringBuilder();

//        WHERE formTeacherAssignment.capacity=? AND formTeacherAssignment.schoolYear.id=? 
//              AND formTeacherAssignment.teacher.id=? AND formTeacherAssignment.schoolClass.id=? 
//              AND formTeacherAssignment.isDeleted=?
		if (formTeacherAssignment != null || searchKey != null) {
			sql.append(" where");
		}
		if (formTeacherAssignment != null) {
			// formTeacherAssignment.capacity=?
			if (formTeacherAssignment.getCapacity() != null) {
				sql.append(" formTeacherAssignment.capacity = :capacity");
			}
			if (formTeacherAssignment.getSchoolYear() != null && formTeacherAssignment.getCapacity() != null) {
				sql.append(" and");
			}

			// formTeacherAssignment.schoolYear.id=?
			if (formTeacherAssignment.getSchoolYear() != null) {
				sql.append(" formTeacherAssignment.schoolYear.id = :schoolYearID");
			}
			if (formTeacherAssignment.getTeacher() != null
					&& (formTeacherAssignment.getCapacity() != null || formTeacherAssignment.getSchoolYear() != null)) {
				sql.append(" and");
			}

			// formTeacherAssignment.teacher.id=?
			if (formTeacherAssignment.getTeacher() != null) {
				sql.append(" formTeacherAssignment.teacher.id = :teacherID");
			}
			if (formTeacherAssignment.getSchoolClass() != null && (formTeacherAssignment.getCapacity() != null
					|| formTeacherAssignment.getSchoolYear() != null || formTeacherAssignment.getTeacher() != null)) {
				sql.append(" and");
			}

			// formTeacherAssignment.schoolClass.id=?
			if (formTeacherAssignment.getSchoolClass() != null) {
				sql.append(" formTeacherAssignment.schoolClass.id = :schoolClassID");
			}
			if (formTeacherAssignment.getIsDeleted() != null && (formTeacherAssignment.getCapacity() != null
					|| formTeacherAssignment.getSchoolYear() != null || formTeacherAssignment.getTeacher() != null
					|| formTeacherAssignment.getSchoolClass() != null)) {
				sql.append(" and");
			}

			// formTeacherAssignment.isDeleted=?
			if (formTeacherAssignment.getIsDeleted() != null) {
				sql.append(" formTeacherAssignment.isDeleted = :isDeleted");
			}
			if (searchKey != null && (formTeacherAssignment.getCapacity() != null
					|| formTeacherAssignment.getSchoolYear() != null || formTeacherAssignment.getTeacher() != null
					|| formTeacherAssignment.getSchoolClass() != null
					|| formTeacherAssignment.getIsDeleted() != null)) {
				sql.append(" and");
			}
		}

//        AND (formTeacherAssignment.capacity LIKE ? OR formTeacherAssignment.schoolYear.id LIKE ? 
//              OR formTeacherAssignment.teacher.id LIKE ? OR formTeacherAssignment.schoolClass.id LIKE ? )        
		if (searchKey != null) {
			sql.append(" (formTeacherAssignment.capacity like :searchKey");
			sql.append(" or formTeacherAssignment.schoolYear.id like :searchKey");
			sql.append(" or formTeacherAssignment.teacher.id like :searchKey");
			sql.append(" or formTeacherAssignment.schoolClass.id like :searchKey)");
		}

//      ORDER BY ? ?       
		if (sorter != null && sorter.getSortBy() != null && sorter.getSortName() != null) {
			sql.append(" order by " + sorter.getSortBy() + " " + sorter.getSortName());
		}

		return sql.toString();
	}

	@Override
	public String generateHQLForFind(Pageable<FormTeacherAssignment> pageable) {
//        SELECT * FROM FormTeacherAssignment formTeacherAssignment
//        WHERE formTeacherAssignment.capacity=? AND formTeacherAssignment.schoolYear.id=? 
//              AND formTeacherAssignment.teacher.id=? AND formTeacherAssignment.schoolClass.id=? 
//              AND formTeacherAssignment.isDeleted=?
//        AND (formTeacherAssignment.capacity LIKE ? OR formTeacherAssignment.schoolYear.id LIKE ? 
//              OR formTeacherAssignment.teacher.id LIKE ? OR formTeacherAssignment.schoolClass.id LIKE ? )
//        ORDER BY ? ?
//        LIMIT ?, ?

		// SELECT * FROM FormTeacherAssignment formTeacherAssignment
		String hql = "from FormTeacherAssignment formTeacherAssignment";

		// Cac dong con lai
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public String generateHQLForCount(Pageable<FormTeacherAssignment> pageable) {
//        SELECT COUNT(*) FROM FormTeacherAssignment formTeacherAssignment
//        WHERE formTeacherAssignment.capacity=? AND formTeacherAssignment.schoolYear.id=? 
//              AND formTeacherAssignment.teacher.id=? AND formTeacherAssignment.schoolClass.id=? 
//              AND formTeacherAssignment.isDeleted=?
//        AND (formTeacherAssignment.capacity LIKE ? OR formTeacherAssignment.schoolYear.id LIKE ? 
//              OR formTeacherAssignment.teacher.id LIKE ? OR formTeacherAssignment.schoolClass.id LIKE ? )
//        ORDER BY ? ?
//        LIMIT ?, ?

		// SELECT COUNT(*) FROM FormTeacherAssignment formTeacherAssignment
		String hql = "select count(*) from FormTeacherAssignment formTeacherAssignment";

		// Cac dong con lai
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public Query setValueForHQL(Pageable<FormTeacherAssignment> pageable, Session session, String hql) {
		Query query = session.createQuery(hql);
		FormTeacherAssignment formTeacherAssignment = pageable.getFilterModel();

		if (formTeacherAssignment != null) {
			if (formTeacherAssignment.getCapacity() != null) {
				query.setParameter("capacity", formTeacherAssignment.getCapacity());
			}
			if (formTeacherAssignment.getSchoolYear() != null) {
				query.setParameter("schoolYearID", formTeacherAssignment.getSchoolYear().getId());
			}
			if (formTeacherAssignment.getTeacher() != null) {
				query.setParameter("teacherID", formTeacherAssignment.getTeacher().getId());
			}
			if (formTeacherAssignment.getSchoolClass() != null) {
				query.setParameter("schoolClassID", formTeacherAssignment.getSchoolClass().getId());
			}
			if (formTeacherAssignment.getIsDeleted() != null) {
				query.setParameter("isDeleted", formTeacherAssignment.getIsDeleted());
			}
		}
		if (pageable.getSearchKey() != null) {
			query.setString("searchKey", "%" + pageable.getSearchKey() + "%");
		}
		if (pageable.getOffset() != null && pageable.getLimit() != null) {
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getLimit());
		}

		return query;
	}
}
