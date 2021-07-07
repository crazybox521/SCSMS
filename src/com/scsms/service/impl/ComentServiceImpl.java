package com.scsms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.ComentMapper;
import com.scsms.pojo.Coment;
import com.scsms.service.ComentService;




@Repository
public class ComentServiceImpl implements ComentService {
	
	
	@Autowired
	private ComentMapper mapper;


	@Override
	public int insert(Coment obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Coment obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Coment> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Coment> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coment queryObject(int id) {
		// TODO Auto-generated method stub
		return mapper.queryObject(id);
	}

	@Override
	public List<Coment> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}




}
