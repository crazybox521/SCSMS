package com.scsms.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsms.pojo.User;
import com.scsms.service.UserService;

@Controller
public class IndexController {
	@Resource
	private UserService userService;

	@RequestMapping("/")
	public String index() { 
		return "index"; 
	}
	
	@RequestMapping("index")
	public String index2(HttpSession session) { 
		 session.invalidate();
		return "index"; 
	}
	
	@RequestMapping("lesson")
	public String lesson() { 
		return "lesson"; 
	}
	
	@RequestMapping("welcome")
	public String welcome() {
		return "welcome";
	}
	@CrossOrigin
	@RequestMapping("login")
	public String login(HttpSession session,User obj){
		User log=userService.login(obj);
		String path="error";
		if(log==null) {
			return "error";
		}else{
			
			switch (log.getRole().getId()) {
			case 1:
				session.removeAttribute("user");
				session.removeAttribute("dept");
				session.removeAttribute("admin");
				session.setAttribute("admin", log);
				path="admin";
				
				break;
			case 2:
				session.removeAttribute("user");
				session.removeAttribute("admin");
				session.removeAttribute("dept");
				session.setAttribute("teacher", log);
				path="tea";
				break;
			case 3:
				session.removeAttribute("admin");
				session.removeAttribute("dept");
				session.removeAttribute("user");
				session.setAttribute("student", log);
				path="stu";
				break;
			default:
				break;
			}
			return path;
		}
		
		
	}
	
	@RequestMapping("cla_ad")
	public String cla_ad(HttpSession session) { 
		return "ad/cla_ad"; 
	}
	
	@RequestMapping("stu")
	public String stu(HttpSession session) { 
		return "stu"; 
	}
	
	@RequestMapping("data_ad")
	public String data_ad(HttpSession session) { 
		return "ad/data_ad"; 
	}
	
	@RequestMapping("lesson_ad")
	public String lesson_ad(HttpSession session) { 
		return "ad/lesson_ad"; 
	}	
	
	@RequestMapping("mark_ad")
	public String mark_ad(HttpSession session) { 
		return "ad/mark_ad";
	}	
	
	@RequestMapping("pay_ad")
	public String pay_ad(HttpSession session) { 
		return "ad/pay_ad";
	}	
	
	@RequestMapping("room_ad")
	public String room_ad(HttpSession session) { 
		return "ad/room_ad";
	}	
	
	@RequestMapping("stu_ad")
	public String stu_ad(HttpSession session) { 
		return "ad/stu_ad";
	}	
	
	@RequestMapping("tea_ad")
	public String tea_ad(HttpSession session) {
		return "ad/tea_ad"; 
	}
	
	@RequestMapping("user_ad")
	public String user_ad(HttpSession session) { 
		return "ad/user_ad"; 
	}
	
	@RequestMapping("loginout")
	public String loginout(HttpSession session) { 
		session.invalidate();
		return "loginout"; 
	}
	
	@RequestMapping("class_stu")
	public String class_stu(HttpSession session) { 
		return "stu/class_stu"; 
	}
	
	@RequestMapping("pw_stu")
	public String pw_stu(HttpSession session) { 
		return "stu/pw_stu"; 
	}
	
	@RequestMapping("mark_stu")
	public String mark_stu(HttpSession session) { 
		return "stu/mark_stu"; 
	}
	
	@RequestMapping("lesson_stu")
	public String lesson_stu(HttpSession session) { 
		return "stu/lesson_stu"; 
	}
	
	@RequestMapping("noti_stu")
	public String noti_stu(HttpSession session) { 
		return "stu/noti_stu"; 
	}
	@RequestMapping("pay_stu")
	public String pay_stu(HttpSession session) { 
		return "stu/pay_stu"; 
	}
	@RequestMapping("info_stu")
	public String info_stu(HttpSession session) { 
		return "stu/info_stu"; 
	}
	@RequestMapping("stu_tea")
	public String stu_tea(HttpSession session) { 
		return "tea/stu_tea"; 
	}
	
	@RequestMapping("pw_tea")
	public String pw_tea(HttpSession session) { 
		return "tea/pw_tea"; 
	}
	
	@RequestMapping("noti_tea")
	public String noti_tea(HttpSession session) { 
		return "tea/noti_tea"; 
	}
	
	@RequestMapping("cla_tea")
	public String cla_tea(HttpSession session) { 
		return "tea/cla_tea"; 
	}
	
	@RequestMapping("mark_tea")
	public String mark_tea(HttpSession session) { 
		return "tea/mark_tea"; 
	}
	
	@RequestMapping("info_tea")
	public String info_tea(HttpSession session) { 
		return "tea/info_tea"; 
	}
	
}
