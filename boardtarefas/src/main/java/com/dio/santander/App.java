package com.dio.santander;

import java.sql.SQLException;

import static com.dio.santander.persistence.config.ConnectionConfig.getConnection;
import com.dio.santander.persistence.migration.MigrationStrategy;
import com.dio.santander.ui.MainMenu;

public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        try(var connection = getConnection()){
            new MigrationStrategy(connection).executeMigration();            
        }   
        new MainMenu().execute();
    }

}
