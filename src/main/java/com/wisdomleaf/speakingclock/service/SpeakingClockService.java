package com.wisdomleaf.speakingclock.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.wisdomleaf.speakingclock.exception.InvalidInputException;

@Component
public class SpeakingClockService {

	private static final String DOT = ".";
	private static final String ITS = "It's";
	private static final String MIDDAY = "Midday";
	private static final String MIDNIGHT = "Midnight";
	private static final String NOTMIDDAY = "Not a Midnight or Midday";
	
	public String getTimeInWords(String time) {
		Integer hour;
		Integer minutes;
		
		/*
		 * Validating that the time input equals 5 characters.
		 * Validating that the time input contains : at index 2
		 */
		if(time.length()!=5 || !time.contains(":") || time.charAt(2)!=':')
			throw new InvalidInputException("Invalid Input provided. Please provide valid input like 08:34,12:40");
		
		try {
			
			/*
			 * Splitting the input by : to get the hours and minutes
			 */ 
			 
			List<Integer> timeSplitted = Stream.of(time.split(":"))
					  .map(Integer::parseInt)
					  .collect(Collectors.toList());
			
			hour = timeSplitted.get(0);
			minutes = timeSplitted.get(1);
			
			/*
			 * Validation that the hours and minutes should not exceed 23 and 60 respectively.
			 */
			if(hour>23 || minutes>60)
				throw new InvalidInputException("Wrong time provided.Please provide valid input like 08:34,12:40");
			
		} catch (NumberFormatException e) {
			/*
			 * If the parseInt method throws Number format exception,throw invalid input exception
			 * Validating that the hours and minutes should contain any string or special characters
			 */
			throw new InvalidInputException("Invalid Input provided.Could not parse the time."
					+ " Please provide valid input like 08:34,12:40");
		}
		
		if(time.equals("00:00"))
			return String.join(" ", Arrays.asList(ITS, MIDNIGHT));
		else
		{
			String hoursInWords = this.convertHoursOrMinutesToWords(hour);
		    String minutesInWords = this.convertHoursOrMinutesToWords(minutes);
		    String dayType = this.getMidDayOrNight(time);
		    /*
		     * Joining the hours minutes and midday representation in words.
		     */
            return String.join(" ", Arrays.asList(ITS, hoursInWords, minutesInWords, DOT, dayType ));
		}
	}
	
	/*
	 * Method that takes hour and minutes as input and returns the 
	 * exact word representation
	 */
	private String convertHoursOrMinutesToWords(Integer time) {
		StringBuilder builder = new StringBuilder();
		if (time/10 == 1) {
	        switch(time % 10) {
	            case 0: builder.append("ten"); break;
	            case 1: builder.append(" eleven"); break;
	            case 2: builder.append(" twelve"); break;
	            case 3: builder.append(" thirteen"); break;
	            case 4: builder.append(" fourteen"); break;
	            case 5: builder.append(" fifteen"); break;
	            case 6: builder.append(" sixteen"); break;
	            case 7: builder.append(" seventeen"); break;
	            case 8: builder.append(" eighteen"); break;
	            case 9: builder.append(" ninteen"); break;
	            default:
	        }
	        return builder.toString();
	    }
	    switch(time/10) {
	        case 0:break;
	        case 1: builder.append("ten"); break;
	        case 2: builder.append("twenty"); break;
	        case 3: builder.append("thirty"); break;
	        case 4: builder.append("forty"); break;
	        case 5: builder.append("fifty"); break;
	        case 6: builder.append("sixty"); break;
	        case 7: builder.append("seventy"); break;
	        case 8: builder.append("eighty"); break;
	        case 9: builder.append("ninety"); break;
	        default:
	        
	    }
	    switch(time % 10) {
	        case 0: break;
	        case 1: builder.append(" one"); break;
	        case 2: builder.append(" two"); break;
	        case 3: builder.append(" three"); break;
	        case 4: builder.append(" four"); break;
	        case 5: builder.append(" five"); break;
	        case 6: builder.append(" six"); break;
	        case 7: builder.append(" seven"); break;
	        case 8: builder.append(" eight"); break;
	        case 9: builder.append(" nine"); break;
	        default:
	    }
		return builder.toString();
	}
	
	
	/*
	 * Method that takes time as input and returns it is 
	 * midnight or midday or neither.
	 */
	 private String getMidDayOrNight(String time) 
	 {
		 if(time.equals("12:00"))
				return String.join(" ", Arrays.asList(ITS, MIDDAY));
		 if(time.equals("00:00"))
				return String.join(" ", Arrays.asList(ITS, MIDNIGHT));
	     return NOTMIDDAY;
	 }


	

}
