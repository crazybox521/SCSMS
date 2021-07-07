package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Teacher;

@Mapper
public interface TeacherMapper {
	
	
	@Insert("insert into teacher(name,sex,phone,email,notes,userid)values(#{name},#{sex},#{phone},#{email},#{notes},#{user.id})")
	int insert(Teacher obj);
	
	@Delete("delete from teacher where id = #{id}")
	int delete(int id);
	
	@Update("update teacher set name = #{name},sex = #{sex},phone = #{phone},email = #{email},notes = #{notes} where userid = #{user.id}")
	int update(Teacher obj);
	
	@Update("update teacher set lessonid= #{lesson.id} where userid = #{user.id}")
	int update_lessonid(Teacher obj);
	
	@Select("select id,name,sex,email,phone,notes,userid as `user.id` from teacher")
	List<Teacher> queryAll();
	
	@Select("select id,name,sex,email,phone,notes,userid as `user.id`,lessonid as `lesson.id` from teacher where lessonid =#{lessonid}")
	List<Teacher> queryLeAll(int lessonid);
	
	@Select("select count(*) from teacher")
	int queryCount();
	
	@Select("select count(*) from teacher where lessonid=#{lessonid}")
	int queryCountLes(int lessonid);
	
	@Select("select t.id,t.name,t.sex,t.email,t.phone,t.notes,t.userid as `user.id`,t.lessonid as `lesson.id`,l.lessonname as `lesson.lessonName` from teacher t left join lesson l on t.lessonid=l.id where userid= #{id}")
	List<Teacher> search(int id);
	
	@Select("select t.id,t.name,t.sex,t.email,t.phone,t.notes,t.userid as `user.id`,t.lessonid as `lesson.id`,l.lessonname as `lesson.lessonName` from teacher t left join lesson l on t.lessonid=l.id limit #{offset},#{size}")
	List<Teacher> queryPage(Map<String,Integer> map);
	
	@Select("select t.id,t.name,t.sex,t.email,t.phone,t.notes,t.userid as `user.id`,t.lessonid as `lesson.id`,l.lessonname as `lesson.lessonName` from teacher t left join lesson l on t.lessonid=l.id where userid = #{userid}")
	Teacher queryObject(int userid);
	
	@Insert("insert into teacher(userid)values(#{user.id})")
	int insert_login(Teacher obj);
	
}
