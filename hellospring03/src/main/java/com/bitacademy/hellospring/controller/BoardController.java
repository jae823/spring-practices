package com.bitacademy.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  @auther BIT_R39
 *  
 *  @ReuestMapping
 *  Method(Handler) 단독매핑
 */


@Controller
public class BoardController {
	@ResponseBody
	@RequestMapping("/board/write")
	public String write() {
		return "BoardController:write()";
	}
	
	@ResponseBody
	@RequestMapping("/board/view")
	public String view(int no) {
		return "BoardController:view("+ no +")";
	}
	
	@ResponseBody
	@RequestMapping("/board/view/{no}")
	public String view2(@PathVariable("no") int boardNo) {
		return "BoardController:view("+ boardNo +")";
	}
}
