package com.scsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.MarkMapper;
import com.scsms.pojo.Mark;
import com.scsms.service.MarkService;




@Repository
public class MarkServiceImpl implements MarkService {
	
	
	@Autowired
	private MarkMapper mapper;


	@Override
	public int insert(Mark obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Mark obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Mark> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Mark> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mark queryObject(int id) {
		// TODO Auto-generated method stub
		return mapper.queryObject(id);
	}

	@Override
	public List<Mark> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}

	@Override
	public List<Mark> queryPageM(int offset, int size, int gradeid) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("gradeid", gradeid);
		return mapper.queryPageM(map);
		// TODO Auto-generated method stub
	}

	@Override
	public int queryCountByStudent(int studentid) {
		// TODO Auto-generated method stub
		return mapper.queryCountByStudent(studentid);
	}

	@Override
	public List<Mark> queryPageByStudent(int offset, int size, int studentid) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("studentid", studentid);
		return mapper.queryPageByStudent(map);
	}


}
