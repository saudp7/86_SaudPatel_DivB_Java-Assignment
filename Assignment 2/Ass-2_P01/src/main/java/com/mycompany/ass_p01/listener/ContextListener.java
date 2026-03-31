/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package com.mycompany.ass_p01.listener;

import com.mycompany.ass_p01.util.DBUtil;
import com.mycompany.ass_p01.util.MailUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Web application lifecycle listener.
 *
 * @author kevil-gandhi
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String sql
                = "SELECT email, username FROM user_master "
                + "WHERE DAY(dob)=DAY(CURDATE()) AND MONTH(dob)=MONTH(CURDATE())";

        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String email = rs.getString("email");
                String name = rs.getString("username");

                MailUtil.sendMail(
                        email,
                        "Happy Birthday 🎉",
                        "Dear " + name + ",\n\nHappy Birthday!\n\nRegards,\nFashion Store"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
