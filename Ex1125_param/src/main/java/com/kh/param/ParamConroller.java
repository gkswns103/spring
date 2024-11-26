package com.kh.param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vo.PersonVO;

@Controller
public class ParamConroller {
	
	public static final String PATH = "/WEB-INF/views/person/";
	
	
	@RequestMapping(value={"/insert_form.do","/"})
	public String insertForm(Model model) {
		
		return PATH + "insert_form.jsp";
	}
	
	@RequestMapping("insert_single.do")
	public String insertSingle(String name,String tel,int age,Model model) {
		PersonVO vo = new PersonVO();
		vo.setName(name);
		vo.setTel(tel);
		vo.setAge(age);
		
		model.addAttribute("vo",vo);
		
		return PATH + "insert_result.jsp";
	}
	
	@RequestMapping("insert_vo.do")
	public String insertVO(Model model,PersonVO vo) {
		model.addAttribute("vo", vo);
		
		return PATH + "insert_result.jsp";
		
	}
}
