package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Mark;

@Mapper
public interface MarkMapper {
	
	
	@Insert("insert into mark(studentid,fenshu,notes,gradeid)values(#{student.id},#{fenshu},#{notes},#{grade.id})")
	int insert(Mark obj);
	
	@Delete("delete from mark where id = #{id}")
	int delete(int id);
	
	@Update("update mark set fenshu = #{fenshu},notes = #{notes},state = #{state} where id = #{id}")
	int update(Mark obj);
	
	@Select("select * from mark")
	List<Mark> queryAll();
	
	@Select("select count(*) from mark")
	int queryCount();
	
	@Select("select count(*) from mark where studentid=#{studentid}")
	int queryCountByStudent(int studentid);
	
	@Select("select * from mark limit #{offset},#{size}")
	List<Mark> queryPage(Map<String,Integer> map);
	
	@Select("select m.id,m.fenshu,m.notes,m.state,sg.gradeid as `grade.id`,sg.studentid as `student.id`,s.name as `student.name` from mark m RIGHT JOIN student_grade sg on sg.gradeid=m.gradeid and sg.studentid =m.studentid join student s on s.id=sg.studentid where sg.gradeid =#{gradeid} limit #{offset},#{size}")
	List<Mark> queryPageM(Map<String,Integer> map);
	
	@Select("select m.id,m.fenshu,m.notes,m.state,m.gradeid as `grade.id`,g.gradeName as `grade.gradeName`,m.studentid as `student.id`,s.name as `student.name` from mark m join student s on s.id=m.studentid join grade g on g.id = m.gradeid where m.studentid =#{studentid} limit #{offset},#{size}")
	List<Mark> queryPageByStudent(Map<String,Integer> map);
	
	@Select("select * from mark where id= #{id}")
	List<Mark> search(int id);
	
	@Select("select * from mark where id = #{id}")
	Mark queryObject(int id);
	
}
