package com.springboot.board.service;

import com.springboot.board.domain.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void savePost(){

    }
}
