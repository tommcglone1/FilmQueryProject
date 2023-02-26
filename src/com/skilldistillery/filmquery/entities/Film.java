package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private Integer releaseYear;
	private int languageId;
	private String languageName;
	private int rentalDuration;
	private double rentalRate;
	private Integer length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actorList;
	private String categoryName;
	private List<Inventory> inventroyList;

	public Film() {

	}

	public Film(String title, String description, Integer releaseYear, String rating, List<Actor> actorList) {
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.actorList = actorList;
	}

	public Film(int id, String title, String description, Integer releaseYear, String rating, String languageName,
			List<Actor> actorList) {
		this(title, description, releaseYear, rating, actorList);
		this.id = id;
		this.languageName = languageName;
	}

	public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
			double rentalRate, Integer length, double replacementCost, String rating, String specialFeatures,
			List<Actor> actorList, String languageName, String categoryName, List<Inventory> inventoryList) {
		this(id, title, description, releaseYear, rating, languageName, actorList);
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.specialFeatures = specialFeatures;
		this.categoryName = categoryName;
		this.actorList = actorList;
		this.inventroyList = inventoryList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void printActorList() {
		List<Actor> actorList = getActorList();
		System.out.print("Starring: ");
		for (int i = 0; i < actorList.size(); i++) {
			if (i == actorList.size() - 1) {
				System.out.println("and \n" + actorList.get(i));
			} else {
				System.out.println(actorList.get(i));
			}
		}
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Inventory> getInventroyList() {
		return inventroyList;
	}
	
	public void printInventoryList() {
		List<Inventory> invList = getInventroyList();
		for (Inventory inv : invList) {
			System.out.println(inv);
		}
	}

	public void setInventroyList(List<Inventory> inventroyList) {
		this.inventroyList = inventroyList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return id == other.id;
	}

	@Override
	public String toString() {

		return "Title: " + title + "\nDescription: " + description + "\nReleaseYear: " + releaseYear + "\nRating: "
				+ rating + "\nLanguage: " + languageName;
	}

	public String printAllDetails() {
		return "Film id: " + id + "\nTitle: " + title + "\nDescription: " + description + "\nRealease Year: "
				+ releaseYear + "\nLanguage ID: " + languageId + "\nLanguage: " + languageName + "\nReantal Duration: "
				+ rentalDuration + "\nReantal Rate: " + "$" + rentalRate + "\nLength: " + length + " mins"
				+ "\nReplacement Cost: " + "$" + replacementCost + "\nRating: " + rating + "\nSpecial Features: "
				+ specialFeatures + "\nGenre: " + categoryName;

	}
}
