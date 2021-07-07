package com.scsms.service;

import java.util.List;

import com.scsms.pojo.Notice;

public interface NoticeService extends BaseService<Notice> {

	List<Notice> queryPageN(int offset,int size,int gradeid);
	int queryCountByGrade(int gradeid);
	List<Notice> queryByStudent(int studentid);
}
