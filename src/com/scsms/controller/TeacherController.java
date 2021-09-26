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

import com.scsms.pojo.Teacher;
import com.scsms.pojo.User;
import com.scsms.service.TeacherService;
import com.scsms.service.UserService;

@Controller
@CrossOrigin
@RequestMapping("teacherController")
public class TeacherController {
	@Resource
	private TeacherService service;
	
	@Resource
	private UserService userservice;
	
	

	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(String page,String limit,HttpServletRequest request){
		String key=request.getParameter("key[id]");
		Map<String, Object> map = new HashMap<String, Object>();
		if(key!=null) {
			List<Teacher> list = service.search(Integer.parseInt(key));
			map.put("data", list);
			map.put("count", 1);
			map.put("msg", "succes");
		}else {
			int currentPage =Integer.parseInt(page);
			int size = Integer.parseInt(limit);
			int offset = (currentPage - 1)*size;
			int count = service.queryCount();
			List<Teacher> data = service.queryPage(offset, size);
			map.put("data", data);
			map.put("count", count);
			map.put("msg", "succes");
			
		}
		    map.put("code",0);
		return map;
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public void update(Teacher obj,HttpServletResponse response,HttpSession session) {
		User user = (User)session.getAttribute("teacher");
		User user2 = (User)session.getAttribute("admin");
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
	
	
	@RequestMapping("update_lesson")
	@ResponseBody
	public void update_lesson(Teacher obj,HttpServletResponse response,HttpSession session) {
		User user2 = (User)session.getAttribute("admin");
		int i=0;
		if(user2!=null) {
			i = service.update_lessonid(obj);
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
	public Teacher queryObject(int userid,HttpServletResponse response) {
		Teacher tea = service.queryObject(userid);
		tea.setUser(userservice.queryObject(userid));
		return tea;
		
	}
	
	@RequestMapping("info_sh")
	@ResponseBody
	public Map<String,Object> info_sh(int userid,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Teacher tea = service.queryObject(userid);
		if(tea!=null) {
			tea.setUser(userservice.queryObject(userid));
			map.put("data", tea);
			map.put("msg","succes");
		}else {
			map.put("msg","error");
		}
			
		
		return map;
		
		
		
	}
	
	@RequestMapping("getlesson")
	@ResponseBody
	public Map<String,Object> getlesson(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user=(User)session.getAttribute("teacher");
		Teacher tea = service.queryObject(user.getId());
		if(tea!=null && tea.getLesson()!=null) {
			map.put("data", tea);
			map.put("msg","succes");
		}else {
			map.put("msg","error");
		}
		
			
		
		return map;
		
		
		
	}
	
	@RequestMapping("lessontealist")
	@ResponseBody
	public Map<String,Object> lessontealist(HttpServletRequest request,int lessonid){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Teacher> data =service.queryLeAll(lessonid);
		int count = service.queryCountLes(lessonid);
		map.put("data", data);
		map.put("code", 0);
		map.put("msg", "succes");
		map.put("count", count);
		return map;
	}

	
	
}
