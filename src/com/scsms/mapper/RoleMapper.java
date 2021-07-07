package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Role;

@Mapper
public interface RoleMapper {
	
	
	@Insert("insert into role(name,notes)values(#{name},#{notes})")
	int insert(Role obj);
	
	@Delete("delete from role where id = #{id}")
	int delete(int id);
	
	@Update("update role set name = #{name},notes = #{notes} where id = #{id}")
	int update(Role obj);
	
	@Select("select * from role")
	List<Role> queryAll();
	
	@Select("select count(*) from role")
	int queryCount();
	
	@Select("select * from role limit #{offset},#{size}")
	List<Role> queryPage(Map<String,Integer> map);
	
	@Select("select * from role where id= #{id}")
	List<Role> search(int id);
	
	@Select("select * from role where id = #{id}")
	Role queryObject(int id);
	
}
