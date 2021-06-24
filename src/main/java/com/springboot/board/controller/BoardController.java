package com.springboot.board.controller;

import com.springboot.board.dto.BoardDTO;
import com.springboot.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService;

    //리스트보기
//    @GetMapping("/")
//    public String list(Model model){
//
//        List<BoardDTO> boardDTOList = boardService.getBoardList();
//        model.addAttribute("boardList", boardDTOList);
//        return "board/list.html";
//    }

    //리스트보기 + 페이징
    @GetMapping("/")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum){

        List<BoardDTO> boardDTOList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("pageList", pageList);

        return "board/list.html";
    }

    //글쓰기 get
    @GetMapping("/post")
    public String write(){
        return "board/write.html";
    }

    //글쓰기 post
    @PostMapping("/post")
    public String write(BoardDTO boardDTO){
        boardService.savePost(boardDTO);
        return "redirect:/";
    }

    //상세보기
    @GetMapping("/post/{no}")    //@PathVariable : 요청에오는 id값을 getPost로 전달
    public String detail(@PathVariable("no") Long id, Model model){

        // getPost : 각 게시글 정보 가져오는기능 Service에서 구현
        BoardDTO boardDTO = boardService.getPost(id);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail.html";
    }

    //수정
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model){
        BoardDTO boardDTO = boardService.getPost(id);

        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
    }

    //put형식으로 db데이터 저장, main에 빈등록
    @PutMapping("post/edit/{no}")
    public String update(BoardDTO boardDTO){
        boardService.savePost(boardDTO);
        return "redirect:/";
    }

    @DeleteMapping("post/{no}")
    public String delete(@PathVariable("no") Long id){
        boardService.deletePost(id);

        return "redirect:/";
    }

    //검색
    @GetMapping("/post/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model){
        List<BoardDTO> boardDTOList = boardService.searchPosts(keyword);
        model.addAttribute("boardList", boardDTOList);

        return "board/list.html";
    }

}


