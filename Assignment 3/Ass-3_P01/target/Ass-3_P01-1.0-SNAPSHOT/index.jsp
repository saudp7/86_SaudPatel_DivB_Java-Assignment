<%-- 
    Document   : index.jsp
    Created on : Mar 11, 2026, 5:02:06 PM
    Author     : kevil-gandhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <form action="CurrencyServlet" method="post">

            From Currency:
            <!--<input type="text" name="from">-->
            <select name="from">
                <option value="USD">USD</option>
                <option value="INR">INR</option>
                <option value="EUR">EUR</option>
            </select>

            To Currency:
            <!--<input type="text" name="to">-->
            <select name="to">
                <option value="USD">USD</option>
                <option value="INR">INR</option>
                <option value="EUR">EUR</option>
            </select>

            Amount:
            <input type="text" name="amount">

            <input type="submit" value="Convert">

        </form>
    </body>
</html>
