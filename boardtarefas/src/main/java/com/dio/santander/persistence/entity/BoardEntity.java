package com.dio.santander.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.dio.santander.persistence.entity.BoardColumnKindEnum.CANCEL;
import static com.dio.santander.persistence.entity.BoardColumnKindEnum.INITIAL;

public class BoardEntity {

    private Long id;
    private String name;    
    private List<BoardColumnEntity> boardColumns = new ArrayList<>();

    public BoardColumnEntity getInitialColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(INITIAL));
    }

    public BoardColumnEntity getCancelColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(CANCEL));
    }

    private BoardColumnEntity getFilteredColumn(Predicate<BoardColumnEntity> filter){
        return boardColumns.stream()
                .filter(filter)
                .findFirst().orElseThrow();
    }

    public BoardEntity() {
    }

    public BoardEntity(Long id, String name, List<BoardColumnEntity> boardColumns) {
        this.id = id;
        this.name = name;
        this.boardColumns = boardColumns;
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

    public List<BoardColumnEntity> getBoardColumns() {
        return boardColumns;
    }

    public void setBoardColumns(List<BoardColumnEntity> boardColumns) {
        this.boardColumns = boardColumns;
    }

    


}
