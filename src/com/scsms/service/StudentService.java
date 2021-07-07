package com.scsms.service;

import java.util.List;

import com.scsms.pojo.Student;

public interface StudentService extends BaseService<Student> {
	int insert_login(Student obj);
	List<Student> queryPageGradeStu(int offset, int size,int gradeid);

}
