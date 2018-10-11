package com.aka_image.dao;

import com.aka_image.domain.ImageCommand;

public interface ImageDAO {
	public  boolean insertImage(ImageCommand data);
	public  int 	getNewImageNum();
	public  String 	getImgUrlById( int img_id );
	public	int		getImgIdByName( String img_name );
	public	String	getImgNameById( int img_id );
}
