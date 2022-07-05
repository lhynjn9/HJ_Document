package com.hj.file.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */


@Controller
public class HomeController {
	
	@Autowired
	private ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
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
	
	@GetMapping("download")
	public ModelAndView download(Model model, String fileName) {

		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("filename", fileName);
		
		//fileDownLoadView에 해당하는 빈 덕분에 downloadFile이 작동
		return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
		
	}
	}
