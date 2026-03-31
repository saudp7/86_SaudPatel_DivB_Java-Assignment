<%-- 
    Document   : cart
    Created on : Mar 11, 2026, 10:23:06 PM
    Author     : kevil-gandhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bean.CartBean,model.Book,java.util.*"%>

<%--
    CartBean cart = (CartBean) application.getAttribute("cart");
--%>

<jsp:useBean id="cart" class="bean.CartBean" scope="session" />

<h2>Your Cart</h2>

<table border="1">

    <tr>
        <th>Title</th>
        <th>Price</th>
    </tr>

    <%
        for (Book b : cart.getCart()) {
    %>

    <tr>
        <td><%=b.title%></td>
        <td><%=b.price%></td>
    </tr>

    <%
        }
    %>
    
</table>

<a href="bill.jsp">Generate Bill</a>