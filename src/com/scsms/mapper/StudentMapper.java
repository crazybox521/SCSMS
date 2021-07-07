package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Student;

@Mapper
public interface StudentMapper {
	
	
	@Insert("insert into student(name,sex,phone,email,notes,userid)values(#{name},#{sex},#{phone},#{email},#{notes},#{user.id})")
	int insert(Student obj);
	
	@Delete("delete from student where id = #{id}")
	int delete(int id);
	
	@Update("update student set name = #{name},sex = #{sex},phone = #{phone},email = #{email},notes = #{notes} where userid = #{user.id}")
	int update(Student obj);
	
	@Select("select id,name,sex,email,phone,notes,userid as `user.id` from student")
	List<Student> queryAll();
	
	@Select("select count(*) from student")
	int queryCount();
	
	@Select("select id,name,sex,email,phone,notes,userid as `user.id` from student limit #{offset},#{size}")
	List<Student> queryPage(Map<String,Integer> map);
	
	@Select("select id,name,sex,email,phone,notes,userid as `user.id` from student where id in (select studentid from student_grade where gradeid=#{gradeid}) limit #{offset},#{size}")
	List<Student> queryPageGradeStu(Map<String,Integer> map);
	
	@Select("select id,name,sex,email,phone,notes,userid as `user.id` from student where userid = #{userid}")
	Student queryObject(int userid);
	
	@Select("select id,name,sex,email,phone,notes,userid as `user.id` from student where userid= #{id}")
	List<Student> search(int id);
	
	@Insert("insert into student(userid,name,phone)values(#{user.id},#{name},#{phone})")
	int insert_login(Student obj);
}
	