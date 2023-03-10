package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;

public interface DatabaseAccessor {
	public Actor findActorById(int actorId);
	public List<Actor> findActorsByFilmId(int filmId);
	public Film findFilmById(int filmId);
	public List<Film> findFilmByKeyWord(String userInput);
	public Film allFilmDetails(int filmId);
	public List<Inventory> findInventoryByFilmId(int filmId);
}
