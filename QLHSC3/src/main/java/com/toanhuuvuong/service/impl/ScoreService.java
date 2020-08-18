package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.SchoolClassDAO;
import com.toanhuuvuong.dao.impl.ScoreDAO;
import com.toanhuuvuong.dao.impl.ScoreTypeDAO;
import com.toanhuuvuong.dao.impl.StudentOfClassDAO;
import com.toanhuuvuong.dao.impl.SubjectDAO;
import com.toanhuuvuong.model.Performance;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Score;
import com.toanhuuvuong.model.ScoreType;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.model.StudentOfClass;
import com.toanhuuvuong.model.Subject;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IScoreService;

public class ScoreService extends GenericService<Score> implements IScoreService
{ 
	private ScoreDAO scoreDAO = new ScoreDAO();
	private ScoreTypeDAO scoreTypeDAO = new ScoreTypeDAO();
	private StudentOfClassDAO studentOfClassDAO = new StudentOfClassDAO();
	private SubjectDAO subjectDAO = new SubjectDAO();
	private PerformanceService performanceService = new PerformanceService();
	private SchoolClassDAO schoolClassDAO = new SchoolClassDAO();
	
	@Override
	public Map<String, String> validate(Score model)
	{
		Map<String, String> map = new Hashtable<String, String>();
		
		if(model == null)
		{
			map.put("messageCode", "code_existed");
			map.put("alert", "danger");
		}
		else
		{
			map.put("messageCode", "insert_success");
			map.put("alert", "success");
		}
	
		return map;
	}
	@Override
	public AbstractHibernateDAO<Score> getDAO() 
	{
		return scoreDAO;
	}
	@Override
	public List<Score> findByScoreTypeOfStudent(ScoreType scoreType, Student student, Semester semester, 
			SchoolYear schoolYear, SchoolClass schoolClass, Subject subject)
	{
		Score filter = new Score();
		filter.setStudent(student);
		filter.setScoreType(scoreType);
		filter.setSubject(subject);
		filter.setSemester(semester);
		filter.setSchoolYear(schoolYear);
		filter.setSchoolClass(schoolClass);
		
		Pageable<Score> pageable = new PageRequest<Score>(null, null, null, null, filter);
		
		List<Score> scores = scoreDAO.find(pageable);
		
		return scores;
	}
	@Override
	public Integer countByPerformanceOfSchoolClass(Performance performance, Semester semester, SchoolYear schoolYear,
			SchoolClass schoolClass) 
	{
		Integer count = 0;
		
		StudentOfClass filter = new StudentOfClass();
		filter.setSemester(semester);
		filter.setSchoolYear(schoolYear);
		filter.setSchoolClass(schoolClass);
		
		List<StudentOfClass> studentsOfClass = studentOfClassDAO.find(new PageRequest<StudentOfClass>(null, null, null, null, filter));
		
		Student student;
		Float avg;
		Performance tempPerformance;
		for(StudentOfClass studentOfClass : studentsOfClass)
		{
			student= studentOfClass.getStudent();
			avg = calculateAvg(student, semester, schoolYear, schoolClass);
			tempPerformance = performanceService.generateFromAvg(avg);
			if(tempPerformance.getCode().equals(performance.getCode()))
				count++;
		}
		
		return count;
	}
	@Override
	public Float calculateSubjectAvg(Student student, Semester semester, SchoolYear schoolYear, 
			SchoolClass schoolClass, Subject subject)
	{
		Float avg = 0f;
		
		Score filter = new Score();
		filter.setStudent(student);
		filter.setSubject(subject);
		filter.setSemester(semester);
		filter.setSchoolYear(schoolYear);
		filter.setSchoolClass(schoolClass);
		
		Pageable<Score> pageable = new PageRequest<Score>(null, null, null, null, filter);
		List<Score> scores = scoreDAO.find(pageable);
	
		List<ScoreType> scoreTypes = scoreTypeDAO.findAll();
		
		int count;
		int sum = 0;
		for(ScoreType type : scoreTypes)
		{
			count = 0;
			for(Score score : scores)
			{
				if(type.getId() == score.getScoreType().getId())
				{
					count++;
					if(score.getValue() != null)
						avg += score.getValue() * type.getFactor();
				}
			}
			sum += count * type.getFactor();
		}
		
		if(sum != 0)
			avg = avg / sum;
		
		return avg;
	}
	@Override
	public Float calculateAvg(Student student, Semester semester, SchoolYear schoolYear, SchoolClass schoolClass)
	{
		Float avg = 0f;
		
		List<Subject> subjects = subjectDAO.findAll();
		
		int sum = 0;
		for(Subject subject : subjects)
		{
			avg += calculateSubjectAvg(student, semester, schoolYear, schoolClass, subject) * subject.getFactor();
			sum += subject.getFactor();
		}
		
		if(sum != 0)
			avg = avg / sum;
		
		return avg;
	}
	@Override
	public List<Score> generateScoreGroupByStudentFrom(List<Score> scores, Score filterModel)
	{
		StudentOfClass filter = new StudentOfClass();
		filter.setSemester(filterModel.getSemester());
		filter.setSchoolYear(filterModel.getSchoolYear());
		filter.setSchoolClass(filterModel.getSchoolClass());
		
		List<StudentOfClass> studentsOfClass = studentOfClassDAO.find(new PageRequest<StudentOfClass>(null, null, null, null, filter));
		
		boolean isExisted;
		Score score;
		for(StudentOfClass studentOfClass : studentsOfClass)
		{
			isExisted = false;
			for(int i = 0; i < scores.size(); i++)
			{
				score = scores.get(i);
				if(studentOfClass.getStudent().getId() == score.getStudent().getId())
				{
					if(isExisted)
					{
						scores.remove(score);
						i--;
					}
					else
						isExisted = true;
				}
			}
		}
		
		return scores;
	}
	@Override
	public List<Score> generateScoreGroupBySubjectFrom(List<Score> scores, Score filterModel)
	{
		List<Subject> subjects = subjectDAO.findAll();
		
		boolean isExisted;
		Score score;
		for(Subject subject : subjects)
		{
			isExisted = false;
			for(int i = 0; i < scores.size(); i++)
			{
				score = scores.get(i);
				if(subject.getId() == score.getSubject().getId())
				{
					if(isExisted)
					{
						scores.remove(score);
						i--;
					}
					else
						isExisted = true;
				}
			}
		}
		
		return scores;
	}
	@Override
	public List<Score> generateScoreGroupBySchoolClassFrom(List<Score> scores, Score filterModel)
	{
		List<SchoolClass> schoolClasses = schoolClassDAO.findAll();
		
		boolean isExisted;
		Score score;
		for(SchoolClass schoolClass : schoolClasses)
		{
			isExisted = false;
			for(int i = 0; i < scores.size(); i++)
			{
				score = scores.get(i);
				if(schoolClass.getId() == score.getSchoolClass().getId())
				{
					if(isExisted)
					{
						scores.remove(score);
						i--;
					}
					else
						isExisted = true;
				}
			}
		}
		
		return scores;
	}
	@Override
	public String generateScoreFrom(List<Score> scores)
	{
		StringBuilder scoreRow = new StringBuilder();
		for(Score score : scores)
			scoreRow.append(score.getValue().toString() + "   ");
		
		return scoreRow.toString();
	}
	@Override
	public Score findByOrdinalNumberOfScoreTypeOfStudent(Integer ordinalNumber, ScoreType scoreType, Student student,
			Semester semester, SchoolYear schoolYear, SchoolClass schoolClass, Subject subject) 
	{
		Score filter = new Score();
		filter.setStudent(student);
		filter.setScoreType(scoreType);
		filter.setSubject(subject);
		filter.setSemester(semester);
		filter.setSchoolYear(schoolYear);
		filter.setSchoolClass(schoolClass);
		filter.setOrdinalNumber(ordinalNumber);
		
		Pageable<Score> pageable = new PageRequest<Score>(null, null, null, null, filter);
		
		List<Score> scores = scoreDAO.find(pageable);
		
		return (scores != null && !scores.isEmpty()) ? scores.get(0) : null;
	}
}
