package com.bitacademy.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.guestbook.repository.GuestbookRepository;
import com.bitacademy.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	@RequestMapping({"","/"})
	public String index(Model model) {
		List<GuestbookVo> list = guestbookRepository.findAll();
		System.out.println(list);
		model.addAttribute("list", list);
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping("/add")
	public String add(Model model, GuestbookVo vo) {
		System.out.println(vo);
		guestbookRepository.insert(vo);
		List<GuestbookVo> list = guestbookRepository.findAll();
		System.out.println(list);
		model.addAttribute("list", list);
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping("/deleteform/{no}")
	public String deleteform(Model model,@PathVariable("no") String no) {
		model.addAttribute("no", no);
		return "/WEB-INF/views/deleteform.jsp";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model,  GuestbookVo vo) {
		System.out.println(vo);
		guestbookRepository.delete(vo);
		List<GuestbookVo> list = guestbookRepository.findAll();
		System.out.println(list);
		model.addAttribute("list", list);
		return "/WEB-INF/views/index.jsp";
	}
	
}
