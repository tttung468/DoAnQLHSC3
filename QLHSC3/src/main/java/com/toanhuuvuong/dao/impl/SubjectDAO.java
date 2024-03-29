package com.toanhuuvuong.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringNVarcharType;
import org.hibernate.type.StringType;

import com.toanhuuvuong.dao.ISubjectDAO;
import com.toanhuuvuong.model.Subject;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.sort.Sorter;

public class SubjectDAO extends AbstractHibernateDAO<Subject> implements ISubjectDAO {
	public SubjectDAO() {
		setClazz(Subject.class);
	}

	@Override
	public String generateHQL(Pageable<Subject> pageable) {
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		Subject subject = pageable.getFilterModel();
		StringBuilder sql = new StringBuilder();

//        WHERE subject.code=? AND subject.name=? 
//              AND subject.classHours=? AND subject.factor=?
//              AND subject.isDeleted=?
		if (subject != null || searchKey != null) {
			sql.append(" where");
		}
		if (subject != null) {
			// subject.code=?
			if (subject.getCode() != null) {
				sql.append(" subject.code=:code");
			}
			if (subject.getName() != null && subject.getCode() != null) {
				sql.append(" and");
			}

			// subject.name=?
			if (subject.getName() != null) {
				sql.append(" subject.name=:name");
			}
			if (subject.getClassHours() != null && (subject.getCode() != null || subject.getName() != null)) {
				sql.append(" and");
			}

			// subject.classHours=?
			if (subject.getClassHours() != null) {
				sql.append(" subject.classHours=:classHours");
			}
			if (subject.getFactor() != null
					&& (subject.getCode() != null || subject.getName() != null || subject.getClassHours() != null)) {
				sql.append(" and");
			}

			// subject.factor=?
			if (subject.getFactor() != null) {
				sql.append(" subject.factor=:factor");
			}
			if (subject.getIsDeleted() != null && (subject.getCode() != null || subject.getName() != null
					|| subject.getClassHours() != null || subject.getFactor() != null)) {
				sql.append(" and");
			}

			// subject.isDeleted=?
			if (subject.getIsDeleted() != null) {
				sql.append(" subject.isDeleted=:isDeleted");
			}
			if (searchKey != null
					&& (subject.getCode() != null || subject.getName() != null || subject.getClassHours() != null
							|| subject.getFactor() != null || subject.getIsDeleted() != null)) {
				sql.append(" and");
			}
		}

//        AND (subject.code LIKE ? OR subject.name LIKE ? 
//              OR subject.classHours LIKE ? OR subject.factor LIKE ?)
		if (searchKey != null) {
			sql.append(" (subject.code like :searchKey");
			sql.append(" or subject.name like :searchKey");
			sql.append(" or subject.classHours like :searchKey");
			sql.append(" or subject.factor like :searchKey)");
		}

//        ORDER BY ? ?
		if (sorter != null && sorter.getSortBy() != null && sorter.getSortName() != null) {
			sql.append(" order by " + sorter.getSortBy() + " " + sorter.getSortName());
		}

		return sql.toString();
	}

	@Override
	public String generateHQLForFind(Pageable<Subject> pageable) {
//        SELECT * FROM Subject subject
//        WHERE subject.code=? AND subject.name=? 
//              AND subject.classHours=? AND subject.factor=?
//              AND subject.isDeleted=?
//        AND (subject.code LIKE ? OR subject.name LIKE ? 
//              OR subject.classHours LIKE ? OR subject.factor LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

		// SELECT * FROM Subject subject
		String hql = "from Subject subject";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		return hql;
	}

	@Override
	public String generateHQLForCount(Pageable<Subject> pageable) {
//        SELECT COUNT(*) FROM Subject subject
//        WHERE subject.code=? AND subject.name=? 
//              AND subject.classHours=? AND subject.factor=?
//              AND subject.isDeleted=?
//        AND (subject.code LIKE ? OR subject.name LIKE ? 
//              OR subject.classHours LIKE ? OR subject.factor LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

		// SELECT COUNT(*) FROM Subject subject
		String hql = "select count(*) from Subject subject";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		// test
		System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public Query setValueForHQL(Pageable<Subject> pageable, Session session, String hql) {
		Query query = session.createQuery(hql);
		Subject subject = pageable.getFilterModel();

		if (subject != null) {
			if (subject.getCode() != null) {
				query.setParameter("code", subject.getCode(), StringType.INSTANCE);
			}
			if (subject.getName() != null) {
				query.setParameter("name", subject.getName(), StringNVarcharType.INSTANCE);
			}
			if (subject.getClassHours() != null) {
				query.setParameter("classHours", subject.getClassHours());
			}
			if (subject.getFactor() != null) {
				query.setParameter("factor", subject.getFactor());
			}
			if (subject.getIsDeleted() != null) {
				query.setParameter("isDeleted", subject.getIsDeleted());
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
	public Subject findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}