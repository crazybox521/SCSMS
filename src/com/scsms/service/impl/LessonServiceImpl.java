package com.scsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.LessonMapper;
import com.scsms.pojo.Lesson;
import com.scsms.service.LessonService;




@Repository
public class LessonServiceImpl implements LessonService {
	
	
	@Autowired
	private LessonMapper mapper;


	@Override
	public int insert(Lesson obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Lesson obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Lesson> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Lesson> queryPage(int offset, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		return mapper.queryPage(map);
	}

	@Override
	public Lesson queryObject(int id) {
		// TODO Auto-generated method stub
		return mapper.queryObject(id);
	}

	@Override
	public List<Lesson> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}


}
