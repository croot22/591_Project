package application;

import java.util.ArrayList;
import java.util.HashMap;


import org.json.JSONArray;
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
		String token = "e543fdd48f150b";
		String country = "us";
		ArrayList<String> locationCandidates = new ArrayList<String>();
		//HashMap<Integer, String> locationCandidates = new HashMap<Integer, String>();
		
			//Limit is the max number of returns we want for the location candidates. Defined as a global variable to allow user to change it in future expansion.
			userInput = userInput.replace(" ", "+");
			String url = String.format("https://us1.locationiq.com/v1/search.php?key=%1$s&q=%2$s&format=json&countrycodes=%3$s&limit=%4$s", token, userInput, country, limit);
			System.out.println(url);
			String response = GetResponseFromURL.makeRequest(url);
			
			if (response == null) {
				System.out.println();
				return null;
			}
			this.locationResponse = new JSONArray(response);
			
			for (int i = 0; i < locationResponse.length(); i++) {
				locationCandidates.add(locationResponse.getJSONObject(i).getString("display_name"));
				
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
		
		for (int index = 0; index < locationResponse.length(); index++) {
			JSONObject object = locationResponse.getJSONObject(index);
			if (object.getString("display_name").contentEquals(locationName)) {
				this.latitude = object.getString("lat");
				this.longitude = object.getString("lon");
				this.displayName = object.getString("display_name");
			}
			
			//this.savedLocations.put(object.toString());
		}
		

	}
	
	
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

/*
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.ofSeconds(5)).build();

try {
	System.out.println(url);
	HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
	//System.out.println("response code is: " + response.statusCode());
	if (response.statusCode() > 200) {
		System.out.println("Sorry, that location cannot be found. Please enter a different location:");
		return null;

	}
	else {
		this.locationResponse = new JSONArray(response.body());
		for (int i = 0; i < locationResponse.length(); i++) {
			locationCandidates.put(i+1, locationResponse.getJSONObject(i).getString("display_name"));
		}
		
		
	}
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

//}
* 
*/


