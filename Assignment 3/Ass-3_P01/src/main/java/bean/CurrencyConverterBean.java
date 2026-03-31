/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package bean;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBConnection;

/**
 *
 * @author kevil-gandhi
 */
@Stateless
@LocalBean
public class CurrencyConverterBean {

    public double convert(String from, String to, double amount) {

        double rate = 0;

        try {

            Connection con = DBConnection.getConnection();

//            PreparedStatement ps = con.prepareStatement(
//                    "select rate from currency_rates where from_currency=? and to_currency=?");
            PreparedStatement ps = con.prepareStatement(
                    "SELECT rate FROM currency_rates WHERE UPPER(from_currency)=? AND UPPER(to_currency)=?");

//            ps.setString(1, from);
//            ps.setString(2, to);
            ps.setString(1, from.toUpperCase());
            ps.setString(2, to.toUpperCase());

            System.out.println("FROM: " + from);
            System.out.println("TO: " + to);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rate = rs.getDouble("rate");
                System.out.println("RATE FOUND: " + rate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return amount * rate;

    }
}
