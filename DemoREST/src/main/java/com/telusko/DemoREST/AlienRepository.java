package com.telusko.DemoREST;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlienRepository {

	Connection con = null;

	public AlienRepository() {

		String url = "jdbc:mysql://localhost:3306/restdb?serverTimezone=UTC";
		String username = "root";
		String password = "Pa88word";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<>();
		String sql = "select * from alien";
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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return aliens;
	}

	public Alien getAlien(int id) {

		String sql = "select * from alien where id=" + id;
		Alien a = new Alien();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return a;
	}

	public void create(Alien newAlien) {
		String sql = "insert into alien values (?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, newAlien.getId());
			st.setString(2, newAlien.getName());
			st.setInt(3, newAlien.getPoints());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void update(Alien updatedAlien) {
		String sql = "update alien set name=?, points=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, updatedAlien.getName());
			st.setInt(2, updatedAlien.getPoints());
			st.setInt(3, updatedAlien.getId());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void delete(int id) {
		String sql = "delete from alien where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
