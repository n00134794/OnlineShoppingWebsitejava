package com.example.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static Connection sConnection;
    
    public static Connection getInstance () throws ClassNotFoundException, SQLException {
        String host, db, user, password;
        
        host = "daneel";
        db = "n00134794";
        user = "N00134794";
        password = "N00134794";
        
        if (sConnection == null || sConnection.isClosed()) {
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            sConnection = DriverManager.getConnection(url, user, password);
            }
                
            return sConnection;  
    }
}