/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package bean;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;

/**
 *
 * @author kevil-gandhi
 */
import jakarta.ejb.Stateless;
import java.sql.*;
import java.util.*;
import model.Product;
import util.DBConnection;

@Stateless
public class ProductBean {

    public void addProduct(String name, double price, int stock, int catId) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO product_master(product_name,price,stock,category_id) VALUES (?,?,?,?)");

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, stock);
            ps.setInt(4, catId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProducts() {

        List<Product> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM product_master");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product p = new Product();

                p.id = rs.getInt("product_id");
                p.name = rs.getString("product_name");
                p.price = rs.getDouble("price");
                p.stock = rs.getInt("stock");
                p.categoryId = rs.getInt("category_id");

                list.add(p);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void deleteProduct(int id) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement("DELETE FROM product_master WHERE product_id=?");

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
