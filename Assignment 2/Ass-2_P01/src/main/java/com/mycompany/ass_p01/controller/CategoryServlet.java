/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ass_p01.controller;

import com.mycompany.ass_p01.dao.CategoryDAO;
import com.mycompany.ass_p01.model.Category;
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
//@WebServlet(name = "CategoryServlet", urlPatterns = {"/CategoryServlet"})
@WebServlet(name = "CategoryServlet", urlPatterns = {"/secure/category"})
public class CategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet CategoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryServlet at " + request.getContextPath() + "</h1>");
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

        CategoryDAO dao = new CategoryDAO();
        List<Category> list = dao.getAllCategories();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h2>Category Master</h2>");

        out.println("<form method='post'>");
        out.println("Category Name: <input name='name'><br>");
        out.println("Parent Category ID: <input name='parentId'><br>");
        out.println("<button>Add Category</button>");
        out.println("</form><hr>");

        out.println("<h3>Existing Categories</h3>");
        for (Category c : list) {
            out.println(c.getCategoryId() + " - " + c.getCategoryName()
                    + " <a href='category?del=" + c.getCategoryId() + "'>Delete</a><br>");
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

        String name = request.getParameter("name");
        String parent = request.getParameter("parentId");

        Category c = new Category();
        c.setCategoryName(name);

        if (parent != null && !parent.isEmpty()) {
            c.setParentCategoryId(Integer.parseInt(parent));
        }

        CategoryDAO dao = new CategoryDAO();
        dao.addCategory(c);

        response.sendRedirect("category");
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
