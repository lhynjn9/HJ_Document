package com.hj.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownLoadView extends AbstractView {

	public FileDownLoadView() {
		setContentType("application/download; charset=UTF-8");
	}
	
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
       ServletContext ctx = getServletContext();
       String realPath = ctx.getRealPath("/file");
       
       //HomeController에서 downloadFile이라는 이름으로 넘겨준 fileInfo
       Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile"); //전송 받은 파일 정보
       
       
       String filename = (String) fileInfo.get("filename"); // 파일 경로
       // 특정 위치에서 특정 파일을 가져옴
       File file = new File(realPath, filename);
       
       response.setContentType(getContentType());
       response.setContentLength((int) file.length());
       
       String header = request.getHeader("User-Agent");
       boolean isIE = header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1;
       String fileName = null;
       if (isIE) { 
           fileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
       } else {
           fileName = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
       }
       response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\";");
       response.setHeader("Content-Transfer-Encoding", "binary");
       
       // 응답에 대한 출력을
       OutputStream out = response.getOutputStream();
       
       FileInputStream fis = null;
       try {
           fis = new FileInputStream(file);
           
           // file에다 out을 냅다 보냄 : 사용자에게 감
           FileCopyUtils.copy(fis, out);
       } catch(Exception e){
    	   e.printStackTrace();
       } finally {
           if (fis != null) {
               try {
                   fis.close();
               } catch (IOException e) {
            	   e.printStackTrace();
               }
           }
       }
       out.flush();
   }
       
           
}
