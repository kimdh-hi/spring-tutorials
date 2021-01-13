package com.kdh.blog.repository;

import com.kdh.blog.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 제목만
    Page<Board> findByTitleContaining(String title, Pageable pageable);
    // 내용만
    Page<Board> findByContentsContaining(String contents, Pageable pageable);
    // 제목+내용
    Page<Board> findByTitleContainingOrContentsContaining(String title, String contents, Pageable pageable);
}
