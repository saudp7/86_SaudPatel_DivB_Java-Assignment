<%-- 
    Document   : books
    Created on : Mar 11, 2026, 10:22:40 PM
    Author     : kevil-gandhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,model.Book"%>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
%>

<h2>Book Store</h2>

<table border="1">

    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Price</th>
        <th>Add</th>
    </tr>

    <%
        for (Book b : books) {
    %>

    <tr>

        <td><%=b.id%></td>
        <td><%=b.title%></td>
        <td><%=b.price%></td>

        <td>

            <form action="cart" method="post">

                <input type="hidden" name="id" value="<%=b.id%>">
                <input type="hidden" name="title" value="<%=b.title%>">
                <input type="hidden" name="price" value="<%=b.price%>">

                <input type="submit" value="Add To Cart">

            </form>

        </td>

    </tr>

    <%
        }
    %>

</table>

<a href="cart.jsp">View Cart</a>
