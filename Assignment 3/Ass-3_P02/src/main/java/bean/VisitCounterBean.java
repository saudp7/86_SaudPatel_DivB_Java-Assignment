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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBConnection;

@Stateful
public class VisitCounterBean {

    public int recordVisit(String ip) {

        int visits = 1;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement check
                    = con.prepareStatement("SELECT visits FROM page_visits WHERE ip_address=?");

            check.setString(1, ip);

            ResultSet rs = check.executeQuery();

            if (rs.next()) {

                visits = rs.getInt("visits") + 1;

                PreparedStatement update
                        = con.prepareStatement("UPDATE page_visits SET visits=? WHERE ip_address=?");

                update.setInt(1, visits);
                update.setString(2, ip);

                update.executeUpdate();

            } else {

                PreparedStatement insert
                        = con.prepareStatement("INSERT INTO page_visits VALUES (?,1)");

                insert.setString(1, ip);

                insert.executeUpdate();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return visits;

    }

}
