/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ass_p01.dao;

import com.mycompany.ass_p01.model.Product;
import com.mycompany.ass_p01.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevil-gandhi
 */
public class ProductDAO {

    // ADD PRODUCT
    public boolean addProduct(Product p) {
        String sql = "INSERT INTO product_master "
                + "(product_name, price, unit, discount, image, category_id, stock) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getProductName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getUnit());
            ps.setDouble(4, p.getDiscount());
            ps.setString(5, p.getImage());
            ps.setInt(6, p.getCategoryId());
            ps.setInt(7, p.getStock());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // FETCH ALL PRODUCTS
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product_master";

        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setPrice(rs.getDouble("price"));
                p.setUnit(rs.getString("unit"));
                p.setDiscount(rs.getDouble("discount"));
                p.setImage(rs.getString("image"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setStock(rs.getInt("stock"));
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // DELETE PRODUCT
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM product_master WHERE product_id=?";

        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
