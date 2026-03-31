# Assignment 3 - Enterprise Java (EJB)

## Folder Contents
- `Ass-3_P01/` - Currency converter using EJB + DB conversion factors
- `Ass-3_P02/` - Stateful visit counter bean (IP + visit count persistence)
- `Ass-3_P03/` - Product and Category CRUD using EJB logic + JDBC
- `Ass-3_P04/` - Book shop shopping cart and billing flow
- `DB Script.txt` - database setup script

## Mapping to Question Set
1. Currency converter with EJB -> `Ass-3_P01`
2. Stateful bean visit tracking -> `Ass-3_P02`
3. Product/Category CRUD with EJB + JDBC -> `Ass-3_P03`
4. Book shop shopping cart -> `Ass-3_P04`

## Important Entry Servlets
- `Ass-3_P01/src/main/java/servlet/CurrencyServlet.java`
- `Ass-3_P02/src/main/java/servlet/VisitServlet.java`
- `Ass-3_P03/src/main/java/servlet/ProductServlet.java`
- `Ass-3_P04/src/main/java/servlet/BookServlet.java`
- `Ass-3_P04/src/main/java/servlet/CartServlet.java`

## Run Notes
- All are Maven web projects (`pom.xml` present).
- Deploy from NetBeans to a configured Jakarta EE server.
- Run `DB Script.txt` before executing DB-backed workflows.

