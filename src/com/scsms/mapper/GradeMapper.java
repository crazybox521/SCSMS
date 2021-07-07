package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Grade;

@Mapper
public interface GradeMapper {
	
	
	@Insert("insert into grade(gradename,notes,roomid,lessonid,teacherid,starttime,endtime,state)values(#{gradeName},#{notes},#{room.id},#{lesson.id},#{teacher.id},#{starttime},#{endtime},#{state})")
	int insert(Grade obj);
	
	@Delete("delete from grade where id = #{id}")
	int delete(int id);
	
	@Update("update grade set gradename = #{gradeName},notes = #{notes},roomid = #{room.id},lessonid = #{lesson.id},teacherid = #{teacher.id} where id = #{id}")
	int update(Grade obj);
	
	@Update("update grade set state = '结束报名'  where id = #{id}")
	int setjieshu(int id);
	
	@Select("select g.id,g.gradeName,g.starttime,g.endtime,g.notes,g.state,g.roomid as `room.id`,g.lessonid as `lesson.id`,g.teacherid as `teacher.id`,t.name as `teacher.name`,l.lessonname as `lesson.lessonName`,r.roomname as `room.roomName`,r.location as `room.location` from grade g join teacher t on g.teacherid=t.id join lesson l on l.id = g.lessonid join room r on r.id=g.roomid")
	List<Grade> queryAll();
	
	@Select("select g.id,g.gradeName,g.starttime,g.endtime,g.notes,g.state,g.roomid as `room.id`,g.lessonid as `lesson.id`,g.teacherid as `teacher.id`,t.name as `teacher.name`,l.lessonname as `lesson.lessonName`,r.roomname as `room.roomName`,r.location as `room.location` from grade g join teacher t on g.teacherid=t.id join lesson l on l.id = g.lessonid join room r on r.id=g.roomid where g.teacherid=#{teacherid}")
	List<Grade> queryAllByTeacherid(int tercherid);
	
	
	@Select("select g.id,g.gradeName,g.starttime,g.endtime,g.notes,g.state,g.roomid as `room.id`,g.lessonid as `lesson.id`,g.teacherid as `teacher.id`,t.name as `teacher.name`,l.lessonname as `lesson.lessonName`,l.price as `lesson.price`,r.roomname as `room.roomName`,r.location as `room.location` from grade g join teacher t on g.teacherid=t.id join lesson l on l.id = g.lessonid join room r on r.id=g.roomid where g.lessonid=#{lessonid} and g.state='报名'")
	List<Grade> queryByLessonid(int lessonid);

	@Select("select count(*) from grade")
	int queryCount();
	
	@Select("select count(*) from grade where teacherid=#{teacherid}")
	int queryCountByTeacherid(int teacherid);
	
	@Select("select count(*) from grade where lessonid=#{lessonid}")
	int queryCountByLessonid(int lessonid);
	
	@Select("select g.id,g.gradeName,g.starttime,g.endtime,g.notes,g.state,g.roomid as `room.id`,g.lessonid as `lesson.id`,g.teacherid as `teacher.id`,t.name as `teacher.name`,l.lessonname as `lesson.lessonName`,r.roomname as `room.roomName`,r.location as `room.location` from grade g join teacher t on g.teacherid=t.id join lesson l on l.id = g.lessonid join room r on r.id=g.roomid where g.id in (select gradeid from student_grade where studentid=#{studentid})")
	List<Grade> queryByStudentid(int Studentid);
	
	
	@Select("select count(*) from student_grade where studentid=#{studentid}")
	int queryCountByStudentid(int studentid);
	
	@Select("select count(*) from student_grade where gradeid=#{gradeid}")
	int queryCountByGradeid(int gradeid);

	
	@Insert("insert into student_grade(studentid,gradeid)values(#{studentid},#{gradeid})")
	int chooseLesson(Map<String,Integer> map);
	
	@Select("select g.id,g.gradeName,g.starttime,g.endtime,g.notes,g.state,g.roomid as `room.id`,g.lessonid as `lesson.id`,g.teacherid as `teacher.id`,t.name as `teacher.name`,l.lessonname as `lesson.lessonName`,r.roomname as `room.roomName`,r.location as `room.location` from grade g join teacher t on g.teacherid=t.id join lesson l on l.id = g.lessonid join room r on r.id=g.roomid where g.id= #{id}")
	List<Grade> search(int id);
	
	@Select("select g.id,g.gradeName,g.starttime,g.endtime,g.notes,g.state,g.roomid as `room.id`,g.lessonid as `lesson.id`,g.teacherid as `teacher.id`,t.name as `teacher.name`,l.lessonname as `lesson.lessonName`,r.roomname as `room.roomName`,r.location as `room.location` from grade g join teacher t on g.teacherid=t.id join lesson l on l.id = g.lessonid join room r on r.id=g.roomid limit #{offset},#{size}")
	List<Grade> queryPage(Map<String,Integer> map);
	
	@Select("select g.id,g.gradeName,g.starttime,g.endtime,g.notes,g.state,g.roomid as `room.id`,g.lessonid as `lesson.id`,g.teacherid as `teacher.id`,t.name as `teacher.name`,l.lessonname as `lesson.lessonName`,r.roomname as `room.roomName`,r.location as `room.location` from grade g join teacher t on g.teacherid=t.id join lesson l on l.id = g.lessonid join room r on r.id=g.roomid where t.id=#{id} limit #{offset},#{size}")
	List<Grade> tea_queryPage(Map<String,Integer> map);
	
	@Select("select g.id,g.gradeName,g.starttime,g.endtime,g.notes,g.state,g.roomid as `room.id`,g.lessonid as `lesson.id`,g.teacherid as `teacher.id`,t.name as `teacher.name`,l.lessonname as `lesson.lessonName`,l.price as `lesson.price`,r.roomname as `room.roomName`,r.location as `room.location` from grade g join teacher t on g.teacherid=t.id join lesson l on l.id = g.lessonid join room r on r.id=g.roomid  where g.id = #{id}")
	Grade queryObject(int id);
	
}
