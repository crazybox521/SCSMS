package com.scsms.service;

import java.util.List;

import com.scsms.pojo.Teacher;

public interface TeacherService extends BaseService<Teacher> {
	int insert_login(Teacher obj);
	int update_lessonid(Teacher obj);
	List<Teacher> queryLeAll(int lessonid);
	int queryCountLes(int lessonid);
}
