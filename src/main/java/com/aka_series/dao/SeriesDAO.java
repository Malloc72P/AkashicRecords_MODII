package com.aka_series.dao;

import java.util.List;

import com.aka_series.domain.SeriesCommand;

public interface SeriesDAO {
	public boolean 				insertSeries(String series_title);
	public List<SeriesCommand> 	getSeriesList();
	public boolean				updateSeriesAndImg(SeriesCommand data);
	public boolean				updateSeries(SeriesCommand data);
}
