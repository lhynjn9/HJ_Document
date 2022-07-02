package com.hj.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hj.board.model.dto.Board;
import com.hj.board.model.service.BoardService;

@Controller
public class BoardController{
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
		
	}
	
	@RequestMapping("/")
	public String main() {
		return "redirect:list";
	}
	
	@RequestMapping("list")
	public String list(Model model) {
		//받아서
		List<Board> list = boardService.getBoardList();
		// 변수에 값 삽입
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping("detail")
	public String detail(Model model, int id) {
		Board b = boardService.readBoard(id);
		model.addAttribute("board", b);
		return "detail";
	}
	
	@RequestMapping("writeform")
	public String writeform() {
		return "writeform";
	}
	
	//write랑 delete
	@RequestMapping("write")
	public String write(Board board) {
		boardService.writeBoard(board);
		return "redirect:detail?id="+board.getId();
		
	}
	
	@RequestMapping("delete")
	public String delete(int id) {
		boardService.deleteBoard(id);
		return "redirect:list";
	}
	

}