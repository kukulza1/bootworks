package com.khit.board.dto;

import java.sql.Timestamp;
import java.util.List;

import com.khit.board.entity.Board;
import com.khit.board.entity.Member;
import com.khit.board.entity.Reply;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardDTO {
	private Integer id;
	
	private String title;
	
	private String content;
	
	private Member member;
	
	private List<Reply> replyList;
	
	private Timestamp createdDate;
	
	private Timestamp updatedDate;
	
	public static BoardDTO tosaveboardDTO(Board board) {
		BoardDTO boardDTO = BoardDTO.builder()
				             .id(board.getId())
				             .title(board.getTitle())
				             .content(board.getContent())
				             .createdDate(board.getCreatedDate())
				             .updatedDate(board.getUpdatedDate())
				             .build();		
		return boardDTO;		
	}

}
















