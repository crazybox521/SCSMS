package com.scsms.service;

import java.util.List;



public interface BaseService<E> {
	int insert(E obj);

	int delete(int id);

	int update(E obj);

	List<E> queryAll();

	int queryCount();

	List<E> queryPage(int offset,int size);

	E queryObject(int id);
	
	List<E> search(int id);


	
}
