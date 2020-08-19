package com.toanhuuvuong.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringNVarcharType;

import com.toanhuuvuong.dao.ISchoolYearDAO;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.sort.Sorter;

public class SchoolYearDAO extends AbstractHibernateDAO<SchoolYear> implements ISchoolYearDAO {
	public SchoolYearDAO() {
		setClazz(SchoolYear.class);
	}

	@Override
	public String generateHQL(Pageable<SchoolYear> pageable) {
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		SchoolYear schoolYear = pageable.getFilterModel();
		StringBuilder sql = new StringBuilder();

//        WHERE schoolYear.code=? AND schoolYear.lowerBound=? 
//              AND schoolYear.upperBound=? AND schoolYear.theme=?
//              AND schoolYear.isDeleted=?

		if (schoolYear != null || searchKey != null) {
			sql.append(" where");
		}
		if (schoolYear != null) {
			// schoolYear.code=?
			if (schoolYear.getCode() != null) {
				sql.append(" schoolYear.code=:code");
			}
			if (schoolYear.getLowerBound() != null && schoolYear.getCode() != null) {
				sql.append(" and");
			}

			// schoolYear.lowerBound=?
			if (schoolYear.getLowerBound() != null) {
				sql.append(" schoolYear.lowerBound=:lowerBound");
			}
			if (schoolYear.getUpperBound() != null
					&& (schoolYear.getCode() != null || schoolYear.getLowerBound() != null)) {
				sql.append(" and");
			}

			// schoolYear.upperBound=?
			if (schoolYear.getUpperBound() != null) {
				sql.append(" schoolYear.upperBound=:upperBound");
			}
			if (schoolYear.getTheme() != null && (schoolYear.getCode() != null || schoolYear.getLowerBound() != null
					|| schoolYear.getUpperBound() != null)) {
				sql.append(" and");
			}

			// schoolYear.theme=?
			if (schoolYear.getTheme() != null) {
				sql.append(" schoolYear.theme=:theme");
			}
			if (schoolYear.getIsDeleted() != null && (schoolYear.getCode() != null || schoolYear.getLowerBound() != null
					|| schoolYear.getUpperBound() != null || schoolYear.getTheme() != null)) {
				sql.append(" and");
			}

			// schoolYear.isDeleted=?
			if (schoolYear.getIsDeleted() != null) {
				sql.append(" schoolYear.isDeleted=:isDeleted");
			}
			if (searchKey != null && (schoolYear.getCode() != null || schoolYear.getLowerBound() != null
					|| schoolYear.getUpperBound() != null || schoolYear.getTheme() != null
					|| schoolYear.getIsDeleted() != null)) {
				sql.append(" and");
			}
		}

//        AND (schoolYear.code LIKE ? OR schoolYear.lowerBound LIKE ? 
//              OR schoolYear.upperBound LIKE ? OR schoolYear.theme LIKE ?)
		if (searchKey != null) {
			sql.append(" (schoolYear.code like :searchKey");
			sql.append(" or schoolYear.lowerBound like :searchKey");
			sql.append(" or schoolYear.upperBound like :searchKey");
			sql.append(" or schoolYear.theme like :searchKey)");
		}

//        ORDER BY ? ?
		if (sorter != null && sorter.getSortBy() != null && sorter.getSortName() != null) {
			sql.append(" order by " + sorter.getSortBy() + " " + sorter.getSortName());
		}

		return sql.toString();
	}

	@Override
	public String generateHQLForFind(Pageable<SchoolYear> pageable) {
//        SELECT * FROM SchoolYear schoolYear
//        WHERE schoolYear.code=? AND schoolYear.lowerBound=? 
//              AND schoolYear.upperBound=? AND schoolYear.theme=?
//              AND schoolYear.isDeleted=?
//        AND (schoolYear.code LIKE ? OR schoolYear.lowerBound LIKE ? 
//              OR schoolYear.upperBound LIKE ? OR schoolYear.theme LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

		// Dòng 1: SELECT * FROM SchoolYear
		String hql = "from SchoolYear schoolYear";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public String generateHQLForCount(Pageable<SchoolYear> pageable) {
//        SELECT COUNT(*) FROM SchoolYear schoolYear
//        WHERE schoolYear.code=? AND schoolYear.lowerBound=? 
//              AND schoolYear.upperBound=? AND schoolYear.theme=?
//              AND schoolYear.isDeleted=?
//        AND (schoolYear.code LIKE ? OR schoolYear.lowerBound LIKE ? 
//              OR schoolYear.upperBound LIKE ? OR schoolYear.theme LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

		// Dòng 1: SELECT COUNT(*) FROM SchoolYear schoolYear
		String hql = "select count(*) from SchoolYear schoolYear";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public Query setValueForHQL(Pageable<SchoolYear> pageable, Session session, String hql) {
		Query query = session.createQuery(hql);
		SchoolYear schoolYear = pageable.getFilterModel();

		if (schoolYear != null) {
			if (schoolYear.getCode() != null) {
				query.setParameter("code", schoolYear.getCode());
			}
			if (schoolYear.getLowerBound() != null) {
				query.setParameter("lowerBound", schoolYear.getLowerBound());
			}
			if (schoolYear.getUpperBound() != null) {
				query.setParameter("upperBound", schoolYear.getUpperBound());
			}
			if (schoolYear.getTheme() != null) {
				query.setParameter("theme", schoolYear.getTheme(), StringNVarcharType.INSTANCE);
			}
			if (schoolYear.getIsDeleted() != null) {
				query.setParameter("isDeleted", schoolYear.getIsDeleted());
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
	public SchoolYear findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
}
