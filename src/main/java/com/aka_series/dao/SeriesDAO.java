package com.aka_series.dao;

import java.util.List;

import com.aka_series.domain.SeriesCommand;

public interface SeriesDAO {
	public boolean 				insertSeries(String series_title);
	public List<SeriesCommand> 	getSeriesList();
	public SeriesCommand		getSeriesById(int series_id);
	public boolean				updateSeriesAndImg(SeriesCommand data);
	public boolean				updateSeriesPostcount(int series_id);
	public boolean				updateSeriesViewcount(int series_id);
	public boolean				deleteSeriesById(int series_id);
}
