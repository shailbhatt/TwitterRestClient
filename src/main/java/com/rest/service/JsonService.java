package com.rest.service;

import java.io.IOException;

import javax.ws.rs.Produces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.rest.model.TwitterTimeline;

@RestController
public class JsonService {

	@RequestMapping(value = "/getTwitterTimeline", method = RequestMethod.GET)
	@Produces("application/json")
	public ResponseEntity<?> getTwitterTimelineInJSON() {

		JsonElement jsonElement = null;
		TwitterTimeline myTimeline = new TwitterTimeline();
		try {
			jsonElement = myTimeline.fetchTimeline();

		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.ok(jsonElement);

	}
}
