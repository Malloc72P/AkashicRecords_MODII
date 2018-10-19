package com.aka_series.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;


public class Impl_SeriesDAO extends SqlSessionDaoSupport implements SeriesDAO{

	@Override
	public boolean insertSeries(String series_title) {
		// TODO Auto-generated method stub
		int insertChecker = getSqlSession().insert("insertAkaSeries", series_title);
		if( insertChecker != 0 ) {
			return true;
		}
		else return false;
	}
	
}
