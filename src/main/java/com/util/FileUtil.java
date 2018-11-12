package com.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;

import com.aka_image.domain.ImageCommand;

import constSet.MainConst;

//파일업로드시 업로드 할 경로지정 및 파일의 새이름을 부여(공통 모듈)

public class FileUtil {
    //업로드 및 다운로드 경로
	public static final String UPLOAD_PATH="C:\\personalData\\BRIDGE\\WebServerWorkSpaceMK2\\SpringfileBoard\\src\\main\\webapp\\upload";
	
	//탐색기의 원본파일명만 받는 역할(test.txt)
	public static String rename(String filename) throws Exception {
		if(filename==null) return null; //업로드 X 이름변경X
		//새이름 규칙->시스템날짜+랜덤숫자(0~49)
		String newName=Long.toString(System.currentTimeMillis())+
				                   (int)(Math.random()*50);
		System.out.println("newName(난수)=>"+newName);
		return rename(filename,newName);
	}
	//실제로 새로운 파일명을 변경하는 역할(확장자 구분->변경된이름만 구해서)
	//ex) testkiamaaaaaa.txt(뒤에서 검색)->1234aDSDDA.TXT
	public static String rename(String filename,String newName) throws Exception{
		if(filename==null) return null; 
		//확장자 구하기
		int idx=filename.lastIndexOf("."); //못찾으면 -1을 리턴
		String extention="";//확장자를 저장
		String newFileName="";//새파일명을 저장
		if(idx!=-1) { //찾았다면
			extention=filename.substring(idx);
			System.out.println("extention=>"+extention);
		}
		//새파일명
		int newIdx=newName.lastIndexOf(".");//확장자를 포함한 변경된 파일명
		if(newIdx!=-1) {
			newName=newName.substring(0,newIdx);//0은 포함->newIdx-1
			System.out.println("newName(변경된파일명)=>"+newName);
		}
		newFileName=newName+extention.toLowerCase();//확장자(대)->소문자로
		return newFileName;//실질적으로 업로드된 파일명
	}
	//글수정->업로드된 파일도 수정->기존업로드된 파일 삭제->새로 업로드됨
	//파일 삭제->수정목적
	public static void removeFile(String filename) {
		File file=new File(UPLOAD_PATH,filename);//경로명,파일명
		if(file.exists())  file.delete();//파일이 이경로에 존재한다면 삭제시켜라
	}
	public static String makeImgUrl(HttpServletRequest request, String imgUrl) {
		String serverAddr = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		String fileRealUrl = serverAddr+request.getContextPath()+"/"+imgUrl;
		return fileRealUrl;
	}
	public static ImageCommand makeThumbnail(String filePath, String fileName, String fileExt) throws Exception {

	    // 저장된 원본파일로부터 BufferedImage 객체를 생성합니다.
		System.out.println("makeThumbnail >>> filePath : "+filePath);
		System.out.println("makeThumbnail >>> fileName : "+fileName);
		System.out.println("makeThumbnail >>> fileExt  : "+fileExt);
	    BufferedImage srcImg = ImageIO.read(new File( filePath + fileName ));

	    // 썸네일의 너비와 높이 입니다.
	    int dw = 460, dh = 230;
	    
	    // 원본 이미지의 너비와 높이 입니다.
	    int ow = srcImg.getWidth();
	    int oh = srcImg.getHeight();
	    
	    // 원본 너비를 기준으로 하여 썸네일의 비율로 높이를 계산합니다.
	    int nw = ow;
	    int nh = (ow * dh) / dw;
	    
	    // 계산된 높이가 원본보다 높다면 crop이 안되므로
	    // 원본 높이를 기준으로 썸네일의 비율로 너비를 계산합니다.
	    if(nh > oh) {
	        nw = (oh * dw) / dh;
	        nh = oh;
	    }
	  	
	    // 계산된 크기로 원본이미지를 가운데에서 crop 합니다.
	    BufferedImage cropImg = Scalr.crop(srcImg, (ow-nw)/2, (oh-nh)/2, nw, nh);

	    // crop된 이미지로 썸네일을 생성합니다.
	    BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
	    
	    // 썸네일을 저장합니다. 이미지 이름 앞에 "THUMB_" 를 붙여 표시했습니다.
	    String thumbName = filePath	+	"THUMB_" + fileName;
	    
	    File thumbFile = new File(thumbName);
	    if(!thumbFile.exists()) {
	    	thumbFile.createNewFile();
	    	System.out.println("name : "+thumbFile.getName()); 
	    	FileOutputStream file_out = new FileOutputStream(thumbFile);
	    }
	    ImageIO.write(destImg, fileExt.toUpperCase(), thumbFile);
	    ImageCommand thumbImgData = new ImageCommand();
	    
	    thumbImgData.setImg_name(thumbFile.getName());
	    thumbImgData.setImg_size( String.format("%.2f", thumbFile.length()/(1024.0)) );
	    thumbImgData.setImg_ref_type("POST_THUMB");
	    thumbImgData.setImg_url( MainConst.IMG_ROOT_PATH + MainConst.IMG_POST_PATH+thumbFile.getName() );
	    
	    return thumbImgData;
	}
}









