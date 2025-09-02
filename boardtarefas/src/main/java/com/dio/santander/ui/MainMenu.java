package com.dio.santander.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.dio.santander.persistence.config.ConnectionConfig.getConnection;
import com.dio.santander.persistence.entity.BoardColumnEntity;
import com.dio.santander.persistence.entity.BoardColumnKindEnum;
import static com.dio.santander.persistence.entity.BoardColumnKindEnum.CANCEL;
import static com.dio.santander.persistence.entity.BoardColumnKindEnum.FINAL;
import static com.dio.santander.persistence.entity.BoardColumnKindEnum.INITIAL;
import static com.dio.santander.persistence.entity.BoardColumnKindEnum.PENDING;
import com.dio.santander.persistence.entity.BoardEntity;
import com.dio.santander.service.BoardQueryService;
import com.dio.santander.service.BoardService;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public void execute() throws SQLException {
        System.out.println("Bem vindo ao gerenciador de boards, escolha a opção desejada");
        var option = -1;
        while (true){
            System.out.println("1 - Criar um novo board");
            System.out.println("2 - Selecionar um board existente");
            System.out.println("3 - Excluir um board");
            System.out.println("4 - Sair");            
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida, informe um número.");
                continue;
            }
            switch (option){
                case 1 -> createBoard();
                case 2 -> selectBoard();
                case 3 -> deleteBoard();
                case 4 -> System.exit(0);
                default -> System.out.println("Opção inválida, informe uma opção do menu");
            }
        }
    }

    private void createBoard() throws SQLException {
        var entity = new BoardEntity();
        System.out.println("Informe o nome do seu board");
        entity.setName(scanner.nextLine()); // padronizando para nextLine

        System.out.println("Seu board terá colunas além das 3 padrões? Se sim informe quantas, senão digite '0'");
        var additionalColumns = Integer.parseInt(scanner.nextLine());

        List<BoardColumnEntity> columns = new ArrayList<>();

        System.out.println("Informe o nome da coluna inicial do board");
        var initialColumnName = scanner.nextLine(); // padronizando para nextLine
        var initialColumn = createColumn(initialColumnName, INITIAL, 0);
        columns.add(initialColumn);

        for (int i = 0; i < additionalColumns; i++) {
            System.out.println("Informe o nome da coluna de tarefa pendente do board");
            var pendingColumnName = scanner.nextLine();// padronizando para nextLine
            var pendingColumn = createColumn(pendingColumnName, PENDING, i + 1);
            columns.add(pendingColumn);
        }

        System.out.println("Informe o nome da coluna final");
        var finalColumnName = scanner.nextLine(); // padronizando para nextLine
        var finalColumn = createColumn(finalColumnName, FINAL, additionalColumns + 1);
        columns.add(finalColumn);

        System.out.println("Informe o nome da coluna de cancelamento do baord");
        var cancelColumnName = scanner.nextLine(); // padronizando para nextLine
        var cancelColumn = createColumn(cancelColumnName, CANCEL, additionalColumns + 2);
        columns.add(cancelColumn);

        entity.setBoardColumns(columns);
        try(var connection = getConnection()){
            var service = new BoardService(connection);
            service.insert(entity);
        }

    }

    private void selectBoard() throws SQLException {
        System.out.println("Informe o id do board que deseja selecionar");
        var id = Long.parseLong(scanner.nextLine());
        try(var connection = getConnection()){
            var queryService = new BoardQueryService(connection);
            var optional = queryService.findById(id);
            optional.ifPresentOrElse(
                    b -> new BoardMenu(b).execute(),
                    () -> System.out.printf("Não foi encontrado um board com id %s\n", id)
            );
        }
    }

    private void deleteBoard() throws SQLException {
        System.out.println("Informe o id do board que será excluido");
        var id = Long.parseLong(scanner.nextLine());
        try(var connection = getConnection()){
            var service = new BoardService(connection);
            if (service.delete(id)){
                System.out.printf("O board %s foi excluido\n", id);
            } else {
                System.out.printf("Não foi encontrado um board com id %s\n", id);
            }
        }
    }

    private BoardColumnEntity createColumn(final String name, final BoardColumnKindEnum kind, final int order){
        var boardColumn = new BoardColumnEntity();
        boardColumn.setName(name);
        boardColumn.setKind(kind);
        boardColumn.setOrder(order);
        return boardColumn;
    }

}
