package com.khit.board.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khit.board.dto.BoardDTO;
import com.khit.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {
   private final BoardService boardservice;
   
   //글쓰기 페이지
   @GetMapping("/write")
   public String writeform(BoardDTO boardDTO) {//폼에 th:object랑 연동
	   
	   
	   return"/board/write";
   }
   @PostMapping("/write")
   public String write(@Valid BoardDTO boardDTO, BindingResult bindresult) {
	   if(bindresult.hasErrors()) {//에러가 있으면 글쓰기폼으로 다시이동
		   log.info("has error");
		   return"/board/write";
	   }
	   
	   boardservice.save(boardDTO);   
	   return"redirect:/board/list";
   }
   @GetMapping("/list")
   public String list(Model model) {
	   List<BoardDTO> boardDTO = boardservice.findAll();
	   model.addAttribute("boardlist", boardDTO);
	      
	   return "/board/list";
   }
   
   @GetMapping("/{id}")
   public String getBoard(@PathVariable Long id, Model model) {
	   //조회수
	   boardservice.updatehits(id);
	   
	   //글상세
	   BoardDTO boardDTO = boardservice.findById(id);
	   model.addAttribute("board", boardDTO);
	return "/board/detail";
	   
   }
   
   @GetMapping("/delete/{id}")
   public String delete(@PathVariable Long id) {
	   boardservice.delete(id);
	   return"redirect:/board/list";
   }
   
   @GetMapping("/update/{id}")
   public String updateform(Model model, @PathVariable Long id) {
	   BoardDTO boardDTO = boardservice.findById(id);
	   model.addAttribute("board", boardDTO);
	   
	   return"/board/update";
   }
   @PostMapping("/update")
 public String update(@ModelAttribute BoardDTO boardDTO) {
	   boardservice.update(boardDTO);
	   return"redirect:/board/list";
   }
   
}















