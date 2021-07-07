package com.scsms.service;



import com.scsms.pojo.User;

public interface UserService extends BaseService<User> {
	User login(User obj);
	int update_pw(User obj);
	int update_role(User obj);
	User queryObject_userName(String userName);
	int queryRoleCount(int roleid);
}
