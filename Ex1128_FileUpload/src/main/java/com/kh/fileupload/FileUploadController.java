package com.kh.fileupload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import vo.PhotoVO;

@Controller
public class FileUploadController {
	
	public static final String VIEW_PATH = "/WEB-INF/views/";
	
	@Autowired //자동 주입
	ServletContext application;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value={"/","/insert_form.do"})
	public String insert_form() {
		
		return VIEW_PATH + "insert_form.jsp";
	}
	
	
	@RequestMapping("/upload.do")
	public String upload(Model model, PhotoVO vo) {
		String webPath = "/resources/upload/"; //상대경로
		String savePath = application.getRealPath(webPath); //절대경로
		System.out.println(savePath);
		//업로드를 위한 파일정보
		MultipartFile photo = vo.getPhoto();
		String filename = "no_file";
		
		if( !photo.isEmpty() ) {
			filename = photo.getOriginalFilename();
			
			//저장할 파일의 경로
			File saveFile = new File(savePath,filename);
			
			if(!saveFile.exists()) {
				saveFile.mkdirs();
			}
			else {
				//동일한 이름의 파일이 존재한다면 현재 업로드 시간을 붙여서 중복을 방지
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s",time,filename);
				saveFile = new File(savePath,filename);
			}
			//파일을 절대 경로에 생성
			try {
				photo.transferTo(saveFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		vo.setFilename(filename);
		
		model.addAttribute("vo",vo);
		return VIEW_PATH + "input_result.jsp";
	}
}
