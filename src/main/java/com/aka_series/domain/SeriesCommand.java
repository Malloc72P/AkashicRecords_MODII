package com.aka_series.domain;

public class SeriesCommand {
	private	int		series_id;
	private	String	series_Title;
	private	int		img_id;
	private	int		series_postcount;
	private	int		series_viewcount;
	private String	series_regdate;
	public String getSeries_regdate() {
		return series_regdate;
	}
	public void setSeries_regdate(String series_regdate) {
		this.series_regdate = series_regdate;
	}
	public int getSeries_id() {
		return series_id;
	}
	public void setSeries_id(int series_id) {
		this.series_id = series_id;
	}
	public String getSeries_Title() {
		return series_Title;
	}
	public void setSeries_Title(String series_Title) {
		this.series_Title = series_Title;
	}
	public int getImg_id() {
		return img_id;
	}
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	public int getSeries_postcount() {
		return series_postcount;
	}
	public void setSeries_postcount(int series_postcount) {
		this.series_postcount = series_postcount;
	}
	public int getSeries_viewcount() {
		return series_viewcount;
	}
	public void setSeries_viewcount(int series_viewcount) {
		this.series_viewcount = series_viewcount;
	}
	

}
