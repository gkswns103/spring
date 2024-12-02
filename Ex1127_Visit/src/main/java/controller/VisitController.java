package controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.BCryptPwd;
import common.Common;
import common.Paging;
import dao.VisitDAO;
import vo.VisitVO;

@Controller
public class VisitController {
	
	@Autowired //자동 주입
	ServletContext application;
	
	VisitDAO visit_dao;
	BCryptPwd bcp;
	
	public VisitController( VisitDAO visit_dao, BCryptPwd bcp ) {
		this.visit_dao = visit_dao;
		this.bcp = bcp;
	}
	
	@RequestMapping(value= {"/","list.do"})
	public String list(Model model, Integer page) {
		
		int nowPage = 1;
		if( page != null) {
			nowPage = page;
		}
		
		int start = (nowPage - 1) * Common.Visit.BLOCKLIST + 1;
		int end = start + Common.Visit.BLOCKLIST - 1;
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start",start);
		map.put("end",end);
		
		List<VisitVO> list = visit_dao.select_list(map);
		
		int row_total = visit_dao.getRowTotal();
		
		//페이지 메뉴
		String pageMenu = Paging.getPaging("list.do", nowPage, row_total, Common.Visit.BLOCKLIST,Common.Visit.BLOCKPAGE);
		
		model.addAttribute("pageMenu", pageMenu);
		model.addAttribute("list", list);
		
		return Common.Visit.VIEW_PATH + "visit_list.jsp";
	}
	
	@RequestMapping("/insert_form.do")
	public String insert_form() {
		return Common.Visit.VIEW_PATH + "visit_insert_form.jsp";
	}
	
	@RequestMapping("/insert.do")
	public String insert(VisitVO vo,HttpServletRequest request) {
		String web_path = "resources/upload/";
		String save_path = application.getRealPath(web_path);
		
		System.out.println(save_path);
		MultipartFile photo = vo.getPhoto();
		String filename = "no_file";
		
		if(!photo.isEmpty()) {
			filename = photo.getOriginalFilename();
			
			File saveFile = new File(save_path,filename);
			
			if(!saveFile.exists()) {
				saveFile.mkdirs();
			}
			else {
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s",time,filename);
				saveFile = new File(save_path,filename);
			}
			
			try {
				photo.transferTo(saveFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		vo.setFilename(filename);
		
		String encodePwd = bcp.encryption(vo.getPwd());
		
		vo.setPwd(encodePwd);
		vo.setIp(request.getRemoteAddr());
		int res = visit_dao.insert_visit(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/update_form.do")
	public String update_form(Model model, int idx) {
		VisitVO vo = visit_dao.select_one(idx);

		model.addAttribute("vo", vo);
			
		return Common.Visit.VIEW_PATH + "visit_update_form.jsp";

	}
	
	@RequestMapping("/update.do")
	public String update(VisitVO vo) {
		int res = visit_dao.update_visit(vo);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete.do", produces="application/json;charset=UTF-8")
	@ResponseBody // return값을 포워딩이 아닌 콜백으로 돌려보내는 어노테이션
	public String delete(VisitVO vo, Integer page, String c_pwd) {
		
		int nowPage = 1;
		if( page != null) {
			nowPage = page;
		}
		
		//비밀번호 비교
		boolean isValid = bcp.decryption(vo.getPwd(), c_pwd);
		int res = 0;
		String result = "아니요";
		if(isValid) {
			res = visit_dao.delete_visit(vo.getIdx());
		}
		else {
			String pwdCheck = "no_pwd";
			return String.format("[{'res':'%s'}]",pwdCheck);
		}
		
		
		if(res !=0) {
			result = "네";
		}
		
		String resultStr =  String.format("[{'res':'%s'}]",result);
		
		return resultStr; 
	}
	
	//수정을 위한 비밀번호 체크
	@RequestMapping("/update_password_chk.do")
	@ResponseBody
	public String updPwdCheck(String pwd, String c_pwd) {
		boolean isValid = bcp.decryption(pwd, c_pwd);
		
		String result = "no_pwd";
		
		if(isValid) {
			result="yes_pwd";
		}
		return result;
	}
}
