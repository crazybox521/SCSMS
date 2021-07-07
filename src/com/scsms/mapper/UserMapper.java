package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.User;

@Mapper
public interface UserMapper {
	
	
	@Select("select id,userName,password,roleid as `role.id`,time from user where BINARY userName = #{userName} and password = #{password}")
	User login(User obj);

	@Insert("insert into user(userName,password,roleid,time)values(#{userName},#{password},#{role.id},#{time})")
	int insert(User obj);
	
	@Delete("delete from user where id = #{id}")
	int delete(int id);
	
	@Update("update user set password = #{password} where id = #{id}")
	int update(User obj);
	
	@Update("update user set roleid = #{role.id} where id = #{id}")
	int update_role(User obj);
	
	@Select("select  id,roleid as `role.id` from user where BINARY userName = #{userName}")
	User queryObject_userName(String userName);
	
	@Update("update user set password = #{password} where BINARY username = #{userName}")
	int update_pw(User obj);
	
	@Select("select id,userName,password,roleid as `role.id`,DATE_FORMAT(time, '%Y-%m-%d %k:%i:%s') as time from user")
	List<User> queryAll();
	
	@Select("select u.id,u.userName,u.password,DATE_FORMAT(time, '%Y-%m-%d %k:%i:%s') as time,u.roleid as `role.id`,r.name as `role.name`,r.notes as `role.notes` from user u join role r on u.roleid=r.id where u.id= #{id}")
	List<User> search(int id);
	
	@Select("select count(*) from user")
	int queryCount();
	
	@Select("select count(*) from user where roleid=#{roleid}")
	int queryRoleCount(int roleid);
	
	@Select("select u.id,u.userName,u.password,DATE_FORMAT(time, '%Y-%m-%d %k:%i:%s') as time,u.roleid as `role.id`,r.name as `role.name`,r.notes as `role.notes` from user u join role r on u.roleid=r.id limit #{offset},#{size}")
	List<User> queryPage(Map<String,Integer> map);
	
	@Select("select id,userName,password,roleid as `role.id`,DATE_FORMAT(time, '%Y-%m-%d %k:%i:%s') as time from user where id = #{id}")
	User queryObject(int id);
	
}
