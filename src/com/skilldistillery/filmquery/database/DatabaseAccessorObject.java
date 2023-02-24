package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode"
			+ "=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PASS = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		PreparedStatement ps = null;
		ResultSet filmResult = null;
		Connection conn = null;
		String query = "SELECT * FROM film WHERE id = ?";

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			ps = conn.prepareStatement(query);
			ps.setInt(1, filmId);
			filmResult = ps.executeQuery();
			if (filmResult.next()) {
				film = new Film(filmResult.getInt("id"), filmResult.getString("title"),
						filmResult.getString("description"), filmResult.getInt("release_year"),
						filmResult.getInt("language_id"), filmResult.getInt("rental_duration"),
						filmResult.getDouble("rental_rate"), filmResult.getInt("length"),
						filmResult.getDouble("replacement_cost"), filmResult.getString("rating"),
						filmResult.getString("special_features"), findActorsByFilmId(filmId));
			}
			filmResult.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (filmResult != null) {
					filmResult.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		PreparedStatement stmt = null;
		ResultSet actorResult = null;
		Connection conn = null;

		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
						actorResult.getString("last_name"));
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException sqle) {
			System.err.println(sqle);
			sqle.printStackTrace();
		} finally {
			try {
				if (actorResult != null) {
					actorResult.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}

		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet listResult = null;
		Connection conn = null;
		String query = "SELECT actor.* FROM film JOIN film_actor ON film.id = film_actor.film_id JOIN actor ON film_actor.actor_id = actor.id  WHERE film.id = ?;";

		try {

			conn = DriverManager.getConnection(URL, USER, PASS);
			ps = conn.prepareStatement(query);
			ps.setInt(1, filmId);
			listResult = ps.executeQuery();
			while (listResult.next()) {
				Actor actor = new Actor(listResult.getInt("id"), listResult.getString("first_name"),
						listResult.getString("last_name"));
				actorList.add(actor);

			}
			listResult.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (listResult != null) {
					listResult.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}
		}
		return actorList;
	}
}
