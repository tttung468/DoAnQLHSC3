package com.toanhuuvuong.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringNVarcharType;
import org.hibernate.type.StringType;

import com.toanhuuvuong.dao.ISchoolClassDAO;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.sort.Sorter;

public class SchoolClassDAO extends AbstractHibernateDAO<SchoolClass> implements ISchoolClassDAO {
	public SchoolClassDAO() {
		setClazz(SchoolClass.class);
	}

	@Override
	public String generateHQL(Pageable<SchoolClass> pageable) {
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		SchoolClass schoolClass = pageable.getFilterModel();
		StringBuilder sql = new StringBuilder();

		// Dòng 1: SELECT * FROM SchoolClass

		// Dòng 2: WHERE code = ? AND name = ? AND gradeid = ? AND isdeleted = ?
		if (schoolClass != null || searchKey != null) {
			sql.append(" where");
		}
		if (schoolClass != null) {
			// code
			if (schoolClass.getCode() != null) {
				sql.append(" schoolClass.code = :code");
			}
			if (schoolClass.getCode() != null && schoolClass.getName() != null) {
				sql.append(" and");
			}

			// name
			if (schoolClass.getName() != null) {
				sql.append(" schoolClass.name = :name");
			}
			if (schoolClass.getGrade() != null && (schoolClass.getCode() != null || schoolClass.getName() != null)) {
				sql.append(" and");
			}

			// gradeid
			if (schoolClass.getGrade() != null) {
				sql.append(" schoolClass.grade.name = :gradeName");
			}
			if (schoolClass.getIsDeleted() != null && (schoolClass.getCode() != null || schoolClass.getName() != null
					|| schoolClass.getGrade() != null)) {
				sql.append(" and");
			}

			// isDeleted
			if (schoolClass.getIsDeleted() != null) {
				sql.append(" schoolClass.isDeleted = :isDeleted");
			}
			if (searchKey != null && (schoolClass.getCode() != null || schoolClass.getName() != null
					|| schoolClass.getGrade() != null || schoolClass.getIsDeleted() != null)) {
				sql.append(" and");
			}
		}

		// Dòng 3: AND (code LIKE ? OR name LIKE ? OR gradeid LIKE ?)
		if (searchKey != null) {
			sql.append(" (schoolClass.code like :searchKey");
			sql.append(" or schoolClass.name like :searchKey");
			sql.append(" or schoolClass.grade.name like :searchKey)");
		}

		// Dòng 4: ORDER BY ?, ?
		if (sorter != null && sorter.getSortBy() != null && sorter.getSortName() != null) {
			sql.append(" order by " + sorter.getSortBy() + " " + sorter.getSortName());
		}

		return sql.toString();
	}

	@Override
	public String generateHQLForFind(Pageable<SchoolClass> pageable) {
//        SELECT * FROM SchoolClass
//        WHERE code = ? AND name = ? AND gradeid = ? AND isdeleted = ?
//        AND (code LIKE ? OR name LIKE ? OR gradeid LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

		// Dòng 1: SELECT * FROM SchoolClass
		String hql = " from SchoolClass schoolClass";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public String generateHQLForCount(Pageable<SchoolClass> pageable) {
//        SELECT COUNT(*) FROM SchoolClass 
//        WHERE code = ? AND name = ? AND gradeid = ? AND isdeleted = ?
//        AND (code LIKE ? OR name LIKE ? OR gradeid LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

		// Dòng 1: SELECT * FROM SchoolClass
		String hql = " select count(*) from SchoolClass schoolClass";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public Query setValueForHQL(Pageable<SchoolClass> pageable, Session session, String hql) {
		Query query = session.createQuery(hql);
		SchoolClass schoolClass = pageable.getFilterModel();

		if (schoolClass != null) {
			if (schoolClass.getCode() != null) {
				query.setParameter("code", schoolClass.getCode(), StringType.INSTANCE);
			}
			if (schoolClass.getName() != null) {
				query.setParameter("name", schoolClass.getName(), StringNVarcharType.INSTANCE);
			}
			if (schoolClass.getGrade() != null) {
				query.setParameter("gradeName", schoolClass.getGrade().getName());
			}
			if (schoolClass.getIsDeleted() != null) {
				query.setParameter("isDeleted", schoolClass.getIsDeleted());
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

	@Override
	public SchoolClass findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchoolClass findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SchoolClass> findByGradeCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
}
