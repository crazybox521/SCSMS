package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Roomstate;

@Mapper
public interface RoomstateMapper {
	
	
	@Insert("insert into roomstate(roomid,week,state)values(#{room.id},#{week},#{state})")
	int insert(Roomstate obj);
	
	@Delete("delete from roomstate where id = #{id}")
	int delete(int id);
	
	@Update("update roomstate set roomid = #{room.id},week = #{week},state = #{state} where id = #{id}")
	int update(Roomstate obj);
	
	@Select("select * from roomstate")
	List<Roomstate> queryAll();
	
	@Select("select count(*) from roomstate")
	int queryCount();
	
	@Select("select * from roomstate limit #{offset},#{size}")
	List<Roomstate> queryPage(Map<String,Integer> map);
	
	@Select("select * from roomstate where id= #{id}")
	List<Roomstate> search(int id);
	
	@Select("select * from roomstate where id = #{id}")
	Roomstate queryObject(int id);
	
}
