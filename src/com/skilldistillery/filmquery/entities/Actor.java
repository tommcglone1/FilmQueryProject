package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Actor {
	private int id;
	private String firstName;
	private String lastName;
	

	public Actor() {

	}
	public Actor(String fn, String ln) {
		this.firstName = fn;
		this.lastName = ln;
	}

	public Actor(int id, String fn, String ln) {
		this(fn, ln);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return firstName  + " " + lastName;
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
		Actor other = (Actor) obj;
		return id == other.id;
	}

}
