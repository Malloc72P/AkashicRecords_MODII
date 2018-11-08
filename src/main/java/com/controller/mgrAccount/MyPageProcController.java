package com.controller.mgrAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserMetadataCommand;
import com.util.SessionMapMgr;

@Controller
public class MyPageProcController {
	@Autowired
	private UserDAO dao ;
	
	
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/hello/myPageProc.do")
	public ModelAndView requestProcessor( HttpServletRequest request
										 ,HttpServletResponse response
										 ,@RequestParam(value="ssnId", defaultValue="") String ssnId
            							 ,@RequestParam(value="pw1" ,defaultValue=""  ) String pw1
            							 ,@RequestParam(value="pw2" ,defaultValue=""  ) String pw2
            							 ,@RequestParam(value="pw3" ,defaultValue=""  ) String pw3
										 ,@RequestParam(value="nickname" ,defaultValue=""  ) String user_nickname
										 ,@RequestParam(value="profImg"   ,defaultValue="-1") int img_id){
		System.out.println("_____________________________________________");
		System.out.println("RegisterProcController.requestProcessor >>> 매서드 호출됨");
		response.setHeader("Access-Control-Allow-Origin","*");
		ModelAndView mav = new ModelAndView("mgr_account/myPageProc");
		boolean updateChecker = false;
		String	errorCode		=	"noData";
		//######
		HttpSession session = null;
		if( !ssnId.equals("") ) {
			session	=	SessionMapMgr.getInstance().getSessionMap().get(ssnId);
		}else {
			mav.addObject("updateChecker",false);
			mav.addObject("errorCode","invalidApproach");
			return mav;
		}
		//######		
		String user_email = "";
		
		
		System.out.println("RegisterProcController.requestProcessor >>> pw1 : "+pw1);
		System.out.println("RegisterProcController.requestProcessor >>> pw2 : "+pw2);
		System.out.println("RegisterProcController.requestProcessor >>> pw3 : "+pw3);
		System.out.println("RegisterProcController.requestProcessor >>> user_nickname : "+user_nickname);
		System.out.println("RegisterProcController.requestProcessor >>> img_id : "+img_id);
		
		if( session.getAttribute("email") == null ) {
			//비정상접근
			mav.addObject("updateChecker",updateChecker);
			mav.addObject("errorCode","UnauthorizedAccess");
		}
		else {
			user_email = (String)session.getAttribute("email");
			if(user_email.equals("")) {
				mav.addObject("updateChecker",updateChecker);
				mav.addObject("errorCode","UnauthorizedAccess");
			}
		}
		
		UserMetadataCommand newUser = new UserMetadataCommand();
		UserMetadataCommand prevUser	=	dao.getUserMetaDataByEmail(user_email);
		newUser.setUser_email(user_email);
		
		if(pw1.equals(pw2) && pw2.equals(pw3)) {
			System.out.println("패스워드 수정 안함");
			System.out.println("prevUser.getUser_password() : "+prevUser.getUser_password());
			newUser.setUser_password( prevUser.getUser_password() );
		}
		else {
			System.out.println("패스워드 수정하는 경우");
			if( pw2.equals(pw3) ) {//새로 입력한 두 패스워드가 일치하는 경우
				newUser.setUser_password( pw2 );
				System.out.println("newUser.userPassword : "+newUser.getUser_password());
			}
			else {
				mav.addObject("updateChecker",updateChecker);
				mav.addObject("errorCode","pwIsNotCorrect");
			}
		}
		newUser.setUser_nickname(user_nickname);
		newUser.setImg_id(img_id);
				
		
		System.out.println("######");
		System.out.println("newUser : "+newUser.getUser_email());
		System.out.println("newUser : "+newUser.getUser_nickname());
		System.out.println("newUser : "+newUser.getUser_password());
		System.out.println("newUser : "+newUser.getImg_id());
		System.out.println("######");
		updateChecker = dao.myPageUpdator(newUser);
		
		mav.addObject("updateChecker",updateChecker);
		System.out.println("_____________________________________________");
		return mav; 
	}

}
