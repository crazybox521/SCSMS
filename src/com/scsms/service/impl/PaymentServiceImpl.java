package com.scsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scsms.mapper.PaymentMapper;
import com.scsms.pojo.Payment;
import com.scsms.service.PaymentService;




@Repository
public class PaymentServiceImpl implements PaymentService {
	
	
	@Autowired
	private PaymentMapper mapper;


	@Override
	public int insert(Payment obj) {
		return mapper.insert(obj);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Payment obj) {
		return mapper.update(obj);
	}

	@Override
	public List<Payment> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public int queryCount() {
		return mapper.queryCount();
	}

	@Override
	public List<Payment> queryPage(int offset, int size) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		return mapper.queryPage(map);
	}

	@Override
	public Payment queryObject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> search(int id) {
		// TODO Auto-generated method stub
		return mapper.search(id);
	}

	@Override
	public Payment queryObjectp(String payid) {
		// TODO Auto-generated method stub
		return mapper.queryObjectp(payid);
	}

	@Override
	public List<Payment> queryPageByStudent(int offset, int size, int studentid) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("studentid", studentid);
		return mapper.queryPageByStudent(map);
	}

	@Override
	public int queryCountByStudent(int studentid) {
		// TODO Auto-generated method stub
		return mapper.queryCountByStudent(studentid);
	}

	@Override
	public List<Payment> searchPay(String payid) {
		// TODO Auto-generated method stub
		return mapper.searchPay(payid);
	}


}
