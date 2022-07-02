package com.ss.hj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ss.hj.dto.User;

// �� Ŭ������ ��Ʈ�ѷ� ���� ������̼����� ����, ������Ʈ ��ĵ�� ���� ������ ���
@Controller
public class UserController {
	
	/**
	 * '/' �Ǵ� '/index' ��û�� get ������� ������ �� index�� �����Ѵ�.
	 */
	@GetMapping({"/","/index" })
	public String showIndex() {
		return "index";
	}
	
	/**
	 * '/regist' ��û�� get ������� ������ �� regist�� �����Ѵ�.
	 */
	@GetMapping("/regist")
	public String showRegistForm() {
		return "regist";
	}

	/**
	 * '/regist' ��û�� post ������� ������ �� ���޵� User ��ü�� regist_result�� �����Ѵ�. 
	 */
	@PostMapping("/regist")
	public ModelAndView doRegist(User user) {
		ModelAndView mav = new ModelAndView();
		// ���� �������� ����
		mav.setViewName("regist_result");
		// ������ ��ü
		mav.addObject("user", user);
		return mav;
	}
}