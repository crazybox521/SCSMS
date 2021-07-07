package com.scsms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.scsms.pojo.Grade;
import com.scsms.pojo.Mark;
import com.scsms.pojo.Payment;
import com.scsms.pojo.Student;
import com.scsms.pojo.User;
import com.scsms.service.GradeService;
import com.scsms.service.MarkService;
import com.scsms.service.PaymentService;
import com.scsms.service.StudentService;

@Controller
@RequestMapping("payController")
public class PayController {
	
	 private final String APP_ID = "2021000117658198";
	    private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCa3w1a3fPELUkBXq/Z/Y3bg7vMj3n/PgQjzlKsk2UMFSvhj595T07Ky7CseloneCob+ij4bxr/ILhN3zWb5RyjA8g0LvCJKDAnOjONV8T6PPU/5v0I4o9L0HL8Pt32QHNrAGPXmOMNypuxC60OvWIHbCCiNuu1qxlxxo8D3AybtB9xF44PirP6m4qx3ALP3shQwLofvXzYhhao8JVC5CbX+vZOXOjxJ4wSGvB2EAIyVDAWWKNMQi3bAbcDrqY8PCeZPCEMeQfJSt9IorQCk8+coVP65T9mgvJh4BFKdC0PT8IWtwX6TwOUhuJYe8q1OB+7Wo9owI2HbGWC80midiUNAgMBAAECggEAGaAmuY5YM3G6zrs4Jb3KpzPY8zQaX52HWdVd4KhS4/js1dnJ7WyuAOhLR37HRBih/xcZiGH5+ZUUae4xFKaIYoDRs7DApDE+Jn9+4DhRo4ZKnlQ+MiYqx8SqAgr09zYa4u2hl574kuOfd2ATNoknm0rM64pi3iB9iVHtgBn9DBYyvxPPqyOJflsmxrKQe6h9Xz2hu9PVxJk8aK++lVc7yaC2BTs+zjMyOevP3fhRhL7dIfewRdctNZuFsoHolD75zwAWqFZa8a0y+vqnmjCaLHTt13iloheHlgOB7zNQFujX+3Vh21QAGq+96qTO2nu7nQ+EQ9B5hIPSN9ipn5vGgQKBgQDwNMQghIdhoiRJKVgyzA22Oay5ueQ2hFo0kL0WMuoDwDgBouZCtDiAwcJzKC4jSff3zrPIsckANbqujkXjm4RPoS8hewzrC5yMyKOiHWW+EyZ65ShyEjNaU8DwLvlsh6Vfbcxa6J3DEXxThbHBjNzDvB57uVdpRWdDUh5/GWnYuQKBgQClDeZ8NFhLN/d3IDt/mjiKrwooZtm1jqWsmgn23lvATHEX2KplbMM38/Hlaya46/CGcsd09eUKNz/HQQKDl5GlNpz3Dy3mWTQ1aUtnJUsMyl/hkvzMsrasCbRsrpJ4hZDqWUemnTdbLBMjBbeWKJRAT20mNZQTbQxvQOknQmGc9QKBgCz5xEtOZUbrpB2qxx/jwyF+lQ/FdaybhYRYIX95kwWWxSXRSYnt0rKRFQaY3GKxI+PbaSBH4ZnPdZ/fdciTQufiB0AemSGdb/eQTcs58+XuEgNqyvLo44KjMfoG4Z7nmxhOJ7hR29A9gujknI7gACoKHC8QWgSVASvCwYEM0hvJAoGAUWpM4cFUOC4wJBPDJYsmDsqOwSUSiKsHb98/wQUnzvAl7PRUANmoZgYvsNGs9RR0vW0Kk9fjFanxDNE1c0GldxA8VuWm53Pi++GnbwaHQZTlvLgreLFCyBO1atKlr0t7Ew3c+QOd1ZwlCGZ+i1QS5LIJDgrpsrdcpdgn6dSln60CgYEAs98s8Of3UmTQEmLjp5Kfs03UQUF6kcDuSMLpKkMBff9cSBMNI//fmHvkMcnfkT3x4SBAzgPYn+wo1uU3r9xiKs0NCpFv9ycKafH8fR+UeVPFncDn1ncaJbk4Y6huI6wD+7OdjH4AVaN4B1jKAaDNjnqzen3t7SdCYXtLwYi/Jto=";
	    private final String CHARSET = "UTF-8";
	    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgqGT09nFT0TCUU/zFMjbvAOgyYwCaJuwSbib1HKZuv5GZ/acvxAqq9WTAlbYwLYEJks5B9aDIDbHk9LmSgW6A4CoKXfv3zDOSgdgE4clGwHk5jUR+FaPcs//oO5ytf6btHYW0tmiLYBs3DbIW2HlSocaBZfuqwyl+aDUttn/CMA5VUswcDBTGNSXlxy74TfnIPCWIF6BSHbVqIaipXmbFGXD1mRm+4ELWGPuO1R/9H0UxK3GLrbYjRkgeeYkCPU0Rda3n7omGU/bqLOOJ5RJwbQt2lpqS/w4xhucnlfAJ7zcndYBTETlVaD2rQRz0S2KqywFWoAmiiBcriyyHuegdQIDAQAB";
	    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
	    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
	    private final String FORMAT = "JSON";
	    //签名方式
	    private final String SIGN_TYPE = "RSA2";
	    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
	    private final String NOTIFY_URL = "";
	    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
	    private final String RETURN_URL = "http://localhost:8080/SCSMS/payController/returnUrl";
	    
	    
	    @Resource
		private PaymentService service;
	    
	    @Resource
		private GradeService gservice;
	    
	    @Resource
		private StudentService sservice;
	    
	    @Resource
		private MarkService markservice;
	    
	    
	    @RequestMapping("alipay")
	    public void alipay(HttpServletResponse httpResponse,HttpSession session) throws IOException {
	        //实例化客户端,填入所需参数
	    	String payid=(String) session.getAttribute("payid");
	    	Payment p = service.queryObjectp(payid);
	        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
	        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
	        //在公共参数中设置回跳和通知地址
	        request.setReturnUrl(RETURN_URL);
	        request.setNotifyUrl(NOTIFY_URL);

	        //商户订单号，商户网站订单系统中唯一订单号，必填
	        //生成随机Id
	        String out_trade_no = payid;
	        //付款金额，必填
	        String total_amount =Integer.toString(p.getNumber());
	        //订单名称，必填
	        String subject =p.getGrade().getGradeName();
	        //商品描述，可空
	        String body = "欢迎报名"+p.getGrade().getGradeName()+"";
	        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
	                + "\"total_amount\":\""+ total_amount +"\","
	                + "\"subject\":\""+ subject +"\","
	                + "\"body\":\""+ body +"\","
	                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	        String form = "";
	        try {
	            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
	        } catch (AlipayApiException e) {
	            e.printStackTrace();
	        }
	        httpResponse.setContentType("text/html;charset=" + CHARSET);
	        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
	        httpResponse.getWriter().flush();
	        httpResponse.getWriter().close();
	    } 
	
	@RequestMapping("returnUrl")
	public String returnUrl(HttpServletRequest request, HttpServletResponse response,HttpSession session)
	        throws IOException, AlipayApiException {
	    System.out.println("=================================同步回调=====================================");
	    // 获取支付宝GET过来反馈信息
	    Map<String, String> params = new HashMap<String, String>();
	    Map<String, String[]> requestParams = request.getParameterMap();
	    for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
	        String name = (String) iter.next();
	        String[] values = (String[]) requestParams.get(name);
	        String valueStr = "";
	        for (int i = 0; i < values.length; i++) {
	            valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
	        }
	        // 乱码解决，这段代码在出现乱码时使用
	        valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
	        params.put(name, valueStr);
	    }
	
	    System.out.println(params);//查看参数都有哪些
	    boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
	    //验证签名通过
	    if(signVerified){
	        // 商户订单号
	        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
	        
	        // 支付宝交易号
	        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
	        
	        // 付款金额
	        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
	        Payment p = service.queryObjectp(out_trade_no);
	        p.setAlipay(trade_no);
	        p.setState("已付款");
	        System.out.println("商户订单号="+out_trade_no);
	        System.out.println("支付宝交易号="+trade_no);
	        System.out.println("付款金额="+total_amount);
	        Mark m=new Mark();
			m.setGrade(p.getGrade());
			m.setStudent(p.getStudent());
			markservice.insert(m);
	        service.update(p);
	        gservice.chooseLesson(p.getStudent().getId(), p.getGrade().getId());
	        //支付成功，修复支付状态
	       // service.updateById(Integer.valueOf(out_trade_no));
	        return "ok";//跳转付款成功页面
	    }else{
	        return "error";//跳转付款失败页面
	    }
	    
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(Grade obj,HttpServletResponse response,HttpSession session) throws IOException {
		User user=(User)session.getAttribute("student");
		Payment p=new Payment();
		int price=gservice.queryObject(obj.getId()).getLesson().getPrice();
		p.setNumber(price);
		p.setGrade(obj);
		String payid=UUID.randomUUID().toString();
		p.setPayid(payid);
		session.removeAttribute("payid");
		session.setAttribute("payid", payid);
		p.setStudent(sservice.queryObject(user.getId()));
		p.setState("未付款");
		int i = service.insert(p);
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
	
	@RequestMapping("queryPageByStudent")
	@ResponseBody
	public Map<String,Object> queryPageByStudent(String page,String limit,HttpServletRequest request,HttpSession session){
		User user=(User)session.getAttribute("student");
		Student stu=sservice.queryObject(user.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage =Integer.parseInt(page);
		int size = Integer.parseInt(limit);
		int offset = (currentPage - 1)*size;
		int count = service.queryCountByStudent(stu.getId());
		List<Payment> data = service.queryPageByStudent(offset, size,stu.getId());
		map.put("data", data);
		map.put("count", count);
		map.put("msg", "succes");
	    map.put("code",0);
		return map;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(String page,String limit,HttpServletRequest request){
		String key=request.getParameter("key[id]");
		Map<String, Object> map = new HashMap<String, Object>();
		if(key!=null) {
			List<Payment> list =service.searchPay(key);
			if(list!=null && !list.isEmpty())
			{
			map.put("data", list);
			map.put("count", 1);
			}
		}else {
			int currentPage =Integer.parseInt(page);
			int size = Integer.parseInt(limit);
			int offset = (currentPage - 1)*size;
			int total = service.queryCount();
			List<Payment> list = service.queryPage(offset, size);
			map.put("data", list);
			map.put("count", total);
			
		}
		    map.put("code",0);
		return map;
	}
	

	
	
}
