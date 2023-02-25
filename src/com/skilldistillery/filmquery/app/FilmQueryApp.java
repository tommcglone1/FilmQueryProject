package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

//  private void test() {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//    
//    Actor actor = db.findActorById(1);
//    System.out.println(actor);
//    
//    List<Actor> actorList = db.findActorsByFilmId(1);
//    for (Actor listedActors : actorList) {
//    	System.out.println(listedActors);
//	}
//  }

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

		menu(input);

	}

	public void menu(Scanner input) {
		boolean isMakingSelection = true;
		int userInput = 0;

		while (isMakingSelection) {
			System.out.println("-------Hello, please make a numerical selection...-------");
			System.out.println("|         1. Look up film by ID number                  |");
			System.out.println("|         2. Look up film by keyword                    |");
			System.out.println("|                3. Exit                                |");
			System.out.println("----------------------------------------------------------");

			userInput = input.nextInt();

			input.nextLine();
			switch (userInput) {
			case 1:
				lookUpById(input);
				break;
			case 2:
				lookUpByKeyword(input);
				break;
			case 3:
				System.out.println("Thanks for your queries, goodbye!");
				isMakingSelection = false;
				break;
			default:
				System.out.println("Not a valid selection please try again");
				break;
			}
		}
	}

	public void lookUpById(Scanner input) {
		int userInput = 0;
		System.out.print("Please enter your films ID number: ");
		userInput = input.nextInt();
		input.nextLine();
		Film film = db.findFilmById(userInput);

		if (film == null) {
			System.out.println("Sorry, there is no film with that ID number.");
		} else {
			System.out.println(film);
			System.out.println();
			film.printActorList();
			System.out.println();
			viewAllFilmDetails(input, userInput);
		}
	}

	public void lookUpByKeyword(Scanner input) {
		String userInput = null;
		List<Film> films;
		System.out.print("Please enter a keyword related to your film: ");
		userInput = input.next();
		films = db.findFilmByKeyWord(userInput);

		if (films.isEmpty()) {
			System.out.println("Sorry, there is no film matching that description.");
		} else {
			for (Film film : films) {
				System.out.println(film);
				System.out.println();
				film.printActorList();
				System.out.println();
				
			}
		}
	}

	public void viewAllFilmDetails(Scanner input, int filmId) {
		int userInput;
		System.out.println("------------------------------------------------");
		System.out.println("Input 1 to return to main menu");
		System.out.println("Input 2 to view all details about movie selected");
		System.out.println("------------------------------------------------");
		userInput = input.nextInt();
		input.nextLine();
		switch (userInput) {
		case 1:
			break;
		case 2:
			Film allDetailsFilm = db.allFilmDetails(filmId); 
			System.out.println(allDetailsFilm.printAllDetails());
			System.out.println();
			allDetailsFilm.printActorList();
			System.out.println();
			allDetailsFilm.printInventoryList();
			
		
		}

	}

}
