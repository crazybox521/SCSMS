package com.scsms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.scsms.pojo.Notice;
import com.scsms.pojo.Student;
import com.scsms.pojo.User;
import com.scsms.service.NoticeService;
import com.scsms.service.StudentService;

@Controller
@CrossOrigin
@RequestMapping("noticeController")
public class NoticeController {
	@Resource
	private NoticeService service;
	
	@Resource
	private StudentService sservice;
	
	
	


	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(String page,String limit,HttpServletRequest request){
		String action=request.getParameter("action");
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage =Integer.parseInt(page);
		int size = Integer.parseInt(limit);
		int offset = (currentPage - 1)*size;
		int count = service.queryCountByGrade(Integer.parseInt(action));
		List<Notice> data = service.queryPageN(offset, size,Integer.parseInt(action));
		map.put("data", data);
		map.put("count", count);
		map.put("msg", "succes");
	    map.put("code",0);
		return map;
	}
	
	
	
	@RequestMapping("stu_noticelist")
	@ResponseBody
	public Map<String,Object> stu_noticelist(HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		User user=(User) session.getAttribute("student");
		Student stu=sservice.queryObject(user.getId());
		List<Notice> data = service.queryByStudent(stu.getId());
		if(data!=null && !data.isEmpty()) {
			map.put("data", data);
			map.put("msg", "succes");
		}else {
			map.put("msg", "error");
		}
	    map.put("code",0);
		return map;
	}
	
	
	@RequestMapping("queryAll")
	@ResponseBody
	public Map<String,Object> queryAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int count = service.queryCount();
		List<Notice> data = service.queryAll();
		map.put("data", data);
		map.put("count", count);
		map.put("msg", "sucess");
		map.put("code", 0);
		return map;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(Notice obj,HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String time= formatter.format(date); 
		obj.setTime(time);
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
	public void update(Notice obj,HttpServletResponse response) {
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

	
}
