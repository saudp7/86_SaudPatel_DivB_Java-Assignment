<%-- 
    Document   : product
    Created on : Mar 11, 2026, 9:25:22 PM
    Author     : kevil-gandhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="bean.ProductBean,model.Product,java.util.*" %>

<%
    ProductBean bean = new ProductBean();
    List<Product> list = bean.getProducts();
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Product CRUD</title>
    </head>

    <body>

        <h2>Add Product</h2>

        <form action="product" method="post">

            <input type="hidden" name="action" value="add">

            Name:
            <input type="text" name="name">

            Price:
            <input type="text" name="price">

            Stock:
            <input type="text" name="stock">

            Category ID:
            <input type="text" name="cat">

            <input type="submit" value="Add">

        </form>

        <hr>

        <h2>Product List</h2>

        <table border="1">

            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Category</th>
                <th>Delete</th>
            </tr>

            <%
                for (Product p : list) {
            %>

            <tr>

                <td><%=p.id%></td>
                <td><%=p.name%></td>
                <td><%=p.price%></td>
                <td><%=p.stock%></td>
                <td><%=p.categoryId%></td>

                <td>

                    <form action="product" method="post">

                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%=p.id%>">

                        <input type="submit" value="Delete">

                    </form>

                </td>

            </tr>

            <%
                }
            %>

        </table>

    </body>
</html>
