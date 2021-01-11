package com.kdh.blog.controller;

import com.kdh.blog.model.Board;
import com.kdh.blog.model.User;
import com.kdh.blog.repository.BoardRepository;
import com.kdh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/list")
    public String board(Model model, @PageableDefault(size=2) Pageable pageable) {
        Page<Board> boardList = boardRepository.findAll(pageable);
        int startPage = Math.max(1, boardList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board", new Board());
        }else {
            Board findBoard = boardRepository.findById(id).orElseGet(null);
            model.addAttribute("board", findBoard);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String write(@Valid Board board, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "board/form";
        }
        Board saveBoard = boardRepository.save(board);
        return "redirect:/board/list";
    }
}
