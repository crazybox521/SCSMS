package com.scsms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.scsms.pojo.Payment;

@Mapper
public interface PaymentMapper {
	
	
	@Insert("insert into payment(studentid,gradeid,number,payid,alipay,state)values(#{student.id},#{grade.id},#{number},#{payid},#{alipay},#{state})")
	int insert(Payment obj);
	
	@Delete("delete from payment where id = #{id}")
	int delete(int id);
	
	@Update("update payment set state=#{state},alipay=#{alipay} where id = #{id}")
	int update(Payment obj);
	
	@Select("select * from payment")
	List<Payment> queryAll();
	
	@Select("select count(*) from payment")
	int queryCount();
	
	@Select("select count(*) from payment where studentid= #{studentid} ")
	int queryCountByStudent(int studentid);
	
	@Select("select p.id,p.number,p.gradeid as `grade.id`,g.gradeName as `grade.gradeName`,p.studentid as `student.id`,s.name as `student.name`,p.payid,p.alipay,p.state from payment p join student s on s.id=p.studentid join grade g on g.id=p.gradeid limit #{offset},#{size}")
	List<Payment> queryPage(Map<String,Integer> map);
	
	@Select("select p.id,p.number,p.payid,p.alipay,p.state,p.studentid as `student.id`,p.gradeid as `grade.id`,g.gradeName as `grade.gradeName`,s.name as `student.name` from payment p join grade g on p.gradeid=g.id join  student s on s.id =p.studentid where studentid=#{studentid} limit #{offset},#{size}")
	List<Payment> queryPageByStudent(Map<String,Integer> map);
	
	@Select("select * from payment where id= #{id}")
	List<Payment> search(int id);
	
	@Select("select p.id,p.number,p.gradeid as `grade.id`,g.gradeName as `grade.gradeName`,p.studentid as `student.id`,s.name as `student.name`,p.payid,p.alipay,p.state from payment p join student s on s.id=p.studentid join grade g on g.id=p.gradeid where payid= #{payid}")
	List<Payment> searchPay(String payid);
	
	
	@Select("select p.id,p.number,p.payid,p.alipay,p.state,p.studentid as `student.id`,p.gradeid as `grade.id`,g.gradeName as `grade.gradeName` from payment p join grade g on p.gradeid=g.id where  payid= #{payid}")
	Payment queryObjectp(String payid);
	
}
