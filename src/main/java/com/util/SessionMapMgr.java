package com.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionMapMgr {
	private static SessionMapMgr ssnMapInstance	=	null;
	private Map<String, HttpSession> sessionMap	=	null;
	
	private SessionMapMgr() {
		sessionMap	=	new HashMap<String, HttpSession>();
	}
	
	public static SessionMapMgr getInstance() { 
		if( SessionMapMgr.ssnMapInstance == null ) {
			SessionMapMgr.ssnMapInstance	=	new SessionMapMgr();
			return SessionMapMgr.ssnMapInstance;
		}
		else{
			return SessionMapMgr.ssnMapInstance; 
		}
	}
	public Map<String, HttpSession> getSessionMap() {
		return this.sessionMap;
	}
	
	
}
