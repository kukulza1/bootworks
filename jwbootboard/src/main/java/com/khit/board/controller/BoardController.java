package com.khit.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
   public String write(@Valid BoardDTO boardDTO, 
		   BindingResult bindresult,
		   MultipartFile boardFile) throws IOException {
	   if(bindresult.hasErrors()) {//에러가 있으면 글쓰기폼으로 다시이동
		   log.info("has error");
		   return"/board/write";
	   }	   
	   boardservice.save(boardDTO,boardFile);   
	   return"redirect:/board/pagelist";
   }
   
   @GetMapping("/list")
   public String list(Model model) {
	   List<BoardDTO> boardDTO = boardservice.findAll();
	   model.addAttribute("boardlist", boardDTO);
	      
	   return "/board/pagelist";
   }
   
   @GetMapping("/{id}")
   public String getBoard(@PageableDefault(page=1) Pageable pageable,
		   @PathVariable Long id, Model model) {
	   //조회수
	   boardservice.updatehits(id);
	   
	   //글상세
	   BoardDTO boardDTO = boardservice.findById(id);
	   model.addAttribute("board", boardDTO);
	   model.addAttribute("page", pageable.getPageNumber());
	return "/board/detail";
	   
   }
   // /board/pagelist?page=0
   // /board/pagelist
   @GetMapping("/pagelist")
   public String getPagelist(
		   @RequestParam(value="type", required=false)String type ,
		   @RequestParam(value="keyword",required=false)String keyword,
		   @PageableDefault(page=1) Pageable pageable,
		   Model model) {
	   //검색어가 없으면 페이지처리를 하고, 검색어가 있으면 검색어로 페이지처리
	   Page<BoardDTO> boardDTOList = null;
	   if(keyword == null) {
		    boardDTOList = boardservice.findListAll(pageable);
	   }else if(type != null && type.equals("title")) {
		   boardDTOList = boardservice.findByBoardTitleContaning(keyword, pageable);
	   }else if(type != null && type.equals("content")){
		   boardDTOList = boardservice.findByBoardContentContaning(keyword, pageable);
	   }else {
		    boardDTOList = boardservice.findByBoardTitleContaning(keyword, pageable);
	   }
	   
	   //하단페이지블럭만들기
	   int blockLimit = 10; //하단에 보여줄 페이지 개수
	   
	   //시작페이지 1, 11, 21                ex. 12/10 =1.2 ->2.2 -> 2 -1 * 10 + 1 =11
	   int startPage = ((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit)) -1) *blockLimit+1;
	   //마지막페이지 10 ,20 ,30 ex 12page -> 12가마지막 
	   int endPage = (startPage+blockLimit -1) > boardDTOList.getTotalPages() ? 
			   boardDTOList.getTotalPages() : (startPage+blockLimit -1)  ;
	   model.addAttribute("type", type); //검색유형보내기
	   model.addAttribute("kw", keyword);//검색어 보내기
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   model.addAttribute("boardlist", boardDTOList);
	   return"/board/pagelist";
   }
   
   @GetMapping("/delete/{id}")
   public String delete(@PathVariable Long id) {
	   boardservice.delete(id);
	   return"redirect:/board/pagelist";
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















