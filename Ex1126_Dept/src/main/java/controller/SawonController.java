package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import dao.SawonDAO;
import vo.SawonVO;

@Controller
public class SawonController {
	
	SawonDAO sawon_dao;
	
	public SawonController( SawonDAO sawon_dao ) {
		this.sawon_dao = sawon_dao;
	}
	
	@RequestMapping("/sawon_list.do")
	public String select_list(Model model, int deptno) {
		List<SawonVO> list = sawon_dao.select_list(deptno);
		
		model.addAttribute("list", list);
		
		return Common.Sawon.VIEW_PATH + "sawon_list.jsp";
	}
	
	@RequestMapping("/sawon_update_form.do")
	public String update(Model model,int sabun) {
		SawonVO vo = sawon_dao.select_sawon(sabun);
		
		model.addAttribute("vo", vo);
		
		return Common.Sawon.VIEW_PATH + "sawon_update.jsp";
	}
	
	@RequestMapping("/update_sawon.do")
	public String update2(Model model,SawonVO vo) {
		int res = sawon_dao.update_sawon(vo);

		return"redirect:sawon_list.do?deptno="+vo.getDeptno();
	}
	
	@RequestMapping("/sawon_delete_form.do")
	public String delete_sawon(Model model, SawonVO vo) {
		int res = sawon_dao.delete_sawon(vo.getSabun());
		
		return "redirect:sawon_list.do?deptno="+vo.getDeptno();
	}
	
	@RequestMapping("/sawon_insert_form.do")
	public String insert() {
		return Common.Sawon.VIEW_PATH + "sawon_insert.jsp";
	}
	
	@RequestMapping("/insert_sawon.do")
	public String insert_sawon(Model model, SawonVO vo) {
		int res = sawon_dao.insert_sawon(vo);
	
		return "redirect:sawon_list.do?deptno="+vo.getDeptno();
	}
}
