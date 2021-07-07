package com.scsms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.RoleMapper;
import com.scsms.pojo.Role;
import com.scsms.service.RoleService;




@Repository
public class RoleServiceImpl implements RoleService {
	
	
	@Autowired
	private RoleMapper mapper;


	@Override
	public int insert(Role obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Role obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Role> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Role> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role queryObject(int id) {
		// TODO Auto-generated method stub
		return mapper.queryObject(id);
	}

	@Override
	public List<Role> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}


}
