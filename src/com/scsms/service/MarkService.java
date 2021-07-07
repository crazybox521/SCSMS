package com.scsms.service;

import java.util.List;

import com.scsms.pojo.Mark;

public interface MarkService extends BaseService<Mark> {
	List<Mark> queryPageM(int offset,int size,int gradeid);
	int queryCountByStudent(int studentid);
	List<Mark> queryPageByStudent(int offset,int size,int studentid);
}
