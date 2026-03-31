/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package bean;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;

/**
 *
 * @author kevil-gandhi
 */
import jakarta.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;
import model.Book;

@Stateful
public class CartBean {

    List<Book> cart = new ArrayList<>();

    public void addBook(Book b) {

        cart.add(b);

    }

    public List<Book> getCart() {

        return cart;

    }

    public double getTotal() {

        double total = 0;

        for (Book b : cart) {

            total += b.price;

        }

        return total;

    }

    public void clearCart() {

        cart.clear();

    }

}
