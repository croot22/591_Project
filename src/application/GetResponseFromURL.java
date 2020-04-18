package application;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.channels.UnresolvedAddressException;
import java.time.Duration;
/**
 * Class that is meant to get a HTTP response from a URL and pass
 * back that response as String text to other classes
 * @author Bryan Rogers
 *
 */
public class GetResponseFromURL {
	
	/**
	 * static method to make HTTP request to a URL
	 * @param url
	 * @return
	 */
	public static String makeRequest(String url) {
		
		//Setup HTTPClient and Request
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.ofSeconds(5)).build();
		String responseBody = "";
		
		//Try to call the URL and return null if it fails for anything but a redirect
		try {

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() > 200) {
							
				//handle a redirect by sending another request URL endpoint indicated in the headers
				if (response.statusCode() >= 300 && response.statusCode() < 400) {
					//System.out.println("Status code was " + response.statusCode() + " handling redirect");
					String url2 = "https://api.weather.gov" + response.headers().map().get("location").get(0);
					HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create(url2)).timeout(Duration.ofSeconds(5)).build();
					response = client.send(request2, BodyHandlers.ofString());
					responseBody = response.body();
					
				}
				else {
					System.out.println("The HTTP Request failed, status code is: " + response.statusCode());
					return null;
				}
			}
			else {
				responseBody = response.body();
			}
		} catch (IOException e) {
			System.out.println(url + "is unavailable");
			return null;
		} catch (InterruptedException e) {
			return null;
		} catch (UnresolvedAddressException e) {
			System.out.println(url + " is unavailable");
			return null;
		}
		
		return responseBody;
	}

}
