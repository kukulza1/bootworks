package com.khit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public void save2(BoardDTO boardDTO) {		//테스트용 메서드	
		Board board = Board.tosaveEntity(boardDTO);
		boardRepository.save(board);	
	}

	public void save(BoardDTO boardDTO, MultipartFile boardFile) throws  IOException {
		//1.파일은 서버에 저장하고 
		if(!boardFile.isEmpty()) {
			//저장경로
			String filePath = "C:\\bootworks\\jwbootboard\\src\\main\\resources\\static\\upload\\"; // filePath \\ filename		
			
			UUID uuid = UUID.randomUUID();//무작위 아이디 생성(중복파일의 이름을 생성해줌)
			
			String filename = uuid + "_" + boardFile.getOriginalFilename();//원본파일		
			//1.File클래스로 객체생성
			File savedfile = new File(filePath,filename);// filePath \\ filename
			boardFile.transferTo(savedfile);			
			//2.파일이름은 db에 저장
			boardDTO.setFilename(filename);
			boardDTO.setFilepath("/upload/" + filename); //파일경로 설정됨
		}				
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

public Page<BoardDTO> findListAll(Pageable pageable) {
	int page = pageable.getPageNumber() -1;
	int pageSize =10;
	pageable =PageRequest.of(page, pageSize ,Sort.Direction.DESC,"id");
	Page<Board> boardList = boardRepository.findAll(pageable);
	
	log.info("boardList.isFirst()="+boardList.isFirst());
	log.info("boardList.isLast()="+boardList.isLast());
	log.info("boardList.isLast()="+boardList.getNumber());
	
	//생성자 방식으로 boardDTOList만들기
	Page<BoardDTO> boardDTOList = boardList.map(board ->
	    new BoardDTO(board.getId(), 
	    		board.getBoardTitle(),
	    		board.getBoardWriter(),
	    		board.getBoardContent(), 
	    		board.getBoardhit(), 
	    		board.getFilename(),
	    		board.getFilepath(),
	    		board.getCreatedDate(), 
	    		board.getUpdatedDate()));			
	       return  boardDTOList;
}

public Page<BoardDTO> findByBoardTitleContaning(String keyword, Pageable pageable) {

	int page = pageable.getPageNumber() -1;
	int pageSize =10;
	pageable =PageRequest.of(page, pageSize ,Sort.Direction.DESC,"id");
	Page<Board> boardList = boardRepository.findByBoardTitleContaining(keyword,pageable);
		
	//생성자 방식으로 boardDTOList만들기
	Page<BoardDTO> boardDTOList = boardList.map(board ->
	    new BoardDTO(board.getId(), 
	    		board.getBoardTitle(),
	    		board.getBoardWriter(),
	    		board.getBoardContent(), 
	    		board.getBoardhit(),
	    		board.getFilename(),
	    		board.getFilepath(),
	    		board.getCreatedDate(), 
	    		board.getUpdatedDate()));			
	      return  boardDTOList;
}

public Page<BoardDTO> findByBoardContentContaning(String keyword, Pageable pageable) {

	int page = pageable.getPageNumber() -1;
	int pageSize =10;
	pageable =PageRequest.of(page, pageSize ,Sort.Direction.DESC,"id");
	Page<Board> boardList = boardRepository.findByBoardContentContaining(keyword,pageable);
		
	//생성자 방식으로 boardDTOList만들기
	Page<BoardDTO> boardDTOList = boardList.map(board ->
	    new BoardDTO(board.getId(), 
	    		board.getBoardTitle(),
	    		board.getBoardWriter(),
	    		board.getBoardContent(), 
	    		board.getBoardhit(),
	    		board.getFilename(),
	    		board.getFilepath(),
	    		board.getCreatedDate(), 
	    		board.getUpdatedDate()));			
	        return  boardDTOList;
}

}




















