package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Coment;

@Mapper
public interface ComentMapper {
	
	
	@Insert("insert into coment(level,notes,student,lessonid)values(#{level},#{notes},#{student.name},#{lesson.id})")
	int insert(Coment obj);
	
	@Delete("delete from coment where id = #{id}")
	int delete(int id);
	
	@Update("update coment set name = #{name},notes = #{notes},student = #{student.name},lessonid = #{lesson.id} where id = #{id}")
	int update(Coment obj);
	
	@Select("select * from coment")
	List<Coment> queryAll();
	
	@Select("select count(*) from coment")
	int queryCount();
	
	@Select("select * from coment limit #{offset},#{size}")
	List<Coment> queryPage(Map<String,Integer> map);
	
	@Select("select * from coment where id= #{id}")
	List<Coment> search(int id);
	
	@Select("select * from coment where id = #{id}")
	Coment queryObject(int id);
	
}
