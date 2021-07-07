package com.scsms.service;

import java.util.List;

import com.scsms.pojo.Payment;

public interface PaymentService extends BaseService<Payment> {
	Payment queryObjectp(String payid);
	List<Payment> queryPageByStudent(int offset,int size,int studentid);
	int queryCountByStudent(int studentid);
	List<Payment> searchPay(String payid);
}
