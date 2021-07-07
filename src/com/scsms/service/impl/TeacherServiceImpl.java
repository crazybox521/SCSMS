package com.scsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.TeacherMapper;
import com.scsms.pojo.Teacher;
import com.scsms.service.TeacherService;




@Repository
public class TeacherServiceImpl implements TeacherService {
	
	
	@Autowired
	private TeacherMapper mapper;


	@Override
	public int insert(Teacher obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Teacher obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Teacher> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Teacher> queryPage(int offset, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		return mapper.queryPage(map);
	}

	@Override
	public Teacher queryObject(int userid) {
		// TODO Auto-generated method stub
		return mapper.queryObject(userid);
	}

	@Override
	public int insert_login(Teacher obj) {
		// TODO Auto-generated method stub
		return mapper.insert_login(obj);
	}

	@Override
	public int update_lessonid(Teacher obj) {
		// TODO Auto-generated method stub
		return mapper.update_lessonid(obj);
	}

	@Override
	public List<Teacher> queryLeAll(int lessonid) {
		// TODO Auto-generated method stub
		return mapper.queryLeAll(lessonid);
	}

	@Override
	public int queryCountLes(int lessonid) {
		// TODO Auto-generated method stub
		return mapper.queryCountLes(lessonid);
	}

	@Override
	public List<Teacher> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}




}
