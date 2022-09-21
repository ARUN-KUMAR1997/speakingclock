package com.wisdomleaf.speakingclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.speakingclock.service.SpeakingClockService;

@RestController
@RequestMapping("/clock")
public class SpeakingClockController {
	
	@Autowired
	SpeakingClockService speakingClockService;

	
	/*
	 * An API which is going to get the time in 24 hours format and returns the time in words
	 * The API also returns that the time is Midday (12.00) or Midnight (00:00)
	 */
	@GetMapping("/timeInWords")
    public ResponseEntity<String> getTimeInWords(@RequestParam(name = "time") String time) {
        return ResponseEntity.ok(speakingClockService.getTimeInWords(time));
    }
}
