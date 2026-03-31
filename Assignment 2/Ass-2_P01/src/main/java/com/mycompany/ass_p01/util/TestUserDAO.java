/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ass_p01.util;

import com.mycompany.ass_p01.dao.UserDAO;
import com.mycompany.ass_p01.model.User;

/**
 *
 * @author kevil-gandhi
 */
public class TestUserDAO {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User u = new User();
        u.setUsername("Test User");
        u.setLoginId("test");
        u.setPassword("123");
        u.setEmail("test@test.com");

        System.out.println(dao.register(u));
    }
}
