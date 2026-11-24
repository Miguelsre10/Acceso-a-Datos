/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema2_conectores_sql_p1;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author miguel
 */
public class ConnectionManager {
    private static Connection conn = null; 
	
	
	public static Connection getConnection () throws Exception {
            if (conn == null) {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    conn = (Connection) DriverManager.getConnection (
                        "jdbc:mysql://localhost:3306/instituto?serverTimezone=UTC",
                        "root", "1234");
            }
            return conn;
	}
    
}
