package com.dio.santander.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.dio.santander.persistence.dao.BoardColumnDAO;
import com.dio.santander.persistence.entity.BoardColumnEntity;

public class BoardColumnQueryService {

    private final Connection connection;

    public BoardColumnQueryService(Connection connection) {
        this.connection = connection;
    }

    public Optional<BoardColumnEntity> findById(final Long id) throws SQLException {
        var dao = new BoardColumnDAO(connection);
        return dao.findById(id);
    }

}
