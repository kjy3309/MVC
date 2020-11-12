package com.upload.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CommonUploadService {

	public String upload(HttpServletRequest req) {
		/* enctype="multipart/fomr-data" 일 경우 아래 방식으로 추출 X
		String title = req.getParameter("title");
		System.out.println("title : "+title);
		*/
		String oriFileName = "";
		String newFileName = "";
		
		//저장경로 확인 및 폴더 생성
		String root = "C:/";
		File dir = new File(root+"upload");
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		//업로드 실행
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());//req -> file 해주는 놈
		upload.setFileSizeMax(10*1024*1024);//용량 제한
		
		 try {
			List items = upload.parseRequest(req);//reqest 에서 무언가를 뽑아 냄
			Iterator<FileItem> iter = items.iterator();//뽑아낸 무언가를 쪼갤수 있는 형태로 변경
			
			while(iter.hasNext()) {// 하나씩 뽑아 낸다.(값이 있는가?)
				FileItem item = iter.next();//있으면 뽑는다.
				//System.out.println(item);
				//formFiled == true : 파일이 아닌 일반 필드를 의미
				//formField == false : 파일 전송 폼을 의미
				if(item.isFormField()) {//파일을 담는 필드 인가?
					System.out.println(item.getFieldName());//파라메터 이름
					System.out.println(item.getString("UTF-8"));//파라메터 값
				}else {
					//이름 변경
					oriFileName = item.getName();
					System.out.println("원래 파일 명 : "+oriFileName);
					String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
					System.out.println("확장자 : "+ext);
					newFileName = System.currentTimeMillis()+ext;
					System.out.println("바뀐 파일명 : "+newFileName);	
					//파일 저장
					File path = new File(root+"upload/"+newFileName);
					item.write(path);
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "photo/"+newFileName;
	}

}
