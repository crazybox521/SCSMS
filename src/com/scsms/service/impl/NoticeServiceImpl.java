package com.scsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.NoticeMapper;
import com.scsms.pojo.Notice;
import com.scsms.service.NoticeService;




@Repository
public class NoticeServiceImpl implements NoticeService {
	
	
	@Autowired
	private NoticeMapper mapper;


	@Override
	public int insert(Notice obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Notice obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Notice> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Notice> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice queryObject(int id) {
		// TODO Auto-generated method stub
		return mapper.queryObject(id);
	}

	@Override
	public List<Notice> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}

	@Override
	public List<Notice> queryPageN(int offset,int size,int gradeid) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("gradeid", gradeid);
		return mapper.queryPageN(map);
	}

	@Override
	public int queryCountByGrade(int gradeid) {
		// TODO Auto-generated method stub
		return mapper.queryCountByGrade(gradeid);
	}

	@Override
	public List<Notice> queryByStudent(int studentid) {
		// TODO Auto-generated method stub
		return mapper.queryByStudent(studentid);
	}


}
