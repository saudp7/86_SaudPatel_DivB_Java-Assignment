<%-- 
    Document   : bill
    Created on : Mar 11, 2026, 10:23:35 PM
    Author     : kevil-gandhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bean.CartBean"%>

<%--
    CartBean cart = (CartBean) application.getAttribute("cart");
--%>

<jsp:useBean id="cart" class="bean.CartBean" scope="session" />

<h2>Final Bill</h2>

Total Amount : <%=cart.getTotal()%>