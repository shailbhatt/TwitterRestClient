package com.rest.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
@Service
public class TwitterTimeline {
	public String screenName;
	private String fetchUrl = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=s2h0a1i2l";
//	private String fetchUrl = "https://api.twitter.com/1.1/statuses/user_timeline.json";

	public TwitterTimeline(String screenName) {
		this.screenName = screenName;
	}
	public TwitterTimeline() {
		super();
	}

	public JsonElement fetchTimeline() throws IOException {
		List<Tweet> timeline = new ArrayList();
		
		fetchUrl = fetchUrl;
		
		URLConnection urlConnection = new URL(fetchUrl).openConnection();
		urlConnection.connect();
		
		JsonReader reader = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));
		
		JsonParser parser = new JsonParser();
		
		JsonElement rootElement = parser.parse(reader);
		
		JsonArray tweetsJson = rootElement.getAsJsonArray();
		
		timeline = new ArrayList<Tweet>();
		Gson myGson = new Gson();
		
		for (JsonElement tweetElement : tweetsJson) {
			Tweet myTweet = myGson.fromJson(tweetElement, Tweet.class);
			timeline.add(myTweet);
		}
		
		return rootElement;
	}
}
