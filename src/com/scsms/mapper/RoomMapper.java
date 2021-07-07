package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Room;

@Mapper
public interface RoomMapper {
	
	
	@Insert("insert into room(roomname,location)values(#{roomName},#{location})")
	int insert(Room obj);
	
	@Delete("delete from room where id = #{id}")
	int delete(int id);
	
	@Update("update room set roomname = #{roomName},location = #{location} where id = #{id}")
	int update(Room obj);
	
	@Select("select * from room")
	List<Room> queryAll();
	
	@Select("select count(*) from room")
	int queryCount();
	
	@Select("select * from room limit #{offset},#{size}")
	List<Room> queryPage(Map<String,Integer> map);
	
	@Select("select * from room where id= #{id}")
	List<Room> search(int id);
	
	@Select("select * from room where id = #{id}")
	Room queryObject(int id);
	
}
