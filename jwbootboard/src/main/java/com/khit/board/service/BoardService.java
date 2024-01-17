package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	public void save(BoardDTO boardDTO) {
		Board board = Board.tosaveEntity(boardDTO);
		boardRepository.save(board);
	}

	public List<BoardDTO> findAll() {
		List<Board> boardlist = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		List<BoardDTO> boardDTOlist = new ArrayList<>();
		for(Board board : boardlist) {
			BoardDTO boardDTO = BoardDTO.tosaveEntity2(board);
			boardDTOlist.add(boardDTO);
			
		}
		return boardDTOlist;
	}

	public BoardDTO findById(Long id) {
		Optional<Board> board = boardRepository.findById(id);
		if(board.isPresent()) {
			BoardDTO boardDTO = BoardDTO.tosaveEntity2(board.get());
			return boardDTO;
		}else {
			throw new BootBoardException("게시글을 찾을수없습니다");
		}
	
	}
   @Transactional //컨트롤러 작업(메서드)이 2개이상이면 사용함
	public void updatehits(Long id) {//이메서드를 보드레포에 생성
		boardRepository.updateHits(id);
	}

public void delete(Long id) {
	boardRepository.deleteById(id);
}

public void update(BoardDTO boardDTO) {
     Board board = Board.tosaveEntity2(boardDTO);	
         boardRepository.save(board);
}

}









