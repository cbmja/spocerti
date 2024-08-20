package com.spo.certificate.board.controller;

import com.spo.certificate.board.dto.BoardData;
import com.spo.certificate.board.service.boardData.BoardDataInfoService;
import com.spo.certificate.board.service.boardData.BoardDataSaveService;
import com.spo.certificate.board.service.board.BoardInfoService;
import com.spo.certificate.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardDataSaveService boardDataSaveService;
    private final BoardInfoService boardInfoService;
    private final BoardDataInfoService boardDataInfoService;

    @GetMapping("/board/list")
    public String board(Model model, @ModelAttribute BoardSearch boardSearch){

        Page page = new Page(boardSearch.getPage() , boardDataInfoService.getTotal(boardSearch) , boardSearch);

        model.addAttribute("boardList" , boardDataInfoService.findByPage(page));

        model.addAttribute("board" , boardInfoService.findByBoardType(boardSearch.getBoardType()));
        model.addAttribute("page" , page);
        model.addAttribute("pageType","board");

        return "view/board/board";
    }

    @PostMapping("/board/save")
    public ResponseEntity<String> saveBoard(@RequestBody BoardData boardData) {
        boardDataSaveService.save(boardData);
        return ResponseEntity.ok("글이 저장되었습니다.");
    }


    @GetMapping("/board/detail/{id}")
    @ResponseBody
    public BoardData getBoardDetail(@PathVariable(name = "id") int id) {
        // 여기에 실제 데이터베이스에서 id에 해당하는 게시물 정보를 조회하는 로직을 구현
        BoardData boardData = boardDataInfoService.findById(id);

        return boardData;  // JSON 형식으로 자동 변환되어 반환됩니다.
    }

}
