package com.wisdomleaf.speakingclock.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wisdomleaf.speakingclock.exception.InvalidInputException;

@SpringBootTest
class SpeakingClockServiceTest {
	
	@Autowired
	SpeakingClockService service;
	
	

	@Test
	void invalidTimeLengthTest() {
		assertThrows(InvalidInputException.class, () -> {
			service.getTimeInWords("12:1");
	    });
	}
	
	@Test
	void timeNotContainsColonTest() {
		assertThrows(InvalidInputException.class, () -> {
			service.getTimeInWords("12125");
	    });
	}
	
	@Test
	void timeContainsColonAtWrongIndexTest() {
		assertThrows(InvalidInputException.class, () -> {
			service.getTimeInWords("121:5");
	    });
	}
	
	@Test
	void invalidHourValueTest() {
		assertThrows(InvalidInputException.class, () -> {
			service.getTimeInWords("25:34");
	    });
	}
	
	@Test
	void invalidMinuteValueTest() {
		assertThrows(InvalidInputException.class, () -> {
			service.getTimeInWords("17:77");
	    });
	}
	
	@Test
	void specialCharTest() {
		assertThrows(InvalidInputException.class, () -> {
			service.getTimeInWords("1a:55");
	    });
	}
	
	@Test
	void validTimeTest() {
		String timeInWords = service.getTimeInWords("08:34");
		assertEquals("It's  eight thirty four . Not a Midnight or Midday", timeInWords);
			
	}
	
	@Test
	void validMultipleTimeTest() {
		List<String> validTimeInputs =Arrays.asList("00:00","05:15","08:30","13:40","17:53","21:60","23:59","12:00");
		List<String> expectedOutputs =Arrays.asList("It's Midnight",
				"It's  five  fifteen . Not a Midnight or Midday",
				"It's  eight thirty . Not a Midnight or Midday",
				"It's  thirteen forty . Not a Midnight or Midday",
				"It's  seventeen fifty three . Not a Midnight or Midday",
				"It's twenty one sixty . Not a Midnight or Midday",
				"It's twenty three fifty nine . Not a Midnight or Midday",
				"It's  twelve  . It's Midday");
		int i=0;
		for(String time : validTimeInputs)
		{
			String timeInWords=service.getTimeInWords(time);
			assertEquals(expectedOutputs.get(i), timeInWords);
			i++;
		}
	}


}
