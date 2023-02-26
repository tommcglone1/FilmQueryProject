package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;

class DatabaseAccessTests {
	private DatabaseAccessor db;

	@BeforeEach
	void setUp() throws Exception {
		db = new DatabaseAccessorObject();
	}

	@AfterEach
	void tearDown() throws Exception {
		db = null;
	}

	@Test
	@DisplayName("Test findActorById and return Actor with that ID")
	void testFindActorById() {
		Actor actor = db.findActorById(13);
		assertNotNull(actor);
		assertEquals("Uma Wood", actor.getFirstName() + " " + actor.getLastName());
	}

	@Test
	@DisplayName("Test findActorById with invalid ID returning null")
	void testFindActorByIdNull() {
		Actor actor = db.findActorById(-42);
		assertNull(actor);
	}

	@Test
	@DisplayName("Test findActorsByFilmID returning populated List with actor names")
	void testFindActorsByFilmId() {
		List<Actor> actorList = db.findActorsByFilmId(1);
		assertFalse(actorList.isEmpty());
		assertEquals("Penelope Guiness", actorList.get(0).toString());
	}

	@Test
	@DisplayName("Test findActorsByFilmID with invalid ID returning empty list")
	void testFindActorsByFilmIdEmpty() {
		List<Actor> actorList = db.findActorsByFilmId(-42);
		assertTrue(actorList.isEmpty());
	}

	@Test
	@DisplayName("Test findFilmByID returning film with that ID")
	void testFindFilmById() {
		Film film = db.findFilmById(1);
		assertNotNull(film);
		assertEquals("ACADEMY DINOSAUR", film.getTitle());
	}

	@Test
	@DisplayName("Test findFilmById with invalid ID returning null")
	void testFindFilmByIdNull() {
		Film film = db.findFilmById(-42);
		assertNull(film);
	}

	@Test
	@DisplayName("Test findFilmByKeyword returning films with that keyword in a list")
	void testfindFilmByKeyWord() {
		List<Film> filmList = db.findFilmByKeyWord("ads");
		assertFalse(filmList.isEmpty());
		assertEquals("cat coneheads", filmList.get(0).getTitle().toLowerCase());
	}

	@Test
	@DisplayName("Test findFilmByKeyword with invalid keyword returning empty List")
	void testFindFilmByKeyWordEmpty() {
		List<Film> filmList = db.findFilmByKeyWord("asd");
		assertTrue(filmList.isEmpty());
	}

	@Test
	@DisplayName("Test findInventoryByFilmId returning inventory objects in list")
	void testFindInventoryByFilmKeyword() {
		List<Inventory> invList = db.findInventoryByFilmId(1);
		assertFalse(invList.isEmpty());
	}

	@Test
	@DisplayName("Test findInventoryByFilmKeyword with invalid keyword returning empty List")
	void testfindInventoryByFilmKeywordEmpty() {
		List<Inventory> invList = db.findInventoryByFilmId(-42);
		assertTrue(invList.isEmpty());
	}

	@Test
	@DisplayName("Test allFilmDetails returning film with that ID with all film details")
	void testAllFilmDetailsWithId() {
		Film film = db.allFilmDetails(1);
		assertNotNull(film);
		assertEquals("ACADEMY DINOSAUR", film.getTitle());
	}

	@Test
	@DisplayName("Test allFilmDetails with invalid ID returning null")
	void testAllFilmDetailsWithIdEmpty() {
		Film film = db.allFilmDetails(-42);
		assertNull(film);
	}

}
