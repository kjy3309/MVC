package com.mvc.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.mvc.dto.PhotoDTO;
import com.oreilly.servlet.MultipartRequest;

public class UploadService {
	
	HttpServletRequest req = null;

	public UploadService(HttpServletRequest req) {
		this.req = req;
	}

	public PhotoDTO regist() {
		String savePath="C:/upload/";//저장 경로
		int maxSize = 10*1024*1024;//용량 제한
		String oriFileName = "";
		String newFileName = "";
		PhotoDTO dto = new PhotoDTO();
		
		try {
			MultipartRequest multi = new MultipartRequest(req,savePath,maxSize,"UTF-8");//파일 저장
			
			//어차피 반환할 DTO 이기 때문에 한번에 담아서 반환 시키려고
			dto.setUser_name(multi.getParameter("userName"));
			dto.setSubject(multi.getParameter("subject"));
			dto.setContent(multi.getParameter("content"));	
			String idx = multi.getParameter("idx");
			
			if(idx != null) {
				dto.setIdx(Integer.parseInt(multi.getParameter("idx")));
			}// idx 받기.. 파일이름 변경할때 필요함
			 
			
			oriFileName = multi.getFilesystemName("photo");//원본파일명 추출			
			if(oriFileName != null) {//업로드 한 파일이 있다면...
				String ext = oriFileName.substring(oriFileName.lastIndexOf(".")); // 확장자 가져오기
				newFileName = System.currentTimeMillis()+ext;//새 파일명 생성
				
				File oldFile = new File(savePath+oriFileName);
				File newFile = new File(savePath+newFileName);
				oldFile.renameTo(newFile);	//파일명 변경	
				
				dto.setOriFileName(oriFileName);
				dto.setNewFileName(newFileName);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return dto;
	}
	
	// 파일 삭제
	public void delete(String fileName) {
		File file = new File("C:/upload/"+fileName);
		boolean success = false;
		if(file.exists()) { // 실제로 파일이 존재하는지 2차 확인
			success = file.delete();
			System.out.println("파일 삭제 성공 여부"+ success);
		}
	}
	

}
