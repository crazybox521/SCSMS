package com.scsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.RoomMapper;
import com.scsms.pojo.Room;
import com.scsms.service.RoomService;




@Repository
public class RoomServiceImpl implements RoomService {
	
	
	@Autowired
	private RoomMapper mapper;


	@Override
	public int insert(Room obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Room obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Room> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Room> queryPage(int offset, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		return mapper.queryPage(map);
	}

	@Override
	public Room queryObject(int id) {
		// TODO Auto-generated method stub
		return mapper.queryObject(id);
	}

	@Override
	public List<Room> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}


}
