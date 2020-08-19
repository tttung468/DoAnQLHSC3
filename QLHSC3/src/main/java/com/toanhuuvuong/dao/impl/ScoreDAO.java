package com.toanhuuvuong.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.toanhuuvuong.dao.IScoreDAO;
import com.toanhuuvuong.model.Score;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.sort.Sorter;

public class ScoreDAO extends AbstractHibernateDAO<Score> implements IScoreDAO {
	public ScoreDAO() {
		setClazz(Score.class);
	}

	@Override
	public String generateHQL(Pageable<Score> pageable) {
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		Score score = pageable.getFilterModel();
		StringBuilder sql = new StringBuilder();

//        WHERE score.value=? AND score.ordinalNumber=? AND score.scoreType.id=?
//              AND score.subject.id=? AND score.semester.id=? AND score.schoolYear.id=?
//              AND score.student.id=? AND score.schoolClass.id? AND score.isDeleted=?
		if (score != null || searchKey != null) {
			sql.append(" where");
		}
		if (score != null) {
			// value
			if (score.getValue() != null) {
				sql.append(" score.value=:value");
			}
			if (score.getValue() != null && score.getOrdinalNumber() != null) {
				sql.append(" and");
			}

			// ordinalNumber
			if (score.getOrdinalNumber() != null) {
				sql.append(" score.ordinalNumber=:ordinalNumber");
			}
			if (score.getScoreType() != null && (score.getValue() != null || score.getOrdinalNumber() != null)) {
				sql.append(" and");
			}

			// scoreType.id
			if (score.getScoreType() != null) {
				sql.append(" score.scoreType.id=:scoreTypeID");
			}
			if (score.getSubject() != null
					&& (score.getValue() != null || score.getOrdinalNumber() != null || score.getScoreType() != null)) {
				sql.append(" and");
			}

			// score.subject.id
			if (score.getSubject() != null) {
				sql.append(" score.subject.id=:subjectID");
			}
			if (score.getSemester() != null && (score.getValue() != null || score.getOrdinalNumber() != null
					|| score.getScoreType() != null || score.getSubject() != null)) {
				sql.append(" and");
			}

			// score.semester.id
			if (score.getSemester() != null) {
				sql.append(" score.semester.name=:semesterName");
			}
			if (score.getSchoolYear() != null && (score.getValue() != null || score.getOrdinalNumber() != null
					|| score.getScoreType() != null || score.getSubject() != null || score.getSemester() != null)) {
				sql.append(" and");
			}

			// score.schoolYear.id
			if (score.getSchoolYear() != null) {
				sql.append(" score.schoolYear.code=:schoolYearCode");
			}
			if (score.getStudent() != null && score.getStudent().getId() != null
					&& (score.getValue() != null || score.getOrdinalNumber() != null || score.getScoreType() != null
							|| score.getSubject() != null || score.getSemester() != null
							|| score.getSchoolYear() != null)) {
				sql.append(" and");
			}

			// score.student.id
			if (score.getStudent() != null && score.getStudent().getId() != null) {
				sql.append(" score.student.id=:studentID");
			}
			if (score.getStudent() != null && score.getStudent().getCode() != null
					&& (score.getValue() != null || score.getOrdinalNumber() != null || score.getScoreType() != null
							|| score.getSubject() != null || score.getSemester() != null
							|| score.getSchoolYear() != null
							|| score.getStudent() != null && score.getStudent().getId() != null)) {
				sql.append(" and");
			}

			// score.student.code
			if (score.getStudent() != null && score.getStudent().getCode() != null) {
				sql.append(" score.student.code=:studentCode");
			}
			if (score.getStudent() != null && score.getStudent().getName() != null
					&& (score.getValue() != null || score.getOrdinalNumber() != null || score.getScoreType() != null
							|| score.getSubject() != null || score.getSemester() != null
							|| score.getSchoolYear() != null || (score.getStudent() != null
									&& (score.getStudent().getId() != null || score.getStudent().getCode() != null)))) {
				sql.append(" and");
			}

			// score.student.name
			if (score.getStudent() != null && score.getStudent().getName() != null) {
				sql.append(" score.student.name=:studentName");
			}
			if (score.getStudent() != null && score.getStudent().getGender() != null && (score.getValue() != null
					|| score.getOrdinalNumber() != null || score.getScoreType() != null || score.getSubject() != null
					|| score.getSemester() != null || score.getSchoolYear() != null
					|| (score.getStudent() != null && (score.getStudent().getId() != null
							|| score.getStudent().getCode() != null || score.getStudent().getName() != null)))) {
				sql.append(" and");
			}

			// score.student.gender
			if (score.getStudent() != null && score.getStudent().getGender() != null) {
				sql.append(" score.student.gender=:studentGender");
			}
			if (score.getStudent() != null && score.getStudent().getStatus() != null
					&& (score.getValue() != null || score.getOrdinalNumber() != null || score.getScoreType() != null
							|| score.getSubject() != null || score.getSemester() != null
							|| score.getSchoolYear() != null
							|| (score.getStudent() != null && (score.getStudent().getId() != null
									|| score.getStudent().getCode() != null || score.getStudent().getName() != null
									|| score.getStudent().getGender() != null)))) {
				sql.append(" and");
			}

			// score.student.status
			if (score.getStudent() != null && score.getStudent().getStatus() != null) {
				sql.append(" score.student.status=:studentStatus");
			}
			if (score.getSchoolClass() != null && score.getSchoolClass().getId() != null && (score.getValue() != null
					|| score.getOrdinalNumber() != null || score.getScoreType() != null || score.getSubject() != null
					|| score.getSemester() != null || score.getSchoolYear() != null
					|| score.getStudent().getId() != null
					|| (score.getStudent() != null && (score.getStudent().getId() != null
							|| score.getStudent().getCode() != null || score.getStudent().getName() != null
							|| score.getStudent().getGender() != null || score.getStudent().getStatus() != null)))) {
				sql.append(" and");
			}
			// score.schoolClass.id
			if (score.getSchoolClass() != null && score.getSchoolClass().getId() != null) {
				sql.append(" score.schoolClass.id=:schoolClassID");
			}
			if (score.getSchoolClass() != null && score.getSchoolClass().getGrade().getName() != null
					&& (score.getValue() != null || score.getOrdinalNumber() != null || score.getScoreType() != null
							|| score.getSubject() != null || score.getSemester() != null
							|| score.getSchoolYear() != null
							|| (score.getSchoolClass() != null && score.getSchoolClass().getId() != null)
							|| (score.getStudent() != null && (score.getStudent().getId() != null
									|| score.getStudent().getCode() != null || score.getStudent().getName() != null
									|| score.getStudent().getGender() != null
									|| score.getStudent().getStatus() != null)))) {
				sql.append(" and");
			}
			// score.schoolClass.grade.name
			if (score.getSchoolClass() != null && score.getSchoolClass().getGrade().getName() != null) {
				sql.append(" score.schoolClass.grade.name=:gradeName");
			}
			if (score.getIsDeleted() != null && (score.getValue() != null || score.getOrdinalNumber() != null
					|| score.getScoreType() != null || score.getSubject() != null || score.getSemester() != null
					|| score.getSchoolYear() != null
					|| (score.getSchoolClass() != null && (score.getSchoolClass().getId() != null
							|| score.getSchoolClass().getGrade().getName() != null))
					|| (score.getStudent() != null && (score.getStudent().getId() != null
							|| score.getStudent().getCode() != null || score.getStudent().getName() != null
							|| score.getStudent().getGender() != null || score.getStudent().getStatus() != null)))) {
				sql.append(" and");
			}
			// score.isDeleted
			if (score.getIsDeleted() != null) {
				sql.append(" score.isDeleted=:isDeleted");
			}
			if (searchKey != null && (score.getValue() != null || score.getOrdinalNumber() != null
					|| score.getScoreType() != null || score.getSubject() != null || score.getSemester() != null
					|| score.getSchoolYear() != null
					|| (score.getSchoolClass() != null && (score.getSchoolClass().getId() != null
							|| score.getSchoolClass().getGrade().getName() != null))
					|| score.getIsDeleted() != null
					|| (score.getStudent() != null && (score.getStudent().getId() != null
							|| score.getStudent().getCode() != null || score.getStudent().getName() != null
							|| score.getStudent().getGender() != null || score.getStudent().getStatus() != null)))) {
				sql.append(" and");
			}

		}

//        AND ( score.value LIKE ? OR score.ordinalNumber LIKE ?
//              OR score.scoreType.id LIKE ? OR score.subject.id LIKE ? OR score.semester.id LIKE ?
//              OR score.schoolYear.id LIKE ? OR score.student.id LIKE ? OR score.schoolClass.id LIKE ? )
		if (searchKey != null) {
			sql.append(" (score.value like :searchKey");
			sql.append(" or score.student.code like :searchKey");
			sql.append(" or score.student.name like :searchKey");
			sql.append(" or score.student.gender like :searchKey");
			sql.append(" or score.student.status like :searchKey)");
		}

//        ORDER BY ? ?
		if (sorter != null && sorter.getSortBy() != null && sorter.getSortName() != null) {
			sql.append(" order by " + sorter.getSortBy() + " " + sorter.getSortName());
		}

		return sql.toString();
	}

	@Override
	public String generateHQLForFind(Pageable<Score> pageable) {
//        SELECT * FROM Score score
//        WHERE score.value=? AND score.value=? AND score.ordinalNumber=?
//              AND score.scoreType.id=? AND score.subject.id=? AND score.semester.id=? AND score.schoolYear.id=?
//              AND score.student.id=? AND score.schoolClass.id? AND score.isDeleted=?
//        AND ( score.value LIKE ? OR score.value LIKE ? OR score.ordinalNumber LIKE ?
//              OR score.scoreType.id LIKE ? OR score.subject.id LIKE ? OR score.semester.id LIKE ?
//              OR score.schoolYear.id LIKE ? OR score.student.id LIKE ? OR score.schoolClass.id LIKE ? )
//        ORDER BY ? ?
//        LIMIT ?, ?

		// Dòng 1: SELECT * FROM SchoolClass
		String hql = " from Score score";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public String generateHQLForCount(Pageable<Score> pageable) {
//        SELECT COUNT(*) FROM Score
//        WHERE score.value=? AND score.value=? AND score.ordinalNumber=?
//              AND score.scoreType.id=? AND score.subject.id=? AND score.semester.id=? AND score.schoolYear.id=?
//              AND score.student.id=? AND score.schoolClass.id? AND score.isDeleted=?
//        AND ( score.value LIKE ? OR score.value LIKE ? OR score.ordinalNumber LIKE ?
//              OR score.scoreType.id LIKE ? OR score.subject.id LIKE ? OR score.semester.id LIKE ?
//              OR score.schoolYear.id LIKE ? OR score.student.id LIKE ? OR score.schoolClass.id LIKE ? )
//        ORDER BY ? ?
//        LIMIT ?, ?

		// Dòng 1: SELECT * FROM Score
		String hql = " select count(*) from Score score";

		// Các dòng còn lại
		hql += generateHQL(pageable);

		// test
		// System.out.println("\n" + hql);

		return hql;
	}

	@Override
	public Query setValueForHQL(Pageable<Score> pageable, Session session, String hql) {
		Query query = session.createQuery(hql);
		Score score = pageable.getFilterModel();

		if (score != null) {
			if (score.getValue() != null) {
				query.setParameter("value", score.getValue());
			}
			if (score.getOrdinalNumber() != null) {
				query.setParameter("ordinalNumber", score.getOrdinalNumber());
			}
			if (score.getScoreType() != null) {
				query.setParameter("scoreTypeID", score.getScoreType().getId());
			}
			if (score.getSubject() != null) {
				query.setParameter("subjectID", score.getSubject().getId());
			}
			if (score.getSemester() != null) {
				query.setParameter("semesterName", score.getSemester().getName());
			}
			if (score.getSchoolYear() != null) {
				query.setParameter("schoolYearCode", score.getSchoolYear().getCode());
			}
			if (score.getStudent() != null && score.getStudent().getId() != null) {
				query.setParameter("studentID", score.getStudent().getId());
			}
			if (score.getStudent() != null && score.getStudent().getCode() != null) {
				query.setParameter("studentCode", score.getStudent().getCode());
			}
			if (score.getStudent() != null && score.getStudent().getName() != null) {
				query.setParameter("studentName", score.getStudent().getName());
			}
			if (score.getStudent() != null && score.getStudent().getGender() != null) {
				query.setParameter("studentGender", score.getStudent().getGender());
			}
			if (score.getStudent() != null && score.getStudent().getStatus() != null) {
				query.setParameter("studentStatus", score.getStudent().getStatus());
			}
			if (score.getSchoolClass() != null && score.getSchoolClass().getId() != null) {
				query.setParameter("schoolClassID", score.getSchoolClass().getId());
			}
			if (score.getSchoolClass() != null && score.getSchoolClass().getGrade().getName() != null) {
				query.setParameter("gradeName", score.getSchoolClass().getGrade().getName());
			}
			if (score.getIsDeleted() != null) {
				query.setParameter("isDeleted", score.getIsDeleted());
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
