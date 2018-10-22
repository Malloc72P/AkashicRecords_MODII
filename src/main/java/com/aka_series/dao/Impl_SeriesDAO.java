package com.aka_series.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.aka_series.domain.SeriesCommand;


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

	@Override
	public List<SeriesCommand> getSeriesList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getSeriesList");
	}

	@Override
	public boolean updateSeriesAndImg(SeriesCommand data) {
		// TODO Auto-generated method stub
		int updateResult = getSqlSession().update("updateSeriesAndImg", data);
		if(updateResult != 0) {
			return true;
		}
		else return false;
	}
	@Override
	public boolean updateSeriesPostcount(int series_id) {
		// TODO Auto-generated method stub
		int updateResult = getSqlSession().update("updateSeriesPostcount", series_id);
		if(updateResult != 0) {
			return true;
		}
		else return false;
	}
	@Override
	public boolean updateSeriesViewcount(int series_id) {
		// TODO Auto-generated method stub
		int updateResult = getSqlSession().update("updateSeriesViewcount", series_id);
		if(updateResult != 0) {
			return true;
		}
		else return false;
	}
	
}
