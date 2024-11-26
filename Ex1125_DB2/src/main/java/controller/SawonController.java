package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SawonDAO;
import vo.SawonVO;

@Controller
public class SawonController {

	SawonDAO sawon_dao;
	
	public SawonController( SawonDAO sawon_dao ) {
		this.sawon_dao = sawon_dao;
		System.out.println("sawon controller");
	}
	
	public static final String PATH ="/WEB-INF/views/sawon/";
	
	@RequestMapping(value= {"/","/list_sawon.do"})
	public String list( Model model ) {
		List<SawonVO> list = sawon_dao.selectList();
		model.addAttribute("list", list);
		
		return PATH + "sawon_list.jsp";
	}
	
}
