package com.toanhuuvuong.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringNVarcharType;
import org.hibernate.type.StringType;

import com.toanhuuvuong.dao.IStudentDAO;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.sort.Sorter;

public class StudentDAO extends AbstractHibernateDAO<Student> implements IStudentDAO {
	public StudentDAO() {
		setClazz(Student.class);
	}

	@Override
	public String generateHQL(Pageable<Student> pageable) {
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		Student student = pageable.getFilterModel();
		StringBuilder sql = new StringBuilder();

//        WHERE student.code=? AND student.name=? AND student.phone = ? AND student.identifyCard=?
//              AND student.email=? AND student.avatarpath=? AND student.gender=? AND student.birth=?
//              AND student.address=? AND student.status=? AND student.ethnic.name=? AND student.religion.name=? 
//              AND student.nationality.name=? AND student.isDeleted=?       
		if (student != null || searchKey != null) {
			sql.append(" where");
		}
		if (student != null) {
			// student.code=?
			if (student.getCode() != null) {
				sql.append(" student.code = :code");
			}
			if (student.getName() != null && student.getCode() != null) {
				sql.append(" and");
			}

			// student.name=?
			if (student.getName() != null) {
				sql.append(" student.name = :name");
			}
			if (student.getPhone() != null && (student.getCode() != null || student.getName() != null)) {
				sql.append(" and");
			}

			// student.phone = ?
			if (student.getPhone() != null) {
				sql.append(" student.phone = :phone");
			}
			if (student.getIdentifyCard() != null
					&& (student.getCode() != null || student.getName() != null || student.getPhone() != null)) {
				sql.append(" and");
			}

			// student.identifyCard=?
			if (student.getIdentifyCard() != null) {
				sql.append(" student.identifyCard = :identifyCard");
			}
			if (student.getEmail() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null)) {
				sql.append(" and");
			}

			// student.email=?
			if (student.getEmail() != null) {
				sql.append(" student.email = :email");
			}
			if (student.getAvatarpath() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null)) {
				sql.append(" and");
			}

			// student.avatarpath=?
			if (student.getAvatarpath() != null) {
				sql.append(" student.avatarpath = :avatarpath");
			}
			if (student.getGender() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null
					|| student.getAvatarpath() != null)) {
				sql.append(" and");
			}

			// student.gender=?
			if (student.getGender() != null) {
				sql.append(" student.gender = :gender");
			}
			if (student.getBirth() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null
					|| student.getAvatarpath() != null || student.getGender() != null)) {
				sql.append(" and");
			}

			// student.birth=?
			if (student.getBirth() != null) {
				sql.append(" student.birth = :birth");
			}
			if (student.getAddress() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null
					|| student.getAvatarpath() != null || student.getGender() != null || student.getBirth() != null)) {
				sql.append(" and");
			}

			// student.address=?
			if (student.getAddress() != null) {
				sql.append(" student.address = :address");
			}
			if (student.getStatus() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null
					|| student.getAvatarpath() != null || student.getGender() != null || student.getBirth() != null
					|| student.getAddress() != null)) {
				sql.append(" and");
			}

			// student.status=?
			if (student.getStatus() != null) {
				sql.append(" student.status = :status");
			}
			if (student.getEthnic() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null
					|| student.getAvatarpath() != null || student.getGender() != null || student.getBirth() != null
					|| student.getAddress() != null || student.getStatus() != null)) {
				sql.append(" and");
			}

			// student.ethnic.id=?
			if (student.getEthnic() != null) {
				sql.append(" student.ethnic.name = :ethnicName");
			}
			if (student.getReligion() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null
					|| student.getAvatarpath() != null || student.getGender() != null || student.getBirth() != null
					|| student.getAddress() != null || student.getStatus() != null || student.getEthnic() != null)) {
				sql.append(" and");
			}

			// student.religion.id=?
			if (student.getReligion() != null) {
				sql.append(" student.religion.name = :religionName");
			}
			if (student.getNationality() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null
					|| student.getAvatarpath() != null || student.getGender() != null || student.getBirth() != null
					|| student.getAddress() != null || student.getStatus() != null || student.getEthnic() != null
					|| student.getReligion() != null)) {
				sql.append(" and");
			}

			// student.nationality.id=?
			if (student.getNationality() != null) {
				sql.append(" student.nationality.name = :nationalityName");
			}
			if (student.getIsDeleted() != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null
					|| student.getAvatarpath() != null || student.getGender() != null || student.getBirth() != null
					|| student.getAddress() != null || student.getStatus() != null || student.getEthnic() != null
					|| student.getReligion() != null || student.getNationality() != null)) {
				sql.append(" and");
			}

			// student.isDeleted=?
			if (student.getIsDeleted() != null) {
				sql.append(" student.isDeleted = :isDeleted");
			}
			if (searchKey != null && (student.getCode() != null || student.getName() != null
					|| student.getPhone() != null || student.getIdentifyCard() != null || student.getEmail() != null
					|| student.getAvatarpath() != null || student.getGender() != null || student.getBirth() != null
					|| student.getAddress() != null || student.getStatus() != null || student.getEthnic() != null
					|| student.getReligion() != null || student.getNationality() != null
					|| student.getIsDeleted() != null)) {
				sql.append(" and");
			}
		}

//        AND (student.code LIKE ? OR student.name LIKE ? OR student.phone LIKE ? OR student.identifyCard LIKE ?
//              OR student.email LIKE ? OR student.avatarpath LIKE ? OR student.gender LIKE ? OR student.birth LIKE ?
//              OR student.address LIKE ? OR student.status LIKE ? OR student.ethnic.id LIKE ?
//              OR student.religion.id LIKE ? OR student.nationality.id LIKE ?)
		if (searchKey != null) {
			sql.append(" (student.code like :searchKey");
			sql.append(" or student.name like :searchKey");
			sql.append(" or student.phone like :searchKey");
			sql.append(" or student.identifyCard like :searchKey");
			sql.append(" or student.email like :searchKey");
			sql.append(" or student.avatarpath like :searchKey");
			sql.append(" or student.gender like :searchKey");
			sql.append(" or student.birth like :searchKey");
			sql.append(" or student.address like :searchKey");
			sql.append(" or student.status like :searchKey");
			sql.append(" or student.ethnic.name like :searchKey");
			sql.append(" or student.religion.name like :searchKey");
			sql.append(" or student.nationality.name like :searchKey)");
		}

		// ORDER BY ?, ?
		if (sorter != null && sorter.getSortBy() != null && sorter.getSortName() != null) {
			sql.append(" order by " + sorter.getSortBy() + " " + sorter.getSortName());
		}

		return sql.toString();
	}

	@Override
	public String generateHQLForFind(Pageable<Student> pageable) {
//        SELECT * FROM Student student 
//        WHERE student.code=? AND student.name=? AND student.phone = ? AND student.identifyCard=?
//              AND student.email=? AND student.avatarpath=? AND student.gender=? AND student.birth=?
//              AND student.address=? AND student.status=? AND student.ethnic.id=? AND student.religion.id=? 
//              AND student.nationality.id=? AND student.isDeleted=?
//        AND (student.code LIKE ? OR student.name LIKE ? OR student.phone LIKE ? OR student.identifyCard LIKE ?
//              OR student.email LIKE ? OR student.avatarpath LIKE ? OR student.gender LIKE ? OR student.birth LIKE ?
//              OR student.address LIKE ? OR student.status LIKE ? OR student.ethnic.id LIKE ?
//              OR student.religion.id LIKE ? OR student.nationality.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

		// Dòng 1: SELECT * FROM Student student
		String hql = "from Student student";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public String generateHQLForCount(Pageable<Student> pageable) {
//        SELECT COUNT(*) FROM Student student 
//        WHERE student.code=? AND student.name=? AND student.phone = ? AND student.identifyCard=?
//              AND student.email=? AND student.avatarpath=? AND student.gender=? AND student.birth=?
//              AND student.address=? AND student.status=? AND student.ethnic.id=? AND student.religion.id=? 
//              AND student.nationality.id=? AND student.isDeleted=?
//        AND (student.code LIKE ? OR student.name LIKE ? OR student.phone LIKE ? OR student.identifyCard LIKE ?
//              OR student.email LIKE ? OR student.avatarpath LIKE ? OR student.gender LIKE ? OR student.birth LIKE ?
//              OR student.address LIKE ? OR student.status LIKE ? OR student.ethnic.id LIKE ?
//              OR student.religion.id LIKE ? OR student.nationality.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

		// Dòng 1: SELECT COUNT(*) FROM Student student
		String hql = "select count(*) from Student student";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public Query setValueForHQL(Pageable<Student> pageable, Session session, String hql) {
		Query query = session.createQuery(hql);
		Student student = pageable.getFilterModel();

		if (student != null) {
			if (student.getCode() != null) {
				query.setParameter("code", student.getCode(), StringType.INSTANCE);
			}
			if (student.getName() != null) {
				query.setParameter("name", student.getName(), StringNVarcharType.INSTANCE);
			}
			if (student.getPhone() != null) {
				query.setParameter("phone", student.getPhone(), StringType.INSTANCE);
			}
			if (student.getIdentifyCard() != null) {
				query.setParameter("identifyCard", student.getIdentifyCard());
			}
			if (student.getEmail() != null) {
				query.setParameter("email", student.getEmail());
			}
			if (student.getAvatarpath() != null) {
				query.setParameter("avatarpath", student.getAvatarpath());
			}
			if (student.getGender() != null) {
				query.setParameter("gender", student.getGender());
			}
			if (student.getBirth() != null) {
				query.setParameter("birth", student.getBirth());
			}
			if (student.getAddress() != null) {
				query.setParameter("address", student.getAddress(), StringNVarcharType.INSTANCE);
			}
			if (student.getStatus() != null) {
				query.setParameter("status", student.getStatus(), StringNVarcharType.INSTANCE);
			}
			if (student.getEthnic() != null) {
				query.setParameter("ethnicName", student.getEthnic().getName(), StringNVarcharType.INSTANCE);
			}
			if (student.getReligion() != null) {
				query.setParameter("religionName", student.getReligion().getName(), StringNVarcharType.INSTANCE);
			}
			if (student.getNationality() != null) {
				query.setParameter("nationalityName", student.getNationality().getName(), StringNVarcharType.INSTANCE);
			}
			if (student.getIsDeleted() != null) {
				query.setParameter("isDeleted", student.getIsDeleted());
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
	public Student findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
}
