package com.scsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.UserMapper;
import com.scsms.pojo.User;
import com.scsms.service.UserService;

@Repository
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserMapper mapper;
	
	@Override
	public int insert(User obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(User obj) {
		return mapper.update(obj);
	}

	@Override
	public List<User> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<User> queryPage(int offset, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		return mapper.queryPage(map);
	}

	@Override
	public User queryObject(int id) {
		return mapper.queryObject(id);
	}

	@Override
	public User login(User obj) {
		return mapper.login(obj);
	}

	@Override
	public int update_pw(User obj) {
		return mapper.update_pw(obj);
	}

	@Override
	public int update_role(User obj) {
		return mapper.update_role(obj);
	}

	@Override
	public User queryObject_userName(String userName) {
		return mapper.queryObject_userName(userName);
	}

	@Override
	public int queryRoleCount(int roleid) {
		// TODO Auto-generated method stub
		return mapper.queryRoleCount(roleid);
	}

	@Override
	public List<User> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}

}
