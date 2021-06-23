package com.springboot.board.domain.repository;

import com.springboot.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository : 데이터 조작 담당. interface로 생성하며 JpaRepository 상속, 값은 매핑할 entity와 id타입
 * */
public interface BoardRepository extends JpaRepository<Board, Long> {
}
