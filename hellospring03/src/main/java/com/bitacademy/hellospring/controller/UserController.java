package com.bitacademy.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @authoer Bit_R39
 * 
 * @RequestMapping
 * Method(Handler) + Type(Class) 단독매핑
 */

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value = "/joinform", method=RequestMethod.GET)
	public String join() {
		return "/WEB-INF/views/join.jsp";
	}
	
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public String join(String name, String email, String password) {
		System.out.println(name+":"+email+":"+password);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		System.out.println(vo);
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(String name) {
		System.out.println(name);
		return "UserController:update(...)";
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/update")
//	public String update(@RequestParam("name") String name) {
//	    만약 name이라는 이름으로 파라미터가 전달되지 않으면 400 bad Request @RequestParam("name")에서 nameParam이 없으면 400
//	    required=false이면 없어도 400 안남
//      defaultValue="" 로값이 없을경우 설정가능
//      @RequestParam("name", required=false, defaultValue="")
//		System.out.println(name);
//		return "UserController:update(...)";
//	}
}
