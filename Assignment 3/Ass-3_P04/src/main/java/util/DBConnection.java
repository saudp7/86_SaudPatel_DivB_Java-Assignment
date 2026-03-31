/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author kevil-gandhi
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookshop?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "8466");

    }

}
