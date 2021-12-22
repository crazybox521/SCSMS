package com.scsms.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.scsms.pojo.Student;
import com.scsms.pojo.User;
import com.scsms.service.StudentService;
import com.scsms.service.UserService;

@Controller
@CrossOrigin
@RequestMapping("studentController")
public class StudentController {
	@Resource
	private StudentService service;
	
	@Resource
	private UserService userservice;
	
	

	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(String page,String limit,HttpServletRequest request){
		String key=request.getParameter("key[id]");
		Map<String, Object> map = new HashMap<String, Object>();
		if(key!=null) {
			List<Student> list = service.search(Integer.parseInt(key));
			map.put("data", list);
			map.put("count", 1);
			map.put("msg", "succes");
		}else {
			int currentPage =Integer.parseInt(page);
			int size = Integer.parseInt(limit);
			int offset = (currentPage - 1)*size;
			int count = service.queryCount();
			List<Student> data = service.queryPage(offset, size);
			map.put("data", data);
			map.put("count", count);
			map.put("msg", "succes");
			
		}
		    map.put("code",0);
		return map;
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public void update(Student obj,HttpServletResponse response,HttpSession session) {
		
		System.out.println(obj);
		User user = (User)session.getAttribute("student");
		User user2 = (User)session.getAttribute("admin");
		System.out.println(user);
		int i=0;
		if(user!=null) {
			obj.setUser(user);
			i = service.update(obj);
		}
		if(user2!=null) {
			i = service.update(obj);
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
	public Student queryObject(int userid,HttpServletResponse response) {
		Student stu3 = service.queryObject(userid);
		if(stu3!=null)
			stu3.setUser(userservice.queryObject(userid));
		return stu3;
		
		
		
	}
	
	@RequestMapping("info_sh")
	@ResponseBody
	public Map<String,Object> info_sh(int userid,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Student stu3 = service.queryObject(userid);
		if(stu3!=null) {
			stu3.setUser(userservice.queryObject(userid));
			map.put("data", stu3);
			map.put("msg","succes");
		}else {
			map.put("msg","error");
		}
			
		
		return map;
		
		
		
	}

	
	
}
