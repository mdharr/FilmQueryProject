package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Category;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	@Override
	public Film findFilmById(int filmId) throws SQLException {

		Film film = null;

		String userName = "student";
		String pwd = "student";

		String sql = "SELECT * FROM film WHERE film.id = ?";

		Connection conn = DriverManager.getConnection(URL, userName, pwd);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);

		ResultSet filmResult = stmt.executeQuery();

		if (filmResult.next()) {
			film = new Film(); // create the object

			// here is our mapping of query columns to our object fields:
			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setReleaseYear(filmResult.getInt("release_year"));
			film.setLanguageId(filmResult.getInt("language_id"));
			film.setRentalDuration(filmResult.getInt("rental_duration"));
			film.setRentalRate(filmResult.getDouble("rental_rate"));
			film.setLength(filmResult.getInt("length"));
			film.setReplacementCost(filmResult.getDouble("replacement_cost"));
			film.setRating(filmResult.getString("rating"));
			film.setSpecialFeatures(filmResult.getString("special_features"));

		}
		stmt.close();
		conn.close();

		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {

		Actor actor = null;

		String userName = "student";
		String pwd = "student";

		// ...
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor WHERE id = ?";

		Connection conn = DriverManager.getConnection(URL, userName, pwd);

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);

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
		stmt.close();
		conn.close();

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {

		List<Actor> actors = new ArrayList<>();

		String userName = "student";
		String pwd = "student";

		// ...
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM film JOIN film_actor ON film.id = film_actor.film_id JOIN actor ON actor.id = film_actor.actor_id WHERE film.id = ?";

		Connection conn = DriverManager.getConnection(URL, userName, pwd);

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);

		ResultSet actorResult = stmt.executeQuery();

		while (actorResult.next()) {
			Actor actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
			
			actors.add(actor);
		}

		stmt.close();
		conn.close();

		return actors;
	}

	@Override
	public List<Film> findFilmsBySearchWord(String searchWord) throws SQLException {
		List<Film> films = new ArrayList<>();
		
		String userName = "student";
		String pwd = "student";

		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features FROM film WHERE film.title LIKE ? OR film.description LIKE ?";

		Connection conn = DriverManager.getConnection(URL, userName, pwd);

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + searchWord + "%");
		stmt.setString(2, "%" + searchWord + "%");

		ResultSet filmResult = stmt.executeQuery();

		while (filmResult.next()) {
			Film film = new Film();
			
			film.setId(filmResult.getInt("film.id"));
			film.setTitle(filmResult.getString("film.title"));
			film.setDescription(filmResult.getString("film.description"));
			film.setReleaseYear(filmResult.getInt("film.release_year"));
			film.setLanguageId(filmResult.getInt("film.language_id"));
			film.setRentalDuration(filmResult.getInt("film.rental_duration"));
			film.setRentalRate(filmResult.getDouble("film.rental_rate"));
			film.setLength(filmResult.getInt("film.length"));
			film.setReplacementCost(filmResult.getDouble("film.replacement_cost"));
			film.setRating(filmResult.getString("film.rating"));
			film.setSpecialFeatures(filmResult.getString("film.special_features"));

			// Assuming you have a user object


			films.add(film);

		}

		stmt.close();
		conn.close();

		return films;
	}

	@Override
	public Category findCategoriesByFilmId(int filmId) throws SQLException {
		Category category = null;

		String userName = "student";
		String pwd = "student";

		// ...
		String sql = "select category.id, category.name from category join film_category on category.id = category_id join film on film.id = film_id where film.id = ?";

		Connection conn = DriverManager.getConnection(URL, userName, pwd);

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);

		ResultSet categoryResult = stmt.executeQuery();

		if (categoryResult.next()) {
			category = new Category(); // create the object

			// here is our mapping of query columns to our object fields:
			category.setId(categoryResult.getInt("category.id"));
			category.setName(categoryResult.getString("category.name"));

		}
		// ... conn.close();
		stmt.close();
		conn.close();

		return category;
	}

}
