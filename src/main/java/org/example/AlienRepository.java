package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

    //JDBC
    Connection con = null;

    public AlienRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/neon", "root", "root");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public List<Alien> getAliens() {
        List<Alien> aliens = new ArrayList<>();
        String sql = "select * from alien1";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Alien a = new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));

                aliens.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return aliens;
    }

    public Alien getAlien(int id) {
        String sql = "select * from alien1 where id="+id;
        Alien a = new Alien();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    public void create(Alien a) {
        String sql = "insert into alien1 values (?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.setString(2, a.getName());
            st.setInt(3, a.getPoints());
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Alien a) {
        String sql = "update alien1 set name=?,points=? where id =?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(3, a.getId());
            st.setString(1, a.getName());
            st.setInt(2, a.getPoints());
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAlien(int id) {
        String sql = "delete from alien1 where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //    List<Alien> aliens;
//
//    public AlienRepository() {
//        aliens = new ArrayList<>();
//
//        Alien a1 = new Alien();
//        a1.setId(101);
//        a1.setName("Navin");
//        a1.setPoints(10);
//
//        Alien a2 = new Alien();
//        a2.setId(102);
//        a2.setName("Arthi");
//        a2.setPoints(20);
//
//        aliens.add(a1);
//        aliens.add(a2);
//    }
//
//    public List<Alien> getAliens() {
//        return aliens;
//    }
//
//    public Alien getAlien(int id) {
//        for(Alien a: aliens) {
//            if(a.getId() == id) {
//                return a;
//            }
//        }
//        return null;
//    }
//
//    public void create(Alien a) {
//        aliens.add(a);
//    }
}
