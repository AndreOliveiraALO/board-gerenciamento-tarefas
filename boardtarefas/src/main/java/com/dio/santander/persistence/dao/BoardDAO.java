package com.dio.santander.persistence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.dio.santander.persistence.entity.BoardEntity;

//@AllArgsConstructor
public class BoardDAO {

    private Connection connection;

    public BoardDAO(Connection connection) {
        this.connection = connection;
    }

    /*public BoardEntity insert(final BoardEntity entity) throws SQLException {
        var sql = "INSERT INTO BOARDS (name) values (?);";
        try(var statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getName());
            statement.executeUpdate();
            if (statement instanceof StatementImpl impl){
                entity.setId(impl.getLastInsertID());
            }
        }
        return entity;
    } */

    public BoardEntity insert(final BoardEntity entity) throws SQLException {
        var sql = "INSERT INTO boards (name) VALUES (?) RETURNING id;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getName());
            try(var resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    entity.setId(resultSet.getLong("id"));
                }
            }
        }
        return entity;
    }

    /*public void delete(final Long id) throws SQLException {
        var sql = "DELETE FROM BOARDS WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }*/

    public void delete(final Long id) throws SQLException {
        var sql = "DELETE FROM boards WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    /*public Optional<BoardEntity> findById(final Long id) throws SQLException {
        var sql = "SELECT id, name FROM BOARDS WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            if (resultSet.next()){
                var entity = new BoardEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                return Optional.of(entity);
            }
            return Optional.empty();
        }
    }*/

    public Optional<BoardEntity> findById(final Long id) throws SQLException {
        var sql = "SELECT id, name FROM boards WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            try(var resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    var entity = new BoardEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setName(resultSet.getString("name"));
                    return Optional.of(entity);
                }
            }
        }
        return Optional.empty();
    }

    /*public boolean exists(final Long id) throws SQLException {
        var sql = "SELECT 1 FROM BOARDS WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            return statement.getResultSet().next();
        }
    }*/

    public boolean exists(final Long id) throws SQLException {
        var sql = "SELECT 1 FROM boards WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            try(var resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        }
    }

}