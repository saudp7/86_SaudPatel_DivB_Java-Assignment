/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ass_p01.dao;

/**
 *
 * @author kevil-gandhi
 */
import com.mycompany.ass_p01.model.Category;
import com.mycompany.ass_p01.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    // ADD CATEGORY
    public boolean addCategory(Category c) {
        String sql = "INSERT INTO category_master (category_name, parent_category_id) VALUES (?, ?)";

        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCategoryName());

            if (c.getParentCategoryId() == null) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, c.getParentCategoryId());
            }

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // FETCH ALL CATEGORIES
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM category_master";

        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setParentCategoryId((Integer) rs.getObject("parent_category_id"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // DELETE CATEGORY
    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM category_master WHERE category_id=?";

        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
