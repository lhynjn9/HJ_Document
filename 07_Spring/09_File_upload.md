# File upload

- SpringMVC_file

- upload

  1. pom.xml : common file dependency 추가

  ```xml
  <!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
  		<dependency>
  		    <groupId>commons-fileupload</groupId>
  		    <artifactId>commons-fileupload</artifactId>
  		    <version>1.4</version>
  		</dependency>
  ```

  2. CommonMultipartViewResolver 빈으로 등록(servlet-context.xml)

  - 오타 유의

  ```xml
  <!-- id는 수정불가 -->
  <!-- 10MB -->
  <beans:bean 
  	id="multipartResolver"
  	class="org.springframework.web.multipart.commons.CommonsMultipartResolver">	
  		<beans:property name="maxUploadSize" value="10485760"></beans:property>
  		<beans:property name="defaultEncoding" value="UTF-8"></beans:property>
  	</beans:bean>
  ```

  3. client : jsp type="file" method = "post", encodingtype ="multipartformdata" (home.jsp)

  ```jsp
  <form action="upload" method="post" enctype="multipart/form-data">
  	<input type="file" name="upload_file">
  	<input type="submit" name="upload">
  </form>
  ```

  4. server : MulitpartFile이란 type으로 데이터를 받아 원하는 로컬의 위치에 copy하면 저장이 됨(MultipartFile 파라미터 처리) (HomeController.java)

     - 자원접근(servlet-context.xml)

     ```xml
     <resources mapping="/file/**" location="/file/" />
     ```

     - 특정 폴더의 위치 찾아내기 : ServletContext가 제공하는 getRealPath

  ```java
  @Autowired
  	private ServletContext servletContext;	
  
  @PostMapping("upload")
  	public String upload(MultipartFile upload_file, Model model) {
  		String uploadPath = servletContext.getRealPath("/file");
  		String fileName = upload_file.getOriginalFilename();
  		File target = new File(uploadPath, fileName);
  		if( !new File(uploadPath).exists())
  			new File(uploadPath).mkdirs();
  		try {
  			FileCopyUtils.copy(upload_file.getBytes(), target);			
  		}catch(IOException e) {
  			e.printStackTrace();
  		}
  		model.addAttribute("fileName", fileName);
  		return "result";
  	}
  ```

  

- download

  1. FileDownLoadView.java 구현

     ```java
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
            OutputStream out = response.getOutputStream();
            
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
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
     ```

  2. downlaod 생성

     ```java
     	@GetMapping("download")
     	public ModelAndView download(Model model, String fileName) {
     
     		Map<String, Object> fileInfo = new HashMap<String, Object>();
     		fileInfo.put("filename", fileName);
     		
     		//fileDownLoadView에 해당하는 빈 덕분에 downloadFile이 작동
     		return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
     		
     	}
     ```

  3. bean으로 등록(servlet-context.xml)

     ```xml
     	<beans:bean 
     	id="multipartResolver"
     	class="org.springframework.web.multipart.commons.CommonsMultipartResolver">	
     		<beans:property name="maxUploadSize" value="10485760"></beans:property>
     		<beans:property name="defaultEncoding" value="UTF-8"></beans:property>
     	</beans:bean>
     	
     	<beans:bean class="com.hj.file.controller.FileDownLoadView" id="fileDownLoadView"></beans:bean>
     	<beans:bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
     		<beans:property name="order" value="0"></beans:property>
     	</beans:bean>
     ```

     





- 파일 확인 경로 : `C:\Users\HyeonJeong\lhynjn9_Github\Public\HJ_Document\07_Spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVC_file\file`