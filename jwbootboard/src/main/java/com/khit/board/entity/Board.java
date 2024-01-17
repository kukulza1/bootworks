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
	
	public static Board tosaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				
				      .id(boardDTO.getId())
				      .boardTitle(boardDTO.getBoardTitle())
				      .boardWriter(boardDTO.getBoardWriter())
				      .boardContent(boardDTO.getBoardContent())
				      .boardhit(0)
				      .build();
		return board;
		
	}
	public static Board tosaveEntity2(BoardDTO boardDTO) {
		Board board = Board.builder()
				
				.id(boardDTO.getId())
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.boardhit(boardDTO.getBoardhit())
				
				.build();
		return board;
		
	}

}
