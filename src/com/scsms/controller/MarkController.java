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
import org.springframework.web.bind.annotation.ResponseBody;

import com.scsms.pojo.Mark;
import com.scsms.pojo.Student;
import com.scsms.pojo.User;
import com.scsms.service.MarkService;
import com.scsms.service.StudentService;

@Controller
@CrossOrigin
@RequestMapping("markController")
public class MarkController {
	@Resource
	private MarkService service;
	
	@Resource
	private StudentService sservice;
	
	
	


	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(String page,String limit,HttpServletRequest request){
		String key=request.getParameter("key[id]");
		String action=request.getParameter("action");
		Map<String, Object> map = new HashMap<String, Object>();
		if(key!=null) {
			List<Mark> list = service.search(Integer.parseInt(key));
			map.put("data", list);
			map.put("count", 1);
			map.put("msg", "succes");
		}else {
			int currentPage =Integer.parseInt(page);
			int size = Integer.parseInt(limit);
			int offset = (currentPage - 1)*size;
			int count = service.queryCount();
			List<Mark> data = service.queryPageM(offset, size,Integer.parseInt(action));
			map.put("data", data);
			map.put("count", count);
			map.put("msg", "succes");
			
		}
		    map.put("code",0);
		return map;
	}
	
	
	@RequestMapping("stu_list")
	@ResponseBody
	public Map<String,Object> stu_list(String page,String limit,HttpSession session){
		
		Map<String, Object> map = new HashMap<String, Object>();
		User user =(User) session.getAttribute("student");
		Student stu=sservice.queryObject(user.getId());
		int currentPage =Integer.parseInt(page);
		int size = Integer.parseInt(limit);
		int offset = (currentPage - 1)*size;
		int count = service.queryCountByStudent(stu.getId());
		if(count!=0) {
			List<Mark> data = service.queryPageByStudent(offset, size,stu.getId());
			map.put("data", data);
			map.put("count", count);
			map.put("msg", "succes");
			map.put("code",0);
		}else {
			map.put("msg", "error");
			map.put("code",0);
		}
		
		return map;
	}
	
	
	@RequestMapping("queryAll")
	@ResponseBody
	public Map<String,Object> queryAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int count = service.queryCount();
		List<Mark> data = service.queryAll();
		map.put("data", data);
		map.put("count", count);
		map.put("msg", "sucess");
		map.put("code", 0);
		return map;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(Mark obj,HttpServletResponse response) throws IOException {
	
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
	
	@RequestMapping("update")
	@ResponseBody
	public void update(Mark obj,HttpServletResponse response) {
		if(Integer.parseInt(obj.getFenshu())<60) {
			obj.setState("不合格");
		}else if(Integer.parseInt(obj.getFenshu())>=60 && Integer.parseInt(obj.getFenshu())<75){
			obj.setState("合格");
		}else if(Integer.parseInt(obj.getFenshu())>=75 && Integer.parseInt(obj.getFenshu())<90){
			obj.setState("良好");
		}else if(Integer.parseInt(obj.getFenshu())>=90){
			obj.setState("优秀");
		}
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
	public Mark queryObject(int id,HttpServletResponse response) {
		Mark mark = service.queryObject(id);
		return mark;
		
	}

	
	
}
