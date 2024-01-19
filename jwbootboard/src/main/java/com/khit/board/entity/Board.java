package com.khit.board.entity;

import com.khit.board.dto.BoardDTO;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tbl_board")
@Entity
public class Board extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable= false)
	private String boardTitle;
	
	@Column(length=30, nullable= false)
	private String boardWriter;
	
	@Column(length=2000, nullable= false)
	private String boardContent;
	
	@Column
	private Integer boardhit;
	
	@Column
	private String filename;
	
	@Column
	private String filepath;
	
	public static Board tosaveEntity(BoardDTO boardDTO) { //인서트
		Board board = Board.builder()				
				      .id(boardDTO.getId())
				      .boardTitle(boardDTO.getBoardTitle())
				      .boardWriter(boardDTO.getBoardWriter())
				      .boardContent(boardDTO.getBoardContent())
				      .filename(boardDTO.getFilename())
				      .filepath(boardDTO.getFilepath())
				      .boardhit(0)
				      .build();
		return board;
		
	}
	public static Board tosaveEntity2(BoardDTO boardDTO) { //수정
		Board board = Board.builder()
				
				.id(boardDTO.getId())
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.filename(boardDTO.getFilename())
			     .filepath(boardDTO.getFilepath())
				.boardhit(0)				
				.build();
		return board;
		
	}

}
