/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ass_p01.dao;

import com.mycompany.ass_p01.model.CartItem;
import com.mycompany.ass_p01.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author kevil-gandhi
 */
public class OrderDAO {

    public int createOrder(String sessionId, double totalAmount) {

        String sql = "INSERT INTO order_master "
                + "(order_datetime, session_id, payment_mode, tax, total_amount, order_status) "
                + "VALUES (NOW(), ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, sessionId);
            ps.setString(2, "COD");
            ps.setDouble(3, totalAmount * 0.05); // 5% tax
            ps.setDouble(4, totalAmount);
            ps.setString(5, "PLACED");

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // order_id
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addOrderDetails(int orderId, Map<Integer, CartItem> cart) {

        String sql = "INSERT INTO order_details "
                + "(order_id, product_id, product_price, discount) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            for (CartItem item : cart.values()) {
                ps.setInt(1, orderId);
                ps.setInt(2, item.getProductId());
                ps.setDouble(3, item.getPrice());
                ps.setDouble(4, 0); // discount (optional)
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
