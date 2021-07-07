package com.scsms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.RoomstateMapper;
import com.scsms.pojo.Roomstate;
import com.scsms.service.RoomstateService;




@Repository
public class RoomstateServiceImpl implements RoomstateService {
	
	
	@Autowired
	private RoomstateMapper mapper;


	@Override
	public int insert(Roomstate obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Roomstate obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Roomstate> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Roomstate> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Roomstate queryObject(int id) {
		// TODO Auto-generated method stub
		return mapper.queryObject(id);
	}

	@Override
	public List<Roomstate> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}


}
