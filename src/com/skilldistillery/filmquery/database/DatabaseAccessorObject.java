package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

//	public DatabaseAccessorObject() throws ClassNotFoundException {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//
//	}
	
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		// TODO Auto-generated method stub
		Actor actor = null;
		
		String userName = "student";
		String pwd = "student";

		// ...
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

		Connection conn = DriverManager.getConnection(URL, userName, pwd);

		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		
		System.out.println(stmt);

		ResultSet actorResult = stmt.executeQuery();

		if (actorResult.next()) {
			actor = new Actor(); // create the object

			// here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));

//			actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
		}
		// ... conn.close();
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}
