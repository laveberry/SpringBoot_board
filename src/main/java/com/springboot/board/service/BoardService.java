package com.springboot.board.service;

import com.mysql.cj.log.Log;
import com.springboot.board.domain.entity.Board;
import com.springboot.board.domain.repository.BoardRepository;
import com.springboot.board.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    //페이지처리를위한 추가
    private static final int BLOCK_PAGE_NUM_COUNT = 5; //블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 4; //한 페이지에 존재하는 게시글 수

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //@Transactional : 프록시객체 생성, 처리한 쿼리들 에러났을때 자동 rollback
    @Transactional
    public Long savePost(BoardDTO boardDTO){ //글쓰기&수정 db저장
        return boardRepository.save(boardDTO.toEntity()).getId();
    }

    @Transactional
    public List<BoardDTO> getBoardList(Integer pageNum){
        //페이지처리 추가
        Page<Board> page = boardRepository.findAll(PageRequest
                .of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));

//        List<Board> boards = boardRepository.findAll();
        List<Board> boards = page.getContent();//페이지처리로 수정
        List<BoardDTO> boardDTOList = new ArrayList<>();

        //lombok빌더 적용 get정보 가져오기
        for(Board board : boards){
            BoardDTO boardDto = BoardDTO.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .createDate(board.getCreateDate())
                    .build();

            boardDTOList.add(boardDto);
        }
        return boardDTOList;
    }

    @Transactional
    public BoardDTO getPost(Long id){
        Optional<Board> boardWrapper = boardRepository.findById(id);
        Board board = boardWrapper.get();

        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createDate(board.getCreateDate())
                .build();

        return boardDTO;
    }

    @Transactional
    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }

    //검색
    @Transactional
    public List<BoardDTO> searchPosts(String keyword){
        //검색용 레포지토리 생성&호출
        List<Board> boards = boardRepository.findByTitleContaining(keyword);
        List<BoardDTO> boardDTOList = new ArrayList<>();

        if(boards.isEmpty()) return boardDTOList;

        for(Board board : boards){
            boardDTOList.add(this.convertEntitiyToDto(board));
        }

        return boardDTOList;
    }

    private BoardDTO convertEntitiyToDto(Board board){
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createDate(board.getCreateDate())
                .build();
    }


    //페이지
    public Integer[] getPageList(Integer curPageNum){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 수
        Double postsTotalcount = Double.valueOf(this.getBoardCount());

        //총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalcount/PAGE_POST_COUNT)));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        //페이지 시작 번호 조정
        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;

        //페이지번호 할당
        for(int val=curPageNum, i=0; val<=blockLastPageNum; val++, i++){
            pageList[i] = val;
        }

        return pageList;
    }

    @Transactional
    public Long getBoardCount(){
        return boardRepository.count();
    }


}
