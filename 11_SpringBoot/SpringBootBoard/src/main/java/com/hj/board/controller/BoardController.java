package com.hj.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hj.board.model.dto.Board;
import com.hj.board.model.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	//localhost:8080/board/board/list
	
	// 파일 업로드
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BoardService boardService;

	@GetMapping("list")
	// 기본 list 조회 또는 제목과 내용에 해당하는 파라미터가 있는 조회로 나뉨
	public String list(Model model, @RequestParam(defaultValue="") String mode, @RequestParam(defaultValue="") String keyword) {
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("mode", mode);
		params.put("key", keyword); //boardMapper에서 key라고 명명
		List<Board> list = boardService.getBoardList(params);
		model.addAttribute("list", list);
		
		return "board/list";
		
	}
	
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/writeform";
	}
	
	@PostMapping("write")
	public String write(Board board, MultipartFile upload_file) {
		
		if(upload_file.getSize() != 0) {
			// 파일 업로드하는 부분
			String uploadPath = servletContext.getRealPath("/file");
			String fileName = upload_file.getOriginalFilename();
			String saveName = UUID.randomUUID()+"";
			File target = new File(uploadPath, saveName);
			if( !new File(uploadPath).exists())
				new File(uploadPath).mkdirs();
			try {
				FileCopyUtils.copy(upload_file.getBytes(), target);			
				board.setFilename(fileName);
				board.setFileUri(target.getCanonicalPath()); // 파일의 절대 경로
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		boardService.writeBoard(board);
		
		return "redirect:/board/list";
		
	}
	
	@GetMapping("detail")
	public String detail(Model model, int id) {
		Board board = boardService.readBoard(id);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@GetMapping("download")
	public String download(Model model, int id) {
		Board board = boardService.getBoardById(id);
		model.addAttribute("fileuri", board.getFileUri());
		model.addAttribute("filename", board.getFilename());
		return "fileDownLoadView";
	}
	
	
	@GetMapping("updateForm")
	public String updateForm(Model model, int id) {
		Board board = boardService.getBoardById(id);
		model.addAttribute("board", board);
		return "board/updateform";
	}
	
	@GetMapping("delete")
	public String delete(int id) {
		boardService.deleteBoard(id);
		return "redirect:/board/list";
	}
	
	@PostMapping("update")
	public String update(Board board) {
		boardService.modifyBoard(board);
		return "redirect:/borad/detail?id="+ board.getId();
	}
}
