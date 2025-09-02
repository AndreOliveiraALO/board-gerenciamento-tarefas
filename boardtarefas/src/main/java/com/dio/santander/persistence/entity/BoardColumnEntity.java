package com.dio.santander.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class BoardColumnEntity {

    private Long id;
    private String name;
    private int order;
    private BoardColumnKindEnum kind;
    private BoardEntity board = new BoardEntity();
    private List<CardEntity> cards = new ArrayList<>();
   
    public BoardColumnEntity() {
    }   

    public BoardColumnEntity(Long id, String name, int order, BoardColumnKindEnum kind, BoardEntity board,
            List<CardEntity> cards) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.kind = kind;
        this.board = board;
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public BoardColumnKindEnum getKind() {
        return kind;
    }
    public void setKind(BoardColumnKindEnum kind) {
        this.kind = kind;
    }
    public BoardEntity getBoard() {
        return board;
    }
    public void setBoard(BoardEntity board) {
        this.board = board;
    }
    public List<CardEntity> getCards() {
        return cards;
    }
    public void setCards(List<CardEntity> cards) {
        this.cards = cards;
    }    

}

