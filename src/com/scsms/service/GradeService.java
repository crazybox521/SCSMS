package com.scsms.service;


import java.util.List;

import com.scsms.pojo.Grade;

public interface GradeService extends BaseService<Grade> {
	List<Grade> queryByLessonid(int lessonid);
	int queryCountByLessonid(int lessonid);
	int chooseLesson(int studentid,int gradeid);
	List<Grade> queryByStudentid(int Studentid);
	int queryCountByStudentid(int studentid);
	List<Grade> tea_queryPage(int offset, int size, int id);
	int queryCountByTeacherid(int teacherid);
	List<Grade> queryAllByTeacherid(int tercherid);
	int setjieshu(int id);
	int queryCountByGradeid(int gradeid);
}
