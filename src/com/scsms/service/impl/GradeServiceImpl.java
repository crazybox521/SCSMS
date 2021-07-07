package com.scsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.GradeMapper;
import com.scsms.pojo.Grade;
import com.scsms.service.GradeService;




@Repository
public class GradeServiceImpl implements  GradeService {
	
	
	@Autowired
	private  GradeMapper mapper;


	@Override
	public int insert(Grade obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Grade obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Grade> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Grade> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		return mapper.queryPage(map);
	}

	@Override
	public Grade queryObject(int id) {
		// TODO Auto-generated method stub
		return mapper.queryObject(id);
	}

	@Override
	public List<Grade> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}

	@Override
	public List<Grade> queryByLessonid(int lessonid) {
		// TODO Auto-generated method stub
		return mapper.queryByLessonid(lessonid);
	}

	@Override
	public int queryCountByLessonid(int lessonid) {
		// TODO Auto-generated method stub
		return mapper.queryCountByLessonid(lessonid);
	}

	@Override
	public int chooseLesson(int studentid, int gradeid) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("studentid", studentid);
		map.put("gradeid", gradeid);
		return mapper.chooseLesson(map);
	}

	@Override
	public List<Grade> queryByStudentid(int Studentid) {
		// TODO Auto-generated method stub
		return mapper.queryByStudentid(Studentid);
	}

	@Override
	public int queryCountByStudentid(int studentid) {
		// TODO Auto-generated method stub
		return mapper.queryCountByStudentid(studentid);
	}

	@Override
	public List<Grade> tea_queryPage(int offset,int size,int id) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("id", id);
		return mapper.tea_queryPage(map);
	}

	@Override
	public int queryCountByTeacherid(int teacherid) {
		// TODO Auto-generated method stub
		return mapper.queryCountByTeacherid(teacherid);
	}

	@Override
	public List<Grade> queryAllByTeacherid(int tercherid) {
		// TODO Auto-generated method stub
		return mapper.queryAllByTeacherid(tercherid);
	}

	@Override
	public int setjieshu(int id) {
		// TODO Auto-generated method stub
		return mapper.setjieshu(id);
	}

	@Override
	public int queryCountByGradeid(int gradeid) {
		// TODO Auto-generated method stub
		return mapper.queryCountByGradeid(gradeid);
	}
	
	


}
