package com.aka_image.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.aka_image.domain.ImageCommand;

public class Impl_ImageDAO extends SqlSessionDaoSupport implements ImageDAO {

	@Override
	public boolean insertImage(ImageCommand data) {
		// TODO Auto-generated method stub
		int insertResult = (Integer)getSqlSession().insert("insertImage",data);
		if(insertResult != 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int getNewImageNum() {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getMaxImageNum")+1;
	}

	@Override
	public String getImgUrlById(int img_id) {
		// TODO Auto-generated method stub
		return (String)getSqlSession().selectOne("getImgUrlById",img_id);
	}
	
	
	
}
