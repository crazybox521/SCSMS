package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Notice;

@Mapper
public interface NoticeMapper {
	
	
	@Insert("insert into notice(gradeid,notes,time)values(#{grade.id},#{notes},#{time})")
	int insert(Notice obj);
	
	@Delete("delete from notice where id = #{id}")
	int delete(int id);
	
	@Update("update notice set notes = #{notes} where id = #{id}")
	int update(Notice obj);
	
	@Select("select * from notice")
	List<Notice> queryAll();
	
	@Select("select n.id,n.gradeid as `grade.id`,n.notes,DATE_FORMAT(time, '%Y-%m-%d %k:%i:%s') as time,g.gradeName as `grade.gradeName` from notice n join grade g on g.id=n.gradeid where n.gradeid in (select gradeid from student_grade where studentid=#{studentid})")
	List<Notice> queryByStudent(int studentid);
	
	@Select("select count(*) from notice")
	int queryCount();
	
	@Select("select count(*) from notice where gradeid=#{graedid}")
	int queryCountByGrade(int gradeid);
	
	@Select("select * from notice where id= #{id}")
	List<Notice> search(int id);
	
	@Select("select * from notice limit #{offset},#{size}")
	List<Notice> queryPage(Map<String,Integer> map);
	
	@Select("select id,gradeid as `grade.id`,notes,DATE_FORMAT(time, '%Y-%m-%d %k:%i:%s') as time from notice where gradeid=#{gradeid} limit #{offset},#{size}")
	List<Notice> queryPageN(Map<String,Integer> map);
	
	@Select("select * from notice where id = #{id}")
	Notice queryObject(int id);
	
}
