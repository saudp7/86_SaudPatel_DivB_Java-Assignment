/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ass_p01.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.mycompany.ass_p01.dao.ProductDAO;
import com.mycompany.ass_p01.dao.CategoryDAO;
import com.mycompany.ass_p01.model.Product;
import com.mycompany.ass_p01.model.Category;
import java.util.List;

/**
 *
 * @author kevil-gandhi
 */
//@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
@WebServlet(name = "ProductServlet", urlPatterns = {"/secure/product"})
public class ProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        List<Product> products = productDAO.getAllProducts();
        List<Category> categories = categoryDAO.getAllCategories();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h2>Product Master</h2>");

        out.println("<form method='post'>");
        out.println("Product Name: <input name='name'><br>");
        out.println("Price: <input name='price'><br>");
        out.println("Unit: <input name='unit'><br>");
        out.println("Discount (%): <input name='discount'><br>");
        out.println("Stock: <input name='stock'><br>");
        out.println("Image URL: <input name='image'><br>");

        out.println("Category: <select name='categoryId'>");
        for (Category c : categories) {
            out.println("<option value='" + c.getCategoryId() + "'>"
                    + c.getCategoryName() + "</option>");
        }
        out.println("</select><br>");

        out.println("<button>Add Product</button>");
        out.println("</form><hr>");

        out.println("<h3>Product List</h3>");
        for (Product p : products) {
            out.println(p.getProductId() + " - " + p.getProductName()
                    + " | ₹" + p.getPrice()
                    + " | Stock: " + p.getStock()
                    + " <a href='product?del=" + p.getProductId() + "'>Delete</a><br>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

        Product p = new Product();
        p.setProductName(request.getParameter("name"));
        p.setPrice(Double.parseDouble(request.getParameter("price")));
        p.setUnit(request.getParameter("unit"));
        p.setDiscount(Double.parseDouble(request.getParameter("discount")));
        p.setStock(Integer.parseInt(request.getParameter("stock")));
        p.setImage(request.getParameter("image"));
        p.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));

        ProductDAO dao = new ProductDAO();
        dao.addProduct(p);

        response.sendRedirect("product");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
