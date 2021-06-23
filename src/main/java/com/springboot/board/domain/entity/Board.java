package com.springboot.board.domain.entity;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity : DB테이블과 매핑되는 객체
 *  @Builder : setter대신 사용
 *  @Table : DB테이블 이름 정해줄수 있음. 없으면 클래스이름 자동매핑
 * */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeEntitiy{

    @Id @GeneratedValue
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Board(Long id, String writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
