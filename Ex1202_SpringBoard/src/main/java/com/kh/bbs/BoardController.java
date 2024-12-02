package com.kh.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import dao.BoardDAO;
import util.Paging;
import vo.BoardVO;


@Controller
public class BoardController {

	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	BoardDAO board_dao;
	
	public void setBoard_dao(BoardDAO board_dao) {
		this.board_dao = board_dao;
	}
	
	//전체 게시글 보기
	@RequestMapping(value= {"/","/list.do"})
	public String list(Model model, String search, String search_text,Integer page) {
		
		int nowPage = 1;
		
		if( page != null) {
			nowPage = page;
		}
		
		//한 페이지에 표시할 게시글의 시작과 끝번호를 계산
		int start = (nowPage-1) * Common.Board.BLOCKLIST + 1;
		int end = start + Common.Board.BLOCKLIST - 1;
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("end",end);
		
		//검색어 관련
		if( search != null && !search.equals("all") ) {
			switch(search) {  
				case "name_subject_content":
					map.put("name",search_text);
					map.put("subject",search_text);
					map.put("content",search_text);
					break;
				case "name":
					map.put("name",search_text);
					break;
				case "subject":
					map.put("subject",search_text);
					break;
				case "content":
					map.put("content",search_text);
					break;
			}
		}
		
		List<BoardVO> list = null;

		
		
		list = board_dao.selectList(map);
		
		int row_total = board_dao.getRowTotal( map );
		
		String search_param = String.format("search=%s&search_text=%s",search,search_text);
		
		String pageMenu = Paging.getPaging("list.do", nowPage, row_total, search_param, Common.Board.BLOCKLIST, Common.Board.BLOCKPAGE);
		
		
		model.addAttribute("list",list);
		model.addAttribute("pageMenu",pageMenu);
		
		session.removeAttribute("show");
		
		return Common.Board.VIEW_PATH + "board_list.jsp";
		
	}
}
