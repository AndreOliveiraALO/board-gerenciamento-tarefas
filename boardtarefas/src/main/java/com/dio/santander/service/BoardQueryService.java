package com.dio.santander.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.dio.santander.dto.BoardDetailsDTO;
import com.dio.santander.persistence.dao.BoardColumnDAO;
import com.dio.santander.persistence.dao.BoardDAO;
import com.dio.santander.persistence.entity.BoardEntity;

//@AllArgsConstructor
public class BoardQueryService {

    private final Connection connection;

    public BoardQueryService(Connection connection) {
        this.connection = connection;
    }

    public Optional<BoardEntity> findById(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        var optional = dao.findById(id);
        if (optional.isPresent()){
            var entity = optional.get();
            entity.setBoardColumns(boardColumnDAO.findByBoardId(entity.getId()));
            return Optional.of(entity);
        }
        return Optional.empty();
    }

    public Optional<BoardDetailsDTO> showBoardDetails(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        var optional = dao.findById(id);
        if (optional.isPresent()){
            var entity = optional.get();
            var columns = boardColumnDAO.findByBoardIdWithDetails(entity.getId());
            var dto = new BoardDetailsDTO(entity.getId(), entity.getName(), columns);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

}

