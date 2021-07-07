package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Lesson;

@Mapper
public interface LessonMapper {
	
	
	@Insert("insert into lesson(lessonname,notes,price,hours)values(#{lessonName},#{notes},#{price},#{hours})")
	int insert(Lesson obj);
	
	@Delete("delete from lesson where id = #{id}")
	int delete(int id);
	
	@Update("update lesson set lessonname = #{lessonName},notes = #{notes},price = #{price},hours=#{hours} where id = #{id}")
	int update(Lesson obj);
	
	@Select("select * from lesson")
	List<Lesson> queryAll();
	
	@Select("select count(*) from lesson")
	int queryCount();
	
	@Select("select * from lesson limit #{offset},#{size}")
	List<Lesson> queryPage(Map<String,Integer> map);
	
	@Select("select id,lessonName,notes,price,hours from lesson where id= #{id}")
	List<Lesson> search(int id);
	
	@Select("select * from lesson where id = #{id}")
	Lesson queryObject(int id);
	
}
