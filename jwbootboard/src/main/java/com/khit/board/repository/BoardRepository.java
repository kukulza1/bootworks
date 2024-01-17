package com.khit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.khit.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	//메서드 제공 -save() findAll(), delete(), findById()
	@Modifying//수정이나 변경이 일어났을때 사용하는 어노테이션
	//                   Board 엔티티이름쓸것
	@Query(value="update Board b set b.boardhit = b.boardhit + 1")
	public void updateHits(Long id);

	
}
