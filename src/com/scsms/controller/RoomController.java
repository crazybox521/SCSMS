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

import com.scsms.pojo.Room;
import com.scsms.service.RoomService;

@Controller
@CrossOrigin
@RequestMapping("roomController")
public class RoomController {
	@Resource
	private RoomService service;
	
	
	


	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(String page,String limit,HttpServletRequest request){
		String key=request.getParameter("key[id]");
		Map<String, Object> map = new HashMap<String, Object>();
		if(key!=null) {
			List<Room> list = service.search(Integer.parseInt(key));
			map.put("data", list);
			map.put("count", 1);
			map.put("msg", "succes");
		}else {
			int currentPage =Integer.parseInt(page);
			int size = Integer.parseInt(limit);
			int offset = (currentPage - 1)*size;
			int count = service.queryCount();
			List<Room> data = service.queryPage(offset, size);
			map.put("data", data);
			map.put("count", count);
			map.put("msg", "succes");
			
		}
		    map.put("code",0);
		return map;
	}
	
	
	@RequestMapping("queryAll")
	@ResponseBody
	public Map<String,Object> queryAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int count = service.queryCount();
		List<Room> data = service.queryAll();
		map.put("data", data);
		map.put("count", count);
		map.put("msg", "sucess");
		map.put("code", 0);
		return map;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(Room obj,HttpServletResponse response) throws IOException {
	
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
	public void update(Room obj,HttpServletResponse response) {
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
	public Room queryObject(int id,HttpServletResponse response) {
		Room room = service.queryObject(id);
		return room;
		
	}

	
	
}
