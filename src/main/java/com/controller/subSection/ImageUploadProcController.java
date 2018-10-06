package com.controller.subSection;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.util.FileUtil;

import constSet.*;


@Controller()
public class ImageUploadProcController {
	
	@RequestMapping("/hello/imageUploadProc.do")
	public ModelAndView requestProcessor(
											HttpServletRequest request,
											@RequestParam("file") MultipartFile uploadedFile
										) {
		System.out.println("________________________________________________________");
		System.out.println("ImageUploadProcController.requestProcessor >>> 메서드 호출됨");
		
		ModelAndView mav = new ModelAndView("subSection/imageUploadProc");
		System.out.println("ImageUploadProcController.requestProcessor >>> uploadedFile.getName() : "+uploadedFile.getName());
		System.out.println("ImageUploadProcController.requestProcessor >>> uploadedFile.getOriginalFilename() : "+uploadedFile.getOriginalFilename());
		
		ServletContext application = request.getServletContext();
		
		String absol_path   = application.getRealPath("").replace("\\", "/");
		String img_Dir_path = absol_path+MainConst.IMG_ROOT_PATH;
		
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
		
		System.out.println("ImageUploadProcController.requestProcessor >>> absol_path   : "+absol_path  );
		System.out.println("ImageUploadProcController.requestProcessor >>> img_Dir_path : "+img_Dir_path  );
		System.out.println("ImageUploadProcController.requestProcessor >>> img_path     : "+img_path  );
		
		File file = new File(img_path+uploadedFile.getOriginalFilename());
		
		try {
			uploadedFile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		File file = new File(application.getRealPath());
		
		
		System.out.println("________________________________________________________");
		return mav;
	}
}
