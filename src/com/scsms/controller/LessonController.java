package com.scsms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scsms.pojo.Lesson;
import com.scsms.service.LessonService;

@Controller
@CrossOrigin
@RequestMapping("lessonController")
public class LessonController {
	@Resource
	private LessonService service;
	
	
	

	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(String page,String limit,HttpServletRequest request){
		String key=request.getParameter("key[id]");
		Map<String, Object> map = new HashMap<String, Object>();
		if(key!=null) {
			List<Lesson> list = service.search(Integer.parseInt(key));
			map.put("data", list);
			map.put("count", 1);
			map.put("msg", "succes");
		}else {
			int currentPage =Integer.parseInt(page);
			int size = Integer.parseInt(limit);
			int offset = (currentPage - 1)*size;
			int count = service.queryCount();
			List<Lesson> data = service.queryPage(offset, size);
			map.put("data", data);
			map.put("count", count);
			map.put("msg", "succes");
			
		}
		    map.put("code",0);
		return map;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(Lesson obj,HttpServletResponse response) throws IOException {
	
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
	public void update(Lesson obj,HttpServletResponse response) {
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
	public Lesson queryObject(int id,HttpServletResponse response) {
		Lesson les = service.queryObject(id);
		return les;
		
	}
	
	@RequestMapping("lessonlist")
	@ResponseBody
	public Map<String,Object> queryAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Lesson> data = service.queryAll();
		int count =service.queryCount();
		
		map.put("data", data);
		map.put("code",0);
		map.put("msg","succes");
		map.put("count",count);
		return map;
		
	}

	
	
}
