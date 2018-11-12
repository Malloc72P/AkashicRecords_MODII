package com.controller.subSection;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aka_image.dao.ImageDAO;
import com.aka_image.domain.ImageCommand;
import com.util.FileUtil;

import constSet.*;


@Controller()
public class ImageUploadProcController {
	
	@Autowired
	private ImageDAO dao;
	
	@RequestMapping("/hello/imageUploadProc.do")
	public ModelAndView requestProcessor(
											HttpServletRequest 	request,
											HttpServletResponse response,
											@RequestParam("file") MultipartFile uploadedFile
										) {
		System.out.println("________________________________________________________");
		System.out.println("ImageUploadProcController.requestProcessor >>> 메서드 호출됨");
		
		ModelAndView mav = new ModelAndView("subSection/imageUploadProc");
		response.setHeader("Access-Control-Allow-Origin","*");
		
		ServletContext application = request.getServletContext();
		
		String absol_path   = application.getRealPath("").replace("\\", "/");
		String img_Dir_path = absol_path+MainConst.IMG_ROOT_PATH+MainConst.IMG_POST_PATH;
		
		String fileName     = "";
		
		if(!uploadedFile.isEmpty()) {
			try {
				fileName = FileUtil.rename( uploadedFile.getOriginalFilename() );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("ImageUploadProcController.requestProcessor >>> fileName : "+fileName);
		}
				
		String img_path     = img_Dir_path + fileName;
		
//		System.out.println("ImageUploadProcController.requestProcessor >>> absol_path   : "+absol_path  );
//		System.out.println("ImageUploadProcController.requestProcessor >>> img_Dir_path : "+img_Dir_path  );
//		System.out.println("ImageUploadProcController.requestProcessor >>> img_path     : "+img_path  );
		
		File file = new File(img_path);
		
		try {
			uploadedFile.transferTo(file);
			
			System.out.println("ImageUploadProcController.requestProcessor >>> 이미지 파일 크기 : "+String.format("%.2f", file.length()/(1024.0) )+"KB" );
			
			/*******************************
			 * 		DB에 데이터 저장 시도
			 *******************************/
			int    img_id       = dao.getNewImageNum();
			String img_url		= MainConst.IMG_ROOT_PATH + MainConst.IMG_POST_PATH+fileName;
			String img_size		= String.format("%.2f", file.length()/(1024.0));
			String img_ref_type	= "POST";
			String img_name		= fileName;
			
			System.out.println("DA전 데이터 패러미터 체크");
			
//			System.out.println("img_id	     : "+img_id);
//			System.out.println("img_url      : "+img_url);
//			System.out.println("img_size     : "+img_size);
//			System.out.println("img_ref_type : "+img_ref_type);
//			System.out.println("img_name     : "+img_name);
			System.out.println();
			ImageCommand data = new ImageCommand();
			
			data.setImg_id(img_id);
			data.setImg_name(img_name);
			data.setImg_ref_type(img_ref_type);
			data.setImg_size(img_size);
			data.setImg_url(img_url);
			
			Boolean insertChecker = dao.insertImage(data);
			System.out.println("DB입력 성공여부 : "+insertChecker);
			
			mav.addObject("insertChecker",insertChecker);
			System.out.println(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
			String serverAddr = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			if(insertChecker) {
				String fileRealUrl = serverAddr+request.getContextPath()+"/"+MainConst.IMG_ROOT_PATH+MainConst.IMG_POST_PATH+fileName;
				System.err.println("fileRealUrl : "+fileRealUrl);
				mav.addObject("img_url",fileRealUrl);
				mav.addObject("img_name",img_name);
				mav.addObject("img_id", dao.getImgIdByName(img_name));
			}
			else {
				mav.addObject("img_url","noData");
				
			}
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		File file = new File(application.getRealPath());
		
		
		System.out.println("________________________________________________________");
		return mav;
	}
}
