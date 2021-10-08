package com.scsms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scsms.pojo.Grade;
import com.scsms.pojo.Lesson;
import com.scsms.pojo.Mark;
import com.scsms.pojo.Student;
import com.scsms.pojo.Teacher;
import com.scsms.pojo.User;
import com.scsms.service.GradeService;
import com.scsms.service.MarkService;
import com.scsms.service.StudentService;
import com.scsms.service.TeacherService;

@Controller
@CrossOrigin
@RequestMapping("gradeController")
public class GradeController {
	@Resource
	private GradeService service;
	@Resource
	private StudentService stuservice;
	@Resource
	private TeacherService teaservice;
	@Resource
	private MarkService markservice;
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(String page,String limit,HttpServletRequest request){
		String key=request.getParameter("key[id]");
		Map<String, Object> map = new HashMap<String, Object>();
		if(key!=null) {
			List<Grade> list = service.search(Integer.parseInt(key));
			map.put("data", list);
			map.put("count", 1);
			map.put("msg", "succes");
		}else {
			int currentPage =Integer.parseInt(page);
			int size = Integer.parseInt(limit);
			int offset = (currentPage - 1)*size;
			int count = service.queryCount();
			List<Grade> data = service.queryPage(offset, size);
			map.put("data", data);
			map.put("count", count);
			map.put("msg", "succes");
			
		}
		    map.put("code",0);
		return map;
	}
	
	
	@RequestMapping("tea_list")
	@ResponseBody
	public Map<String,Object> tea_list(String page,String limit,HttpSession session){
		User user = (User) session.getAttribute("teacher");
		Teacher tea=teaservice.queryObject(user.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage =Integer.parseInt(page);
		int size = Integer.parseInt(limit);
		int offset = (currentPage - 1)*size;
		int count = service.queryCountByTeacherid(tea.getId());
		List<Grade> data = service.tea_queryPage(offset, size,tea.getId());
		map.put("count", count);
		map.put("data", data);
		map.put("msg", "succes");
		
	    map.put("code",0);
		return map;
	}
	
	
	@RequestMapping("tea_listAll")
	@ResponseBody
	public Map<String,Object> tea_listAll(HttpSession session){
		User user = (User) session.getAttribute("teacher");
		Teacher tea=teaservice.queryObject(user.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		int count = service.queryCountByTeacherid(tea.getId());
		List<Grade> data = service.queryAllByTeacherid(tea.getId());
		map.put("count", count);
		map.put("data", data);
		map.put("msg", "succes");
		
	    map.put("code",0);
		return map;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(Grade obj,HttpServletResponse response) throws IOException {
		obj.setState("报名");
		System.out.println(obj);
		int i = service.insert(obj);
		if(i!=0) {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"yes\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"no\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("tea_insert")
	@ResponseBody
	public void tea_insert(Grade obj,HttpServletResponse response,HttpSession session) throws IOException {
		User user = (User) session.getAttribute("teacher");
		Teacher tea=teaservice.queryObject(user.getId());
		int i =0;
		if(tea.getLesson()!=null) {
			obj.setTeacher(tea);
			Lesson les = new Lesson();
			les.setId(tea.getLesson().getId());
			obj.setLesson(les);
			obj.setState("报名");
			i = service.insert(obj);
		}
		
		if(i!=0) {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"yes\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"no\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public void update(Grade obj,HttpServletResponse response) {
		int i = service.update(obj);
		if(i!=0) {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"yes\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"no\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public void delete(int id,HttpServletResponse response) {
		int i = service.delete(id);
		if(i!=0) {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"yes\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"no\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("info")
	@ResponseBody
	public Grade queryObject(int id,HttpServletResponse response) {
		Grade les = service.queryObject(id);
		return les;
		
	}
	
	@RequestMapping("deleteAll")
	@ResponseBody
	public void deleteAll(@RequestParam(value = "arrs[]")String[] arrs,HttpServletResponse response) {
		int i =0;
		for(String arr:arrs ) {
			i+= service.delete(Integer.parseInt(arr));
		}
			
		if(i!=0) {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"yes\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"no\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping("gradelist")
	@ResponseBody
	public Map<String,Object> gradelist(int id){
		System.out.println(id);
		Map<String, Object> map = new HashMap<String, Object>();
		int count = service.queryCountByLessonid(id);
		List<Grade> data = service.queryByLessonid(id);
		if(data!=null && !data.isEmpty()) {
			map.put("data", data);
			map.put("count", count);
			map.put("msg", "succes");
		    map.put("code",0);
		}else {
			map.put("msg", "fail");
		    map.put("code",0);
		}
		
		return map;
	}
	
	
	

	@RequestMapping("chooselesson")
	@ResponseBody
	public Map<String,Object> chooselesson(int id,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("student");
		Student stu = stuservice.queryObject(user.getId());
		Grade g=new Grade();
		g.setId(id);
		Mark m=new Mark();
		m.setGrade(g);
		m.setStudent(stu);
		markservice.insert(m);
		int i = service.chooseLesson(stu.getId(), id);
		if(i!=0) {
			map.put("msg", "succes");
		    map.put("code",0);
		}else {
			map.put("msg", "fail");
		    map.put("code",0);
		}
		
		return map;
	}
	
	
	@RequestMapping("mygradelist")
	@ResponseBody
	public Map<String,Object> mygradelist(HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("student");
		if(user!=null) {
		Student stu = stuservice.queryObject(user.getId());
		int count = service.queryCountByStudentid(stu.getId());
		if(count!=0) {
			List<Grade> data = service.queryByStudentid(stu.getId());
			map.put("data", data);
			map.put("count", count);
			map.put("msg", "succes");
		    map.put("code",0);
		}else {
			map.put("msg", "nodata");
		    map.put("code",0);
		}
		}else {
			map.put("msg", "fail");
		    map.put("code",0);
		}
		
		return map;
	}
	@RequestMapping("mystu_list")
	@ResponseBody
	public Map<String,Object> mystu_list(String page,String limit,HttpSession session,int id){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("teacher");
		if(user!=null) {
			int currentPage =Integer.parseInt(page);
			int size = Integer.parseInt(limit);
			int offset = (currentPage - 1)*size;
			List<Student> data = stuservice.queryPageGradeStu(offset, size,id);
			if(data!=null&&!data.isEmpty()) {
				int count = service.queryCountByGradeid(id);
				map.put("data", data);
				map.put("count", count);
				map.put("msg", "succes");
				map.put("code",0);
			}else {
				map.put("msg", "fail");
				map.put("code",0);
			}
		}
		
		return map;
	}
	
	
	@RequestMapping("jieshu")
	@ResponseBody
	public void jieshu(int id,HttpServletResponse response) {
		int i = service.setjieshu(id);
		if(i!=0) {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"yes\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"no\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

	
	
}
