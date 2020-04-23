package application;

import java.util.ArrayList;
import java.util.HashMap;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * A class for location storing important info about it such as
 * name, latitude, longitude
 * @author Bryan Rogers
 *
 */
public class Location {
	
	//Setup variables for location object.
	private String latitude;
	private String longitude;
	private String displayName;
	private String limit = "5";
	private JSONArray locationResponse;

	/**
	 * takes a street address as an input and makes a call to get a bunch of location candidates
	 * from locationiq.com returns an arraylist of the names of the location candidates
	 * will return null if there is no location data available and prints out an error
	 * to the console in that case.
	 * Throttle at no more than 2 per second.
	 * @param address
	 * @return
	 */


	public ArrayList<String> getLocationCandidates(String userInput) {
		//include the API token and the country we want to default location info for
		String token = "e543fdd48f150b";
		String country = "us";
		ArrayList<String> locationCandidates = new ArrayList<String>();
		
			//Limit is the max number of returns we want for the location candidates. Defined as a global variable to allow user to change it in future expansion.
			userInput = userInput.replace(" ", "+");
			String url = String.format("https://us1.locationiq.com/v1/search.php?key=%1$s&q=%2$s&format=json&countrycodes=%3$s&limit=%4$s", token, userInput, country, limit);
			String response = GetResponseFromURL.makeRequest(url);
			
			if (response == null) {
				//System.out.println();
				return null;
			}
			
			try {
			this.locationResponse = new JSONArray(response);
			
			for (int i = 0; i < locationResponse.length(); i++) {
				locationCandidates.add(locationResponse.getJSONObject(i).getString("display_name"));
				
			}
			}catch (JSONException e) {
				PrintDebug.printDebug("JSON Exception getting location");
				return null;
			}

			return locationCandidates;
		
	}
	

	
	/**
	 * parses an address from an indicated location. Function takes in 
	 * the desired location name from the getLocationCandidates() method and
	 * puts the proper name, lat, long into the instance variables for the class
	 * 	 * @param index
	 */
	public void parseAddress(String locationName) {
		
		//for each entry in location response, check if it matches the user selected location and if so set the lat, long, name
		for (int index = 0; index < locationResponse.length(); index++) {
			try {
				JSONObject object = locationResponse.getJSONObject(index);
				if (object.getString("display_name").contentEquals(locationName)) {
					
					this.latitude = object.getString("lat");
					this.longitude = object.getString("lon");
					this.displayName = object.getString("display_name");
				}
			} catch (JSONException e) {
				PrintDebug.printDebug("Couldn't parse location due to JSON error");
			}
			
			
		}
		

	}
	
	/**
	 * Method to clone a location
	 * @param l
	 */
	public void clone (Location l) {
		this.displayName = l.getDisplayName();
		this.latitude = l.getLatitude();
		this.longitude = l.getLongitude();
	}
	
	/**
	 *Getters and setters for all the instance variables 
	 */
	public String getLatitude() {
		if (latitude.length() > 6) {
			return latitude.substring(0, 6);
		}
		return latitude;
	}
	public String getLongitude() {
		
		if (longitude.length() > 6) {
			return longitude.substring(0, 6);
		}
		return longitude;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}

}



