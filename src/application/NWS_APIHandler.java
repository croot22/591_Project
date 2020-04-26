package application;

import java.util.ArrayList;

/**
 * A class that is used to call the National Weather Service API to get forecast information
 * for a user provided location (GPS coordinates). The main class, getNWSForecast takes in a GPS location
 * and returns an arraylist of the available forecast data for that location.
 * @author Bryan Rogers
 *
 */
public class NWS_APIHandler {

	
	/**
	 * takes in the GPS location for a place and gets the URLs for calling
	 * the getForecast method as an Array.
	 * @param coordinates
	 * @return
	 */
	private ArrayList<String> getNWSGridLocation(String coordinates) {
		
		//setup the url to get the gridpoints from the request and return them for the next call
		String url = "https://api.weather.gov/points/" + coordinates;
		ArrayList<String> gridLocation = new ArrayList<String>();
		String response = GetResponseFromURL.makeRequest(url);
		JSONInputOutput parser = new JSONInputOutput();
		if (response == null) {
			return null;
		}
		gridLocation = parser.parseGPS(response);
		
		return gridLocation;
	}
	
	/**
	 * takes in GPS coordiates as a string latitude,longitude and returns the forecast for that location
	 * throttle this call at 2 per second. returns NULL if there is a failure to call one of the APIs
	 * @param coordinates
	 * @return
	 */
	
	public ArrayList<DailyForecast> getNWSForecast(String coordinates){
		//make call to prescribed URL location
		ArrayList<String> url = getNWSGridLocation(coordinates);
		//If that call failed, return null to indicate the API was not available
		if (url == null) {
			return null;
		}
		//Otherwise, take the response info from the getLocations method and get the forecast information
		ArrayList<DailyForecast> weatherData = new ArrayList<DailyForecast>();
		JSONInputOutput parseWeather = new JSONInputOutput();
		String requestForecast = GetResponseFromURL.makeRequest(url.get(0));
		String requestForecastGridData = GetResponseFromURL.makeRequest(url.get(1));

		//if either forecast failed, return null
		if (requestForecast == null || requestForecastGridData == null) {
			PrintDebug.printDebug("Failed to get forecast data for "+ url.get(0) + " or " + url.get(1));
			PrintDebug.printDebug("URL 1 response: " + requestForecast);
			PrintDebug.printDebug("URL 2 response: " + requestForecastGridData);
			return null;
		}

		//otherwise, parse the weather info that was returned
		weatherData = parseWeather.parseNWSForecast(requestForecast, requestForecastGridData);

		return weatherData;
	}

}



