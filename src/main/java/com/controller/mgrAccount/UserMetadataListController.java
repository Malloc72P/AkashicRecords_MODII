package com.controller.mgrAccount;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserMetadataCommand;

@Controller
public class UserMetadataListController {
	
	@Autowired
	private UserDAO dao ;
	
	

	@RequestMapping("/hello/getUsersMetadata.do")
	public ModelAndView requestProcessor( HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView("mgr_account/userMetadataList");
		
		List<UserMetadataCommand> metadatas = dao.getUsersMetadata();
		
		System.out.println("_____________________________________"); 
		for( UserMetadataCommand item : metadatas ) {
			System.out.println("data : "+item.getUser_email());
			System.out.println("data : "+item.getImg_id());
			System.out.println("data : "+item.getUser_nickname());
			System.out.println("data : "+item.getValidation());
			System.out.println("data : "+item.getAdmin_level());
			System.out.println("###");
		}
		System.out.println("_____________________________________");
		mav.addObject("metadatas", metadatas);
		return mav; 
	}
}
