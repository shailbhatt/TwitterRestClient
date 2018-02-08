package com.rest.model;

public class Tweet {
	public String text;
	public String created_at;

	public String toString() {
		return "Posted " + text + " at " + created_at;
	}
}
