package com.scsms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scsms.pojo.Role;
import com.scsms.pojo.Student;
import com.scsms.pojo.Teacher;
import com.scsms.pojo.User;
import com.scsms.service.GradeService;
import com.scsms.service.LessonService;
import com.scsms.service.RoleService;
import com.scsms.service.RoomService;
import com.scsms.service.StudentService;
import com.scsms.service.TeacherService;
import com.scsms.service.UserService;

@Controller
@CrossOrigin
@RequestMapping("userController")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private StudentService stuService;
	@Resource
	private TeacherService teaService;
	@Resource
	private RoomService rService;
	@Resource
	private LessonService lService;
	@Resource
	private GradeService gService;
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(String page,String limit,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null==cookies) {
            System.out.println("没有cookie=========");
        } else {
            for(Cookie cookie : cookies){
                System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());
            }
        }

		String key=request.getParameter("key[id]");
		Map<String, Object> map = new HashMap<String, Object>();
		if(key!=null) {
			List<User> list = userService.search(Integer.parseInt(key));
			map.put("data", list);
			map.put("total", 1);
		}else {
			int currentPage =Integer.parseInt(page);
			int size = Integer.parseInt(limit);
			int offset = (currentPage - 1)*size;
			int total = userService.queryCount();
			List<User> list = userService.queryPage(offset, size);
			map.put("data", list);
			map.put("total", total);
			
		}
		    map.put("code",0);
		return map;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(User obj,Student stu,Teacher tea,HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String time= formatter.format(date); 
		obj.setTime(time);
		int i,j=0;
		Role r=new Role();
		r.setId(3);
		if(obj.getRole()==null)
			obj.setRole(r);
		switch (obj.getRole().getId()) {
		case 1:i=j=0;
			if(userService.queryObject_userName(obj.getUserName())==null) {
				i=userService.insert(obj);
				if(i!=0) {
					try {
						PrintWriter out = response.getWriter();
						out.write("{\"result\":\"yes\"}");
						out.flush();
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				else {
					try {
						PrintWriter out = response.getWriter();
						out.write("{\"result\":\"no\"}");
						out.flush();
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
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
			
			break;
		case 2:i=j=0;
			if(userService.queryObject_userName(obj.getUserName())==null) {
				Teacher tea1=new Teacher();
				i=userService.insert(obj);
				tea1.setUser(userService.login(obj));
				j=teaService.insert_login(tea1);
				if(j*i!=0) {
					try {
						PrintWriter out = response.getWriter();
						out.write("{\"result\":\"yes\"}");
						out.flush();
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				else {
					try {
						PrintWriter out = response.getWriter();
						out.write("{\"result\":\"no\"}");
						out.flush();
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
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
			
			break;
		case 3:i=j=0;
				if(userService.queryObject_userName(obj.getUserName())==null) {
					i=userService.insert(obj);
					stu.setUser(userService.login(obj));
					j=stuService.insert_login(stu);
					if(i*j!=0) {
						try {
							PrintWriter out = response.getWriter();
							out.write("{\"result\":\"yes\"}");
							out.flush();
							out.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					else {
						try {
							PrintWriter out = response.getWriter();
							out.write("{\"result\":\"no\"}");
							out.flush();
							out.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
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
	
			break;

		default:
			try {
				PrintWriter out = response.getWriter();
				out.write("{\"result\":\"no\"}");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}


	}
	
	@RequestMapping("update")
	@ResponseBody
	public void update(HttpSession session, User obj,HttpServletResponse response,HttpServletRequest request,String password_ud) {
		String action=request.getParameter("action");
		int i=0;
		if("pw".equals(action)) {
			if(userService.login(obj)!=null) {
				obj=userService.login(obj);
				obj.setPassword(password_ud);
				i = userService.update_pw(obj);
			}
		     
	     if(i!=0) {
	    	 session.invalidate();
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
		else if("all".equals(action)) {
			 i = userService.update(obj);
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
	
	@RequestMapping("delete")
	@ResponseBody
	public void delete(int id,HttpServletResponse response) {
		int i = userService.delete(id);
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
	
	@RequestMapping("deleteAll")
	@ResponseBody
	public void deleteAll(@RequestParam(value = "arrs[]")String[] arrs,HttpServletResponse response) {
		int i =0;
		for(String arr:arrs ) {
			i+= userService.delete(Integer.parseInt(arr));
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
	
	
	
	@RequestMapping("queryCountAll")
	@ResponseBody
	public Map<String,Object> queryCount(HttpSession session,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i=0,j=0,k=0,l=0,m=0,n=0;
		if(session.getAttribute("admin")!=null) {
			i=userService.queryCount();
			j=stuService.queryCount();
			k=teaService.queryCount();
			l=lService.queryCount();
			m=rService.queryCount();
			n=gService.queryCount();
			
			
		}
		map.put("uCount", i);
		map.put("sCount", j);
		map.put("tCount", k);
		map.put("lCount", l);
		map.put("rCount", m);
		map.put("gCount", n);
		
		return map;
		
	}

	
	
	
	
}
