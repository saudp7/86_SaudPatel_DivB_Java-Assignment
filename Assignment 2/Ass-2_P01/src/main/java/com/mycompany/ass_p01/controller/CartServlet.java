/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ass_p01.controller;

import com.mycompany.ass_p01.dao.ProductDAO;
import com.mycompany.ass_p01.model.CartItem;
import com.mycompany.ass_p01.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kevil-gandhi
 */
//@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
@WebServlet(name = "CartServlet", urlPatterns = {"/secure/cart"})
public class CartServlet extends HttpServlet {

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
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart
                = (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("add".equals(action) && idStr != null) {
            int productId = Integer.parseInt(idStr);
            ProductDAO dao = new ProductDAO();
            Product p = dao.getAllProducts()
                    .stream()
                    .filter(pr -> pr.getProductId() == productId)
                    .findFirst()
                    .orElse(null);
            if (p != null) {
                CartItem item = cart.get(productId);
                if (item == null) {
                    item = new CartItem();
                    item.setProductId(productId);
                    item.setProductName(p.getProductName());
                    item.setPrice(p.getPrice());
                    item.setQuantity(1);
                    cart.put(productId, item);
                } else {
                    item.setQuantity(item.getQuantity() + 1);
                }
            }
            response.sendRedirect("cart");
            return;
        }

        if ("remove".equals(action) && idStr != null) {
            cart.remove(Integer.parseInt(idStr));
            response.sendRedirect("cart");
            return;
        }

        // DISPLAY CART
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Your Shopping Cart</h2>");
        out.println("<a href='shop'>Back to Shop</a><br><br>");

        double grandTotal = 0;

        out.println("<table border='1'>");
        out.println("<tr><th>Product</th><th>Price</th><th>Qty</th><th>Total</th><th>Action</th></tr>");

        for (CartItem item : cart.values()) {
            grandTotal += item.getTotal();
            out.println("<tr>");
            out.println("<td>" + item.getProductName() + "</td>");
            out.println("<td>" + item.getPrice() + "</td>");
            out.println("<td>" + item.getQuantity() + "</td>");
            out.println("<td>" + item.getTotal() + "</td>");
            out.println("<td><a href='cart?action=remove&id=" + item.getProductId() + "'>Remove</a></td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<h3>Grand Total: ₹" + grandTotal + "</h3>");

        out.println("<a href='order'>Proceed to Checkout</a>");
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
