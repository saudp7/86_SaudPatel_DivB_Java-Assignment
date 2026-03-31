/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ass_p01.controller;

import com.mycompany.ass_p01.dao.CategoryDAO;
import com.mycompany.ass_p01.dao.ProductDAO;
import com.mycompany.ass_p01.model.Category;
import com.mycompany.ass_p01.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author kevil-gandhi
 */
@WebServlet(name = "ShopServlet", urlPatterns = {"/secure/shop"})
public class ShopServlet extends HttpServlet {

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
            out.println("<title>Servlet ShopServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopServlet at " + request.getContextPath() + "</h1>");
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

        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();

        List<Category> categories = categoryDAO.getAllCategories();
        List<Product> products = productDAO.getAllProducts();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h2>Fashion Store - Shop</h2>");
        out.println("<a href='home.html'>Home</a> | ");
        out.println("<a href='../logout'>Logout</a><hr>");

        // Categories
        out.println("<h3>Categories</h3>");
        for (Category c : categories) {
            out.println("<a href='shop?cat=" + c.getCategoryId() + "'>"
                    + c.getCategoryName() + "</a><br>");
        }

        out.println("<hr><h3>Products</h3>");

        String catId = request.getParameter("cat");

        for (Product p : products) {

            if (catId != null
                    && p.getCategoryId() != Integer.parseInt(catId)) {
                continue;
            }

            out.println("<div style='border:1px solid #ccc; padding:10px; margin:10px;'>");
            out.println("<b>" + p.getProductName() + "</b><br>");
            out.println("Price: ₹" + p.getPrice() + "<br>");
            out.println("Discount: " + p.getDiscount() + "%<br>");
            out.println("Stock: " + p.getStock() + "<br>");

            out.println("<a href='cart?action=add&id=" + p.getProductId() + "'>");
            out.println("Add to Cart</a>");
//            out.println("<a href='cart?action=add&id=product_id'>Add to Cart</a>");
//            out.println("<a href='cart?action=add&id=" + p.getProductId() + "'>");Add to Cart</a>");

            out.println("</div>");
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
        processRequest(request, response);
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
