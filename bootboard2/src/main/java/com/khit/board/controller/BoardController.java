package com.khit.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khit.board.config.SecurityUser;
import com.khit.board.entity.Board;
import com.khit.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	//게시글 전체 목록
	@GetMapping("/list")
	public String getList(Model model) {
		List<Board> boardList = boardService.findAll();
		
		model.addAttribute("boardList", boardList);
		return "/board/list";  //list.html
	}
	
	//게시글 상세 보기
	@GetMapping("/{id}")
	public String getBoard(@PathVariable Integer id,
			Model model) {
		Board board = boardService.findById(id);
		model.addAttribute("board", board);
		return "/board/detail";  //detail.html
	}
	@GetMapping("/write")
	public String writeform() {
		
		return"/board/write";
	}
	@PostMapping("/write")
	public String write(@ModelAttribute Board board,
			@AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.save(board);
		return"redirect:/board/list";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		boardService.delete(id);
		return"redirect:/board/list";
		
	}
	
	@GetMapping("/update/{id}")
	public String updateform(@PathVariable Integer id,Model model) {
		
		Board board = boardService.findById(id);
		 
		model.addAttribute("board", board);
		return"/board/update";
		
	}
	@PostMapping("/update")
	public String update(@ModelAttribute Board board, @AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.update(board);
		return"redirect:/board/list";
	}
	
}





















