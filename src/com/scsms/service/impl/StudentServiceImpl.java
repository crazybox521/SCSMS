package com.scsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.StudentMapper;
import com.scsms.pojo.Student;
import com.scsms.service.StudentService;




@Repository
public class StudentServiceImpl implements StudentService {
	
	
	@Autowired
	private StudentMapper mapper;


	@Override
	public int insert(Student obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Student obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Student> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Student> queryPage(int offset, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		return mapper.queryPage(map);
	}

	@Override
	public Student queryObject(int userid) {
		// TODO Auto-generated method stub
		return mapper.queryObject(userid);
	}

	@Override
	public int insert_login(Student obj) {
		// TODO Auto-generated method stub
		return mapper.insert_login(obj);
	}

	@Override
	public List<Student> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}

	@Override
	public List<Student> queryPageGradeStu(int offset, int size,int gradeid) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("gradeid", gradeid);
		return mapper.queryPageGradeStu(map);
	}


}
